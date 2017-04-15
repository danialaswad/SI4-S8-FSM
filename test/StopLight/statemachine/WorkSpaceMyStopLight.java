
import finite.state.machine.workspace.MyWorkSpaceImpl;
import finite.state.machine.annotation.FSMEvent;

public class WorkSpaceMyStopLight extends MyWorkSpaceImpl {

	  @FSMEvent(event = "goGreen")
    public void goGreen(){
        System.out.println("Go Green");
    }

    @FSMEvent(event = "startGoGreen")
    public void startGoGreen(){
        System.out.println("Start Go Green");
    }

    @FSMEvent(event = "goRed")
    public void goRed(){
        System.out.println("Go Red");
    }

    @FSMEvent(event = "startGoRed")
    public void startGoRed(){
        System.out.println("Start Go Red");
    }

    @FSMEvent(event = "blink")
    public void blink(){
        System.out.println("blink");
    }

    @FSMEvent(event = "unblink")
    public void unblink(){
        System.out.println("unblink");
    }

    @FSMEvent(event = "smashed")
    public void smashed(){
        System.out.println("smashed");
    }

    @FSMEvent(event = "repair")
    public void repair(){
        System.out.println("repair");
    }

    @FSMEvent(event = "stoplightBroken")
    public void stoplightBroken(){
        System.out.println("Stop light Broken");
    }

    @FSMEvent(event = "stoplightRepair")
    public void stoplightRepair(){
        System.out.println("Stop Light Repair");
    }

	public WorkSpaceMyStopLight() {
		start(this, new MyStopLight());
	}

	public static void main(java.lang.String[] args) {
		WorkSpaceMyStopLight stopLight = new WorkSpaceMyStopLight();
		stopLight.submitEvent("goGreen");
        stopLight.submitEvent("startGoGreen");
        stopLight.submitEvent("goRed");
        stopLight.submitEvent("startGoRed");
        // Execute onEnter
        stopLight.submitEvent("smashed");
        stopLight.submitEvent("unblink");
        stopLight.submitEvent("blink");
        stopLight.submitEvent("unblink");
        stopLight.submitEvent("blink");
        // Execute onExit
        stopLight.submitEvent("repair");
        stopLight.submitEvent("goGreen");
	}
}