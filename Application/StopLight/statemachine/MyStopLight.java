
import finite.state.machine.generator.StateMachine;
import finite.state.machine.object.State;
import finite.state.machine.object.FinalState;
import finite.state.machine.object.Transition;

public class MyStopLight implements StateMachine {

	private final State StopLight;
	private final State Work;
	private final State Red;
	private final FinalState Final_1;
	private final Transition ryRed;
	private final Transition redToRedRed;
	private final State R_Yellow;
	private final Transition ygR_Yellow;
	private final Transition r_yellowToYellowR_Yellow;
	private final State Green;
	private final Transition gyGreen;
	private final Transition greenToGreenGreen;
	private final Transition brokeWork;
	private final State G_Yellow;
	private final Transition yrG_Yellow;
	private final Transition g_yellowToYellowG_Yellow;
	private final State Broken;
	private final State Blink;
	private final Transition unblinkBlink;
	private final State UnBlink;
	private final Transition blinkUnBlink;
	private final Transition repairBroken;
	private final Transition stopStopLight;

	public State getInitial() {
		return StopLight;
	}

	public Transition getRyRed() {
		return ryRed;
	}

	public Transition getRedToRedRed() {
		return redToRedRed;
	}

	public Transition getYgR_Yellow() {
		return ygR_Yellow;
	}

	public Transition getR_yellowToYellowR_Yellow() {
		return r_yellowToYellowR_Yellow;
	}

	public Transition getGyGreen() {
		return gyGreen;
	}

	public Transition getGreenToGreenGreen() {
		return greenToGreenGreen;
	}

	public Transition getBrokeWork() {
		return brokeWork;
	}

	public Transition getYrG_Yellow() {
		return yrG_Yellow;
	}

	public Transition getG_yellowToYellowG_Yellow() {
		return g_yellowToYellowG_Yellow;
	}

	public Transition getUnblinkBlink() {
		return unblinkBlink;
	}

	public Transition getBlinkUnBlink() {
		return blinkUnBlink;
	}

	public Transition getRepairBroken() {
		return repairBroken;
	}

	public Transition getStopStopLight(){
		return stopStopLight;
	}

	public MyStopLight() {
		this.StopLight = new State("StopLight");
		this.Work = new State("Work", StopLight);
		this.Red = new State("Red", Work);
		this.Red.setOnEnter("redToRed", "raise");
		this.R_Yellow = new State("R_Yellow", Work);
		this.R_Yellow.setOnEnter("r_yellowToYellow", "raise");
		this.Green = new State("Green", Work);
		this.Green.setOnEnter("greenToGreen", "raise");
		this.Work.setOnEnter("working", "send");
		this.Work.setOnExit("broken", "send");
		this.G_Yellow = new State("G_Yellow", Work);
		this.G_Yellow.setOnEnter("g_yellowToYellow", "raise");
		this.Work.setFirstChild(Red);
		this.Broken = new State("Broken", StopLight);
		this.Blink = new State("Blink", Broken);
		this.Blink.setOnEnter("unblink", "raise");
		this.UnBlink = new State("UnBlink", Broken);
		this.UnBlink.setOnEnter("blink", "raise");
		this.Broken.setFirstChild(Blink);
		this.StopLight.setFirstChild(Work);
		this.Final_1 = new FinalState("Final_1");
		this.stopStopLight = new Transition("stop", StopLight, Final_1);
		this.ryRed = new Transition("ry", Red, R_Yellow);
		this.ryRed.setEvent("toYellow", "send");
		this.redToRedRed = new Transition("redToRed", Red, Red);
		this.redToRedRed.setEvent("countRed", "send");
		this.ygR_Yellow = new Transition("yg", R_Yellow, Green);
		this.ygR_Yellow.setEvent("toGreen", "send");
		this.r_yellowToYellowR_Yellow = new Transition("r_yellowToYellow",
				R_Yellow, R_Yellow);
		this.r_yellowToYellowR_Yellow.setEvent("countRYellow", "send");
		this.gyGreen = new Transition("gy", Green, G_Yellow);
		this.gyGreen.setEvent("toYellow", "send");
		this.greenToGreenGreen = new Transition("greenToGreen", Green, Green);
		this.greenToGreenGreen.setEvent("countGreen", "send");
		this.brokeWork = new Transition("broke", Work, Broken);
		this.yrG_Yellow = new Transition("yr", G_Yellow, Red);
		this.yrG_Yellow.setEvent("toRed", "send");
		this.g_yellowToYellowG_Yellow = new Transition("g_yellowToYellow",
				G_Yellow, G_Yellow);
		this.g_yellowToYellowG_Yellow.setEvent("countGYellow", "send");
		this.unblinkBlink = new Transition("unblink", Blink, UnBlink);
		this.unblinkBlink.setEvent("unblinking", "send");
		this.blinkUnBlink = new Transition("blink", UnBlink, Blink);
		this.blinkUnBlink.setEvent("blinking", "send");
		this.repairBroken = new Transition("repair", Broken, Work);
	}
}