package fsm.workspace;


import finite.state.machine.annotation.FSMEvent;
import finite.state.machine.workspace.MyWorkSpaceImpl;

public class MyStateMachine extends MyWorkSpaceImpl {

    public MyStateMachine(){
        super();
        start(this);
    }


    @FSMEvent(event="print-hello")
    public void print_hello(){
        System.out.println("Hello");
    }

    @FSMEvent(event="print-hello")
    public void print_bye(){
        System.out.println("Bye");
    }

    @FSMEvent(event="print-java")
    public void print_java(){
        System.out.println("Java");
    }

    @FSMEvent(event="print-world")
    public void print_world(){
        System.out.println("World");
    }


    public static void main(String[] args){
        MyStateMachine m = new MyStateMachine();
        m.submitEvent("Button1");
        m.submitEvent("Button2");
        m.submitEvent("Button1");
        m.submitEvent("Button2");
        m.submitEvent("Button1");

    }
}
