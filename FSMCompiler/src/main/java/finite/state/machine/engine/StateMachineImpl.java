package finite.state.machine.engine;

import finite.state.machine.generator.StateMachine;
import finite.state.machine.object.State;
import finite.state.machine.object.Transition;

class StateMachineImpl implements StateMachine {

	private final State State1;
	private final Transition in1State1;
	private final State State2;
	private final Transition in2State2;

	public State getInitial() {
		return State1;
	}

	public Transition getIn1State1() {
		return in1State1;
	}

	public Transition getIn2State2() {
		return in2State2;
	}

	StateMachineImpl() {
		this.State1 = new State("State1");
		this.State1.setOnExit("action1", "send");
		this.State1.setOnEnter("action5", "send");
		this.State2 = new State("State2");
		this.State2.setOnEnter("action3", "send");
		this.State2.setOnExit("action4", "send");
		this.in1State1 = new Transition("in1", State1, State2);
		this.in1State1.setEvent("in2", "raise");
		this.in2State2 = new Transition("in2", State2, State1);
		this.in2State2.setEvent("action2", "send");
	}
}