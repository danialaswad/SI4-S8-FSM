
import finite.state.machine.workspace.MyWorkSpaceImpl;
import finite.state.machine.annotation.FSMEvent;

public class WorkSpaceMyDoor extends MyWorkSpaceImpl {

	@FSMEvent(event="startClosingMotor")
    public void startClosingMotor(){
        System.out.println("start closing the door");
    }

    @FSMEvent(event="stopClosingMotor")
    public void stopClosingMotor(){
        System.out.println("stop closing the door");
    }

    @FSMEvent(event="startOpeningMotor")
    public void startOpeningMotor(){
        System.out.println("start opening the door");
    }

    @FSMEvent(event="stopOpeningMotor")
    public void stopOpeningMotor(){
        System.out.println("stop opening the door");
    }

	public WorkSpaceMyDoor() {
		start(this, new MyDoor());
	}

	public static void main(java.lang.String[] args) {
		WorkSpaceMyDoor myDoor = new WorkSpaceMyDoor();
		myDoor.submitEvent("close");
        myDoor.submitEvent("isClose");
        myDoor.submitEvent("open");
        myDoor.submitEvent("isOpen");
        myDoor.submitEvent("stop");
        myDoor.submitEvent("isOpen");
	}
}