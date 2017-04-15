
import finite.state.machine.generator.StateMachine;
import finite.state.machine.object.State;
import finite.state.machine.object.Transition;
import finite.state.machine.object.FinalState;

public class MyDoor implements StateMachine {

	private final State opened;
	private final Transition stopopened;
	private final Transition closeopened;
	private final State isClosing;
	private final Transition isCloseisClosing;
	private final State closed;
	private final Transition stopclosed;
	private final Transition openclosed;
	private final State isOpening;
	private final Transition isOpenisOpening;
	private final FinalState Final_1;

	public State getInitial() {
		return opened;
	}

	public Transition getStopopened() {
		return stopopened;
	}

	public Transition getCloseopened() {
		return closeopened;
	}

	public Transition getIsCloseisClosing() {
		return isCloseisClosing;
	}

	public Transition getStopclosed() {
		return stopclosed;
	}

	public Transition getOpenclosed() {
		return openclosed;
	}

	public Transition getIsOpenisOpening() {
		return isOpenisOpening;
	}

	public MyDoor() {
		this.opened = new State("opened");
		this.isClosing = new State("isClosing");
		this.closed = new State("closed");
		this.isOpening = new State("isOpening");
		this.Final_1 = new FinalState("Final_1");
		this.stopopened = new Transition("stop", opened, Final_1);
		this.closeopened = new Transition("close", opened, isClosing);
		this.closeopened.setEvent("startClosingMotor", "send");
		this.isCloseisClosing = new Transition("isClose", isClosing, closed);
		this.isCloseisClosing.setEvent("stopClosingMotor", "send");
		this.stopclosed = new Transition("stop", closed, Final_1);
		this.openclosed = new Transition("open", closed, isOpening);
		this.openclosed.setEvent("startOpeningMotor", "send");
		this.isOpenisOpening = new Transition("isOpen", isOpening, opened);
		this.isOpenisOpening.setEvent("stopOpeningMotor", "send");
	}
}