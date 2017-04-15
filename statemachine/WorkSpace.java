
import finite.state.machine.workspace.MyWorkSpaceImpl;
import finite.state.machine.annotation.FSMEvent;

public class WorkSpace extends MyWorkSpaceImpl {

	@FSMEvent(event = "startClosingMotor")
	public void startClosingMotor() {
	}

	@FSMEvent(event = "stopClosingMotor")
	public void stopClosingMotor() {
	}

	@FSMEvent(event = "startOpeningMotor")
	public void startOpeningMotor() {
	}

	@FSMEvent(event = "stopOpeningMotor")
	public void stopOpeningMotor() {
	}

	public WorkSpace() {
		start(this, new MyDoor());
	}

	public static void main(java.lang.String[] args) {
	}
}