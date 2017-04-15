package finite.state.machine.engine;

import finite.state.machine.annotation.FSMEvent;
import finite.state.machine.generator.StateMachine;
import finite.state.machine.object.*;
import finite.state.machine.workspace.MyWorkSpace;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public class StateMachineReader {

    private State currentState;
    private Transition currentTransition;
    private StateMachine machine;
    private MyWorkSpace ms;
    private Map<String, Method> transitionEvent;
    private Map<String, Method> userEvent;

    private Queue<FSMEvents> internalQueue;

    public StateMachineReader(MyWorkSpace ms, StateMachine sm){
        this.ms = ms;
        this.machine = sm;
        this.currentState = machine.getInitial();
        this.internalQueue = new LinkedList<>();
        this.transitionEvent = initTransitionEvent();
        this.userEvent = initUserEvent();
        toState(currentState);
    }

    /*
        Event initialization
        Getting all the declared events in state machine and workspace
     */

    private Map<String, Method> initTransitionEvent(){
        Map<String, Method> map = new HashMap<>();
        Method[] methods = machine.getClass().getDeclaredMethods();
        for (Method m : methods){
            map.put(m.getName().toLowerCase(),m);
        }
        return map;
    }

    private Map<String, Method> initUserEvent(){
        Map<String, Method> map = new HashMap<>();
        Method[] methods = ms.getClass().getDeclaredMethods();
        for (Method m : methods){
            if (m.getAnnotation(FSMEvent.class) != null) {
                FSMEvent annotations = m.getAnnotation(FSMEvent.class);
                map.put(annotations.event(), m);
            }
        }
        return map;
    }


    private Method getTransitionState(String event, State sourceState){
        if (sourceState!= null){
            String name =  ("get" + event+sourceState.getId()).toLowerCase();
            if (transitionEvent.containsKey(name)){ return transitionEvent.get(name); }
            else { return getTransitionState(event,sourceState.getParent()); }
        }
        return null;
    }

    private Transition invokeTransition(Method method){
        Object o = null;
        Method m;
        try {
            m = machine.getClass().getMethod(method.getName(),method.getParameterTypes());
            o = m.invoke(machine);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.err.println("Transition " + method.getName() + " does not exist.");
        }
        return (Transition) o;
    }

    private void executeEvent(){
        if (currentTransition.hasEvent()){
            if (currentTransition.getEvent().getType().equals(FSMEventType.RAISE)){
                internalQueue.add(currentTransition.getEvent());
            } else {
                //extern
                if(userEvent.containsKey(currentTransition.getEvent().getId())) {
                    executeEvent(currentTransition.getEvent().getId());
                    //intern
                }else if(getTransitionState(currentTransition.getEvent().getId(),currentState)!=null){
                    currentTransition = invokeTransition(getTransitionState(currentTransition.getEvent().getId(), currentState));
                }
            }
        }

    }

    private void executeEvent(String event){
        Method m = null;
        try {
            m = ms.getClass().getMethod(userEvent.get(event).getName(), userEvent.get(event).getParameterTypes());
            m.invoke(ms);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.err.println("Event " + currentTransition.getEvent().getId() + " has not been declared.");
        }
    }

    private void fromState(State target){
        if (!target.getAllParent().contains(currentState.getId())){
            if (currentState.hasOnExit()){
                if (currentState.getOnExit().getType().equals(FSMEventType.RAISE)){
                    internalQueue.add(currentState.getOnExit());
                }else {
                    //extern
                    if(userEvent.containsKey(currentState.getOnExit().getId())) {
                        executeEvent(currentState.getOnExit().getId());
                        //intern
                    }else if(getTransitionState(currentState.getOnExit().getId(),currentState)!=null){
                        currentTransition = invokeTransition(getTransitionState(currentState.getOnExit().getId(), currentState));
                    }
                }
            }
            if (currentState.getParent()!=null){
                currentState = currentState.getParent();
                fromState(target);
            }
        }
    }
    private void transitionFromState(){
        if (currentTransition.getType().equals(TransitionType.EXTERNAL)) {
            fromState(currentTransition.getTarget());
        }
    }

    private void toState(State target){
        if (target != null){
            if (target.hasOnEnter()){
                if (target.getOnEnter().getType().equals(FSMEventType.RAISE)){
                    internalQueue.add(target.getOnEnter());
                }else {
                    //extern
                    if(userEvent.containsKey(target.getOnEnter().getId())) {
                        executeEvent(target.getOnEnter().getId());
                        //intern
                    }else if(getTransitionState(target.getOnEnter().getId(),currentState)!=null){
                        currentTransition = invokeTransition(getTransitionState(target.getOnEnter().getId(), currentState));
                    }
                }
            }
            currentState = target;
            toState(currentState.getFirstChild());
        }
        if (currentState instanceof FinalState){
            System.out.println("Reach final");
            System.exit(0);
        }
    }

    private void transitionToState(){
        if (currentTransition.getType().equals(TransitionType.EXTERNAL)) {
           toState(currentTransition.getTarget());
        }
    }

    public void activate(String event){
        internalQueue.add(new FSMEvents(event,"raise"));
        while(!internalQueue.isEmpty()){
            FSMEvents events = internalQueue.poll();
            if (getTransitionState(events.getId(),currentState)!=null) {
                currentTransition = invokeTransition(getTransitionState(events.getId(), currentState));
                transitionFromState();
                executeEvent();
                transitionToState();
            }
        }
    }

    /*
     Test purposes
     */

    public State getCurrentState() {
        return currentState;
    }

    public Transition getCurrentTransition() {
        return currentTransition;
    }
}
