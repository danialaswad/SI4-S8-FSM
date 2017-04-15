package finite.state.machine.object;

import java.io.Serializable;

public class FSMObject implements Serializable {

    private FSMEvents onExit;
    private FSMEvents onEnter;

    public FSMObject(){

    }


    /*
        Instance declaration verifier
     */
    public boolean hasOnEnter(){
        return onEnter != null;
    }

    public boolean hasOnExit(){
        return onExit != null;
    }

    /*
        Getter and Setter
     */

    public FSMEvents getOnExit() {
        return onExit;
    }

    public void setOnExit(String event, String type) {
        this.onExit = new FSMEvents(event,type);
    }

    public FSMEvents getOnEnter() {
        return onEnter;
    }

    public void setOnEnter(String event, String type) {
        this.onEnter = new FSMEvents(event,type);
    }
}
