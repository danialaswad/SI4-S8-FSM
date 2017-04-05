package finite.state.machine.engine;

import finite.state.machine.generator.StateMachine;
import finite.state.machine.object.State;
import finite.state.machine.object.Transition;

class StateMachineImpl implements StateMachine {

	private final State working;
	private final State red;
	private final Transition goGreenred;
	private final State yellow;
	private final State redGoingGreen;
	private final Transition startGoGreenredGoingGreen;
	private final State greenGoingRed;
	private final Transition startGoRedgreenGoingRed;
	private final State green;
	private final Transition goRedgreen;
	private final Transition smashedworking;
	private final State broken;
	private final State blinking;
	private final Transition unblinkblinking;
	private final State unBlinking;
	private final Transition blinkunBlinking;
	private final Transition repairbroken;

	public State getInitial() {
		return working;
	}

	public Transition getGoGreenred() {
		return goGreenred;
	}

	public Transition getStartGoGreenredGoingGreen() {
		return startGoGreenredGoingGreen;
	}

	public Transition getStartGoRedgreenGoingRed() {
		return startGoRedgreenGoingRed;
	}

	public Transition getGoRedgreen() {
		return goRedgreen;
	}

	public Transition getSmashedworking() {
		return smashedworking;
	}

	public Transition getUnblinkblinking() {
		return unblinkblinking;
	}

	public Transition getBlinkunBlinking() {
		return blinkunBlinking;
	}

	public Transition getRepairbroken() {
		return repairbroken;
	}

	StateMachineImpl() {
		this.working = new State("working");
		this.red = new State("red", working);
		this.yellow = new State("yellow", working);
		this.redGoingGreen = new State("redGoingGreen", yellow);
		this.greenGoingRed = new State("greenGoingRed", yellow);
		this.yellow.setFirstChild(redGoingGreen);
		this.green = new State("green", working);
		this.working.setFirstChild(red);
		this.broken = new State("broken");
		this.blinking = new State("blinking", broken);
		this.unBlinking = new State("unBlinking", broken);
		this.broken.setOnEnter("stoplightBroken");
		this.broken.setOnExit("stoplightRepair");
		this.broken.setFirstChild(blinking);
		this.goGreenred = new Transition("goGreen", red, redGoingGreen);
		this.goGreenred.setSend("goGreen");
		this.startGoGreenredGoingGreen = new Transition("startGoGreen",
				redGoingGreen, green);
		this.startGoGreenredGoingGreen.setSend("startGoGreen");
		this.startGoRedgreenGoingRed = new Transition("startGoRed",
				greenGoingRed, red);
		this.startGoRedgreenGoingRed.setSend("startGoRed");
		this.goRedgreen = new Transition("goRed", green, greenGoingRed);
		this.goRedgreen.setSend("goRed");
		this.smashedworking = new Transition("smashed", working, broken);
		this.unblinkblinking = new Transition("unblink", blinking, unBlinking);
		this.unblinkblinking.setSend("unblink");
		this.blinkunBlinking = new Transition("blink", unBlinking, blinking);
		this.blinkunBlinking.setSend("blink");
		this.repairbroken = new Transition("repair", broken, working);
	}
}