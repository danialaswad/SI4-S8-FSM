package finite.state.machine.object;


public class Transition extends FSMObject{

    // Event name of the transition
    private String id;

    // Target state or destination state
    private State target;

    // State from where the transition starts
    private State source;

    // Send method
    private FSMEvents event;

    // Transition type (INTERNAL, EXTERNAL)
    private TransitionType type;

    public Transition(String id, State source, State target){
        super();
        this.source = source;
        this.target = target;
        this.id = id;
        type = TransitionType.EXTERNAL;
    }

    public Transition(String id, State source){
        super();
        this.source = source;
        this.id = id;
        type = TransitionType.INTERNAL;
    }


    /**
     * Verify the existance of send instance
     * @return
     */
    public boolean hasEvent(){
        return this.event != null;
    }


    /*
     Getter and Setter
     */

    public String getId(){
        return id;
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

    public void setEvent(String event, String type){
        this.event = new FSMEvents(event, type);
    }

    public FSMEvents getEvent(){
        return event;
    }

    public TransitionType getType(){
        return type;
    }

    public String toString() {
        return "Transition [id:"+ id +"] [source:" + source.getId() + "] [target:" + target.getId() + "]" ;
    }

}
