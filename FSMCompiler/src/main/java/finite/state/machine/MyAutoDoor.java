package finite.state.machine;


import finite.state.machine.annotation.FSMEvent;
import finite.state.machine.workspace.MyWorkSpaceImpl;

public class MyAutoDoor extends MyWorkSpaceImpl {

    public MyAutoDoor(){
        start(this);
    }


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


    public static void main(String[] args){
        MyAutoDoor stateMachine = new MyAutoDoor();
        stateMachine.submitEvent("close");
        stateMachine.submitEvent("isClose");
        stateMachine.submitEvent("open");
        stateMachine.submitEvent("isOpen");
        stateMachine.submitEvent("stop");
        stateMachine.submitEvent("isOpen");
    }
}