package g2.fsm.machine;

import g2.fsm.object.State;
import g2.fsm.object.Transition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class StateMachineReader {

    private State currentState;
    private Transition currentTransition;
    private StateMachine machine;


    public StateMachineReader(){
        machine = new StateMachine();
        currentState = machine.getInitial();
        currentState = (State) currentState.onEnter();
    }


    private Method getEvent(String event){
        Method[] methods = machine.getClass().getMethods();
        String name = "get"+event.toUpperCase();
        Method m = null;
        for (Method method : methods){
            if (method.getName().equals(name))
                m = method;
        }
        return m;
    }

    private Method getEvent(String event, State sourceState){
        if (sourceState!= null){
            String name =  event.toUpperCase()+sourceState.getId();
            if (getEvent(name)!=null){
                return getEvent(name);
            }else {
                return getEvent(event,sourceState.getParent());
            }
        }
        return null;
    }

    private Transition invokeTransition(Method method){
        Object o = null;
        Method m;
        try {
            m = machine.getClass().getMethod(method.getName(),method.getParameterTypes() );
            o = m.invoke(machine,  null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return (Transition) o;
    }

    public void execute(String event){

        if (getEvent(event,currentState)!=null){
            currentTransition = invokeTransition(getEvent(event,currentState));
            currentState.onExit(currentTransition.getTarget());

            System.out.println(currentTransition.toString());

            currentState = currentTransition.getTarget();

            currentState = (State) currentState.onEnter();
        }
    }
}
