package finite.state.machine.object;

public class FSMObject {

    private String onExit;
    private String onEnter;

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

    public String getOnExit() {
        return onExit;
    }

    public void setOnExit(String onExit) {
        this.onExit = onExit;
    }

    public String getOnEnter() {
        return onEnter;
    }

    public void setOnEnter(String onEnter) {
        this.onEnter = onEnter;
    }
}
