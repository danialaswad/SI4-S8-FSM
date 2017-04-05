package finite.state.machine.object;

import java.util.ArrayList;
import java.util.List;


public class State extends FSMObject {

    // State id is unique
    private String id;

    // Null if root state
    private State parent;

    // Null if no initial child
    private State firstChild;

    public State(String id){
        super();
        this.id = id;
        parent = null;
        firstChild = null;
    }

    public State(String id, State parent){
        super();
        this.id = id;
        this.parent = parent;
        this.firstChild = null;
    }

    /**
     * Get all the parents of the current state
     * @return
     */
    public List<String> getAllParent(){
        List<String> l = new ArrayList<>();
        if (parent!=null) {
            l.add(parent.getId());
            l.addAll(parent.getAllParent());
        }
        return l;
    }

    /*
     Getter and Setter
     */

    public String getId() {
        return id;
    }

    public State getParent() {
        return parent;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public State getFirstChild() {
        return firstChild;
    }

    public void setFirstChild(State firstChild) {
        this.firstChild = firstChild;
    }

    public boolean equal(State s) {
        return s != null && this.getId().equals(s.getId());
    }

    public String toString() {
        return "";
    }

}
