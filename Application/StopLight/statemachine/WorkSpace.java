
import finite.state.machine.workspace.MyWorkSpaceImpl;
import finite.state.machine.annotation.FSMEvent;

public class WorkSpace extends MyWorkSpaceImpl {


	private int redCounter = 0;
	private int greenCounter = 0;
	private int yellowCounter = 0;
	private int workCounter = 0;
	private int brokenCounter = 0;

	/*
		Print functions
	*/
	@FSMEvent(event = "toYellow")
	public void toYellow() {
		System.out.println("Stoplight is now Yellow");
	}

	@FSMEvent(event = "toRed")
	public void toRed() {
		System.out.println("Stoplight is now Red");
	}

	@FSMEvent(event = "toGreen")
	public void toGreen() {
		System.out.println("Stoplight is now Green");
	}

	@FSMEvent(event = "working")
	public void working() {
		System.out.println("Stoplight is working");
	}

	@FSMEvent(event = "broken")
	public void broken() {
		System.out.println("Stoplight is broken");
	}

	/*
		Counter functions
	*/

	@FSMEvent(event = "countRed")
	public void countRed() {
		if(workCounter < 20){
			if (redCounter < 5) {
				System.out.println("Red");
				redCounter++;
			}else {
				workCounter++;
				redCounter = 0;
				this.submitEvent("ry");
			}
		} else if (workCounter == 20) {
				workCounter++;
			this.submitEvent("broke");
		}else {
			this.submitEvent("stop");
		}
	}

	@FSMEvent(event = "countRYellow")
	public void countRYellow() {
		if (workCounter < 20) {
			if (yellowCounter < 2) {
				System.out.println("Yellow");
				yellowCounter++;
			}else {
				workCounter++;
				yellowCounter = 0;
				this.submitEvent("yg");
			}
		} else if (workCounter == 20){
			workCounter++;
			this.submitEvent("broke");
		}else {
			this.submitEvent("stop");
		}
		
	}

	@FSMEvent(event = "countGreen")
	public void countGreen() {
		if (workCounter < 20) {
			if (greenCounter < 5) {
				System.out.println("Green");
				greenCounter++;
			} else{
				workCounter++;
				greenCounter = 0;
				this.submitEvent("gy");
			}
		}else if (workCounter == 20){
				workCounter++;
			this.submitEvent("broke");
		} else {
			this.submitEvent("stop");
		}
		
	}

	@FSMEvent(event = "countGYellow")
	public void countGYellow() {
		greenCounter = 0;
		if (workCounter < 20) {
			if (yellowCounter < 2) {
				System.out.println("Yellow");
				yellowCounter++;
			} else {
				workCounter++;
				yellowCounter = 0;
				this.submitEvent("yr");
			}
		} else if (workCounter == 20){
				workCounter++;
			this.submitEvent("broke");
		} else {
			this.submitEvent("stop");
		}
		
	}

	
	@FSMEvent(event = "unblinking")
	public void unblinking() {
		if (brokenCounter < 5) {
			brokenCounter++;
			System.out.println("unblink");
		} else {
			this.submitEvent("repair");
		}
	}

	@FSMEvent(event = "blinking")
	public void blinking() {
		if (brokenCounter < 5) {
			brokenCounter++;
			System.out.println("blink");
		} else {
			this.submitEvent("repair");
		}
	}

	public WorkSpace() {
		start(this, new MyStopLight());
	}

	public static void main(java.lang.String[] args) {
		WorkSpace m = new WorkSpace();
		m.submitEvent("ry");
	}
}