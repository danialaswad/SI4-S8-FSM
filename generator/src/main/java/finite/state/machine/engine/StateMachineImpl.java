package finite.state.machine.engine;

import finite.state.machine.engine.StateMachine;
import finite.state.machine.object.State;
import finite.state.machine.object.Transition;

class StateMachineImpl implements StateMachine {

	private final State S1;
	private final Transition Button1S1;
	private final State S2;
	private final Transition Button1S2;
	private final State S3;
	private final Transition Button2S3;
	private final State S4;
	private final Transition Button1S4;

	public State getInitial() {
		return S1;
	}

	public Transition getButton1S1() {
		return Button1S1;
	}

	public Transition getButton1S2() {
		return Button1S2;
	}

	public Transition getButton2S3() {
		return Button2S3;
	}

	public Transition getButton1S4() {
		return Button1S4;
	}

	StateMachineImpl() {
		this.S1 = new State("S1");
		this.S2 = new State("S2", S1);
		this.S3 = new State("S3", S1);
		this.S1.setFirstChild(S2);
		this.S4 = new State("S4");
		this.Button1S1 = new Transition("Button1", S1, S4);
		this.Button1S1.setSend("print-hello");
		this.Button1S2 = new Transition("Button1", S2, S3);
		this.Button1S2.setSend("print-world");
		this.Button2S3 = new Transition("Button2", S3, S2);
		this.Button2S3.setSend("print-java");
		this.Button1S4 = new Transition("Button1", S4, S1);
		this.Button1S4.setSend("print-bye");
	}
}