package g2.fsm.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class State extends SCXMLObject{

    // State id is unique
    private String id;

    // Null if root state
    private State parent;

    // Null if no initial child
    private State firstChild;


    public State(String id){
        this.id = id;
        this.parent = null;
        this.firstChild = null;
    }

    public State(String id, State parent){
        this.id = id;
        this.parent = parent;
        this.firstChild = null;
    }

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


    public List<String> getAllParent(){
        List<String> l = new ArrayList<>();
        if (parent!=null) {
            l.add(parent.getId());
            l.addAll(parent.getAllParent());
        }
        return l;
    }
    public SCXMLObject onEnter() {
        System.out.println("enter state " + id);
        if (firstChild != null) {
            return firstChild.onEnter();
        }

        return this;
    }

    public SCXMLObject onExit(State target) {
        if (!target.getAllParent().contains(id) ) {
            System.out.println("exit state " + id);
            if (parent != null) {
                return parent.onExit(target);
            }
        }

        return this;
    }
}
