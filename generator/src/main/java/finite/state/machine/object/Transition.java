package finite.state.machine.object;


public class Transition {

    // Event name of the transition
    private String event;

    private State target;

    private State source;

    private String send;

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

    public boolean hasSend(){
        return send.length() > 0;
    }
    public void setSend(String send){
        this.send = send;
    }

    public String getSend(){
        return send;
    }
    public String toString() {
        return "Transition [event:"+event+"] [source:" + source.getId() + "] [target:" + target.getId() + "]" ;
    }

}
