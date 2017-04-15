
import finite.state.machine.workspace.MyWorkSpaceImpl;
import finite.state.machine.annotation.FSMEvent;

public class WorkSpaceMyRaise extends MyWorkSpaceImpl {

	 @FSMEvent(event="action2")
    public void action2(){
        System.out.println("This is action 2");
    }
    @FSMEvent(event="action1")
    public void action1(){
        System.out.println("Exit state 1");
    }

    @FSMEvent(event="action5")
    public void action5(){
        System.out.println("Enter state 1");
    }

    @FSMEvent(event="action3")
    public void action3(){
        System.out.println("Enter state 2");
    }

    @FSMEvent(event="action4")
    public void action4(){
        System.out.println("Exit state 2");
    }

	public WorkSpaceMyRaise() {
		start(this, new MyRaise());
	}

	public static void main(java.lang.String[] args) {
		WorkSpaceMyRaise myRaise = new WorkSpaceMyRaise();

        myRaise.submitEvent("in1");
	}
}