package g2.fsm.machine;


import g2.fsm.object.SCXMLObject;
import g2.fsm.object.State;
import g2.fsm.object.Transition;

import java.util.List;


public class StateMachine {

    private SCXMLObject currentState;

    public StateMachine(State currentState){

       this.currentState = currentState;
    }


    private SCXMLObject transition_procedure(String event){

        return transition_procedure_rec((State) currentState, event);
    }

    private SCXMLObject transition_procedure_rec(State state, String event){
        if (state.getTransitions(event) != null) {

            List list = state.getTransitions(event);

            Transition transition = (Transition) list.get(0);


            state.onExit(transition.getTarget().getParent());


            transition.onEnter();


            transition.onExit(null);


            transition.getTarget().onEnter();

            return  transition.getTarget();
        }
        if (state.getParent()!=null){
            return transition_procedure_rec( state.getParent(), event);
        }
        return currentState;
    }


    private SCXMLObject stateToTransition(State state, String event){
        if (state.getTransitions(event) != null) {
            List list = state.getTransitions(event);
            return (Transition) list.get(0);
        }
        if (state.getParent()!=null){
            return transition_procedure_rec( state.getParent(), event);
        }

        return null;
    }

    private SCXMLObject stateToTransition(String event){
        SCXMLObject state = stateToTransition((State) currentState,event);
        return state == null? currentState : state;
    }

    private SCXMLObject transitionToState(){
        return ((Transition)currentState).getTarget();
    }


    public void execute(String event){

        currentState.onEnter();
        // State to transition
        SCXMLObject transition = stateToTransition(event);
        if (transition instanceof State) return;
        currentState.onExit(((Transition)transition).getTarget());
        currentState = transition;
        currentState.onEnter();
        // execution of events


        // transition to State
        currentState.onExit(null);
        currentState = transitionToState();
    }


}
