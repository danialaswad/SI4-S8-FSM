package g2.fsm.object;

import g2.fsm.linker.SCXMLEventListener;

/**
 * Created by danial on 09/03/2017.
 */
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
}
