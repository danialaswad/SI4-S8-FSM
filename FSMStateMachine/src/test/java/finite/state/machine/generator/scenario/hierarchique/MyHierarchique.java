package finite.state.machine.generator.scenario.hierarchique;

import finite.state.machine.generator.StateMachine;
import finite.state.machine.object.State;
import finite.state.machine.object.Transition;

public class MyHierarchique implements StateMachine {

	private final State State1;
	private final State State3;
	private final Transition e1State3;
	private final State State4;
	private final Transition e2State4;
	private final Transition e1State1;
	private final State State2;

	public State getInitial() {
		return State1;
	}

	public Transition getE1State3() {
		return e1State3;
	}

	public Transition getE2State4() {
		return e2State4;
	}

	public Transition getE1State1() {
		return e1State1;
	}

	public MyHierarchique() {
		this.State1 = new State("State1");
		this.State3 = new State("State3", State1);
		this.State4 = new State("State4", State1);
		this.State1.setFirstChild(State3);
		this.State2 = new State("State2");
		this.e1State3 = new Transition("e1", State3, State4);
		this.e2State4 = new Transition("e2", State4, State3);
		this.e1State1 = new Transition("e1", State1, State2);
	}
}