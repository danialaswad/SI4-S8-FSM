package fsm.workspace.entity;


public class Transition extends SCXMLObject {

    // Event name of the transition
    private String event;

    private State target;

    public Transition(String event){
        this.event = event;
    }


    public String getEvent(){
        return event;
    }

    public void setTarget(State target){
        this.target = target;
    }

    public State getTarget(){
        return target;
    }

    public String toString() {
        return "Transition [event:"+event+"] [target:" + target.getId() + "]" ;
    }

    public void onEnter() {
        System.out.println("enter transition with event :  " + event);

    }

    public void onExit(State targetParent) {
        System.out.println("exit transition with event :" + event);

    }
}
