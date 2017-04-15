package finite.state.machine.generator.scenario.simple;

import finite.state.machine.generator.StateMachine;
import finite.state.machine.object.State;
import finite.state.machine.object.Transition;

/**
 * Created by danial on 15/04/2017.
 */
public class MySimple implements StateMachine {

    private final State State1;
    private final State State2;
    private final Transition e1State1;

    public State getInitial() {
        return State1;
    }

    public Transition getE1State1(){
        return e1State1;
    }

    public MySimple(){
        this.State1 = new State("State1");
        this.State2 = new State("State2");
        this.e1State1 = new Transition("e1", State1, State2);

    }
}
