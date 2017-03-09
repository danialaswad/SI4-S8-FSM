package g2.fsm.machine;


import g2.fsm.object.State;
import g2.fsm.object.Transition;

import java.util.List;


public class StateMachine {

    private State currentState;

    public StateMachine(State currentState){

       this.currentState = currentState;
    }

    private void execute_onentry(){

        System.out.println("onentry event for " + currentState.getId());
        /*if (currentState.getOnEntry() != null){
            // TODO execute onentry event
        }*/
    }

    private void execute_onexit(){

        System.out.println("onexit event");
        /*if (currentState.getOnExit() != null){
            // TODO execute onexit event
        }*/
    }

    private void exit_procedure(State fromState, State toState){

    }

    private void entry_procedure(){

        execute_onentry();

        while(currentState.getFirstChild()!=null){

            currentState = currentState.getChildrens().get(currentState.getFirstChild());

            entry_procedure();
        }
    }


    private State transition_procedure(String event){

        return transition_procedure_rec(currentState, event);
    }

    private State transition_procedure_rec(State state, String event){
        if (state.getTransitions(event) != null) {
            List list = state.getTransitions(event);

            Transition transition = (Transition) list.get(0);
            // TODO execute event
            System.out.print (", event : " + transition.getEvent() + " ");

            exit_procedure(currentState,transition.getTarget());

            return  transition.getTarget();
        }
        if (state.getParent()!=null){
            return transition_procedure_rec( state.getParent(), event);
        }
        return currentState;
    }

    public void execute(String event){

        entry_procedure();

        System.out.print(" current state : " + currentState.getId());

        // TODO call onexit
        currentState = transition_procedure(event);

        System.out.println(", new state : " + currentState.getId());

    }


}
