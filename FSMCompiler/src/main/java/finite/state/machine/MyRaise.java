package finite.state.machine;

import finite.state.machine.annotation.FSMEvent;
import finite.state.machine.workspace.MyWorkSpaceImpl;

/**
 * Created by danial on 12/04/2017.
 */
public class MyRaise extends MyWorkSpaceImpl {

    public MyRaise(){
        start(this);
    }


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
    public static void main(String[] args){
        MyRaise myRaise = new MyRaise();

        myRaise.submitEvent("in1");
        myRaise.submitEvent("in1");
        myRaise.submitEvent("in1");
        myRaise.submitEvent("in1");
        myRaise.submitEvent("in1");
        myRaise.submitEvent("in1");
    }
}
