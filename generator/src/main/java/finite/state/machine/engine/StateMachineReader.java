package finite.state.machine.engine;

import finite.state.machine.annotation.FSMEvent;
import finite.state.machine.object.Transition;
import finite.state.machine.object.State;
import finite.state.machine.workspace.MyWorkSpace;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class StateMachineReader {

    private State currentState;
    private Transition currentTransition;
    private StateMachine machine;
    private MyWorkSpace ms;
    private Map<String, Method> getEventMap;
    private Map<String, Method> eventMap;

    public StateMachineReader(MyWorkSpace ms){
        machine = new StateMachineImpl();
        currentState = machine.getInitial();
        currentState = currentState.enterProcedure();
        this.getEventMap = initMap();
        this.ms = ms;
        initEventMap();
    }

    private Map<String, Method> initMap(){
        Map<String, Method> map = new HashMap<>();
        Method[] methods = machine.getClass().getMethods();
        for (Method m : methods){
            map.put(m.getName().toLowerCase(),m);
        }
        return map;
    }

    private void initEventMap(){
        eventMap = new HashMap<>();
        Method[] methods = ms.getClass().getDeclaredMethods();
        for (Method m : methods){
            if (m.getAnnotation(FSMEvent.class) != null) {
                FSMEvent annotations = m.getAnnotation(FSMEvent.class);
                eventMap.put(annotations.event(), m);
            }
        }
    }


    private Method getEvent(String event, State sourceState){
        if (sourceState!= null){
            String name =  ("get" + event+sourceState.getId()).toLowerCase();
            if (getEventMap.get(name)!=null){ return getEventMap.get(name); }
            else { return getEvent(event,sourceState.getParent()); }
        }
        return null;
    }

    private Transition invokeTransition(Method method){
        Object o = null;
        Method m;
        try {
            m = machine.getClass().getMethod(method.getName(),method.getParameterTypes() );
            o = m.invoke(machine);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
        return (Transition) o;
    }

    private void executeEvent(){
        if (currentTransition.hasSend()){
            Method m = null;
            try {
                m = ms.getClass().getMethod(eventMap.get(currentTransition.getSend()).getName(), eventMap.get(currentTransition.getSend()).getParameterTypes());
                m.invoke(ms);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void execute(String event){
        if (getEvent(event,currentState)!=null){
            currentTransition = invokeTransition(getEvent(event,currentState));
            currentState.exitProcedure(currentTransition.getTarget());
            executeEvent();
            currentState = currentTransition.getTarget().enterProcedure();
        }
    }

}
