package g2.fsm.object;


public class Transition extends SCXMLObject {

    // Event name of the transition
    private String event;

    private State target;



    private State source;

    public Transition(String event, State source, State target){
        this.source = source;
        this.target = target;
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

    public State getSource() {
        return source;
    }

    public void setSource(State source) {
        this.source = source;
    }

    public String toString() {
        return "Transition [event:"+event+"] [source:" + source.getId() + "] [target:" + target.getId() + "]" ;
    }

    public SCXMLObject onEnter() {
        System.out.println("enter transition with event :  " + event);
        return null;

    }

    public SCXMLObject onExit(State targetParent) {
        System.out.println("exit transition with event :" + event);
        return null;
    }
}
