package finite.state.machine.object;


public class Transition extends FSMObject{

    // Event name of the transition
    private String event;

    // Target state or destination state
    private State target;

    // State from where the transition starts
    private State source;

    // Send method
    private String send;

    // Transition type (INTERNAL, EXTERNAL)
    private TransitionType type;

    public Transition(String event, State source, State target){
        super();
        this.source = source;
        this.target = target;
        this.event = event;
        type = TransitionType.EXTERNAL;
    }

    public Transition(String event, State source){
        super();
        this.source = source;
        this.event = event;
        type = TransitionType.INTERNAL;
    }


    /**
     * Verify the existance of send instance
     * @return
     */
    public boolean hasSend(){
        return send!= null;
    }


    /*
     Getter and Setter
     */

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

    public void setSend(String send){
        this.send = send;
    }

    public String getSend(){
        return send;
    }

    public TransitionType getType(){
        return type;
    }

    public String toString() {
        return "Transition [event:"+event+"] [source:" + source.getId() + "] [target:" + target.getId() + "]" ;
    }

}
