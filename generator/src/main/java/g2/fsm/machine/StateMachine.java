package g2.fsm.machine;

import g2.fsm.object.State;
import g2.fsm.object.Transition;

public class StateMachine {

	private State S1;
	private Transition e1S1;
	private State S4;
	private Transition e2S4;
	private Transition e3S4;
	private State S2;
	private Transition e1S2;
	private State S3;
	private Transition e1S3;
	private State S5;
	private Transition e4S5;
	private State S8;
	private Transition e2S8;
	private State S6;
	private Transition e3S6;

	public State getInitial() {
		return S1;
	}

	public State getS1() {
		return S1;
	}

	public void setS1(State S1) {
		this.S1 = S1;
	}

	public Transition getE1S1() {
		return e1S1;
	}

	public void setE1S1(Transition e1S1) {
		this.e1S1 = e1S1;
	}

	public State getS4() {
		return S4;
	}

	public void setS4(State S4) {
		this.S4 = S4;
	}

	public Transition getE2S4() {
		return e2S4;
	}

	public void setE2S4(Transition e2S4) {
		this.e2S4 = e2S4;
	}

	public Transition getE3S4() {
		return e3S4;
	}

	public void setE3S4(Transition e3S4) {
		this.e3S4 = e3S4;
	}

	public State getS2() {
		return S2;
	}

	public void setS2(State S2) {
		this.S2 = S2;
	}

	public Transition getE1S2() {
		return e1S2;
	}

	public void setE1S2(Transition e1S2) {
		this.e1S2 = e1S2;
	}

	public State getS3() {
		return S3;
	}

	public void setS3(State S3) {
		this.S3 = S3;
	}

	public Transition getE1S3() {
		return e1S3;
	}

	public void setE1S3(Transition e1S3) {
		this.e1S3 = e1S3;
	}

	public State getS5() {
		return S5;
	}

	public void setS5(State S5) {
		this.S5 = S5;
	}

	public Transition getE4S5() {
		return e4S5;
	}

	public void setE4S5(Transition e4S5) {
		this.e4S5 = e4S5;
	}

	public State getS8() {
		return S8;
	}

	public void setS8(State S8) {
		this.S8 = S8;
	}

	public Transition getE2S8() {
		return e2S8;
	}

	public void setE2S8(Transition e2S8) {
		this.e2S8 = e2S8;
	}

	public State getS6() {
		return S6;
	}

	public void setS6(State S6) {
		this.S6 = S6;
	}

	public Transition getE3S6() {
		return e3S6;
	}

	public void setE3S6(Transition e3S6) {
		this.e3S6 = e3S6;
	}

	public StateMachine() {
		this.S1 = new State("S1");
		this.S4 = new State("S4", S1);
		this.S1.setFirstChild(S4);
		this.S2 = new State("S2");
		this.S3 = new State("S3");
		this.S5 = new State("S5", S3);
		this.S8 = new State("S8", S5);
		this.S5.setFirstChild(S8);
		this.S6 = new State("S6", S3);
		this.S3.setFirstChild(S5);
		this.e1S1 = new Transition("e1", S1, S2);
		this.e2S4 = new Transition("e2", S4, S3);
		this.e3S4 = new Transition("e3", S4, S2);
		this.e1S2 = new Transition("e1", S2, S3);
		this.e1S3 = new Transition("e1", S3, S1);
		this.e4S5 = new Transition("e4", S5, S6);
		this.e2S8 = new Transition("e2", S8, S2);
		this.e3S6 = new Transition("e3", S6, S4);
	}
}