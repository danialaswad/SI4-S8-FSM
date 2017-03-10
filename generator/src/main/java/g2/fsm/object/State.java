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

    private String firstChild;

    // Map of id and children states
    private Map<String, State> childrens;

    // Map of events and the corresponding transitions
    private Map<String, List<Transition>> transitions;


    public State(String id){
        this.id = id;
        this.childrens = new HashMap<String, State>();
        this.transitions = new HashMap<String, List<Transition>>();
        this.parent = null;
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

    public String getFirstChild() {
        return firstChild;
    }

    public void setFirstChild(String firstChild) {
        this.firstChild = firstChild;
    }

    public Map<String, State> getChildrens() {
        return childrens;
    }

    public void setChildrens(State children) {
        this.childrens.put(children.getId(), children);
    }

    public List getTransitions(String event){
        if (transitions.containsKey(event))
            return transitions.get(event);
        else return null;
    }

    public void setTransitions(String event, Transition transition) {
        if (!transitions.containsKey(event)){
            ArrayList<Transition> transitionList = new ArrayList<Transition>();
            transitionList.add(transition);
            transitions.put(event, transitionList);
        }else {
            transitions.get(event).add(transition);
        }
    }

    public boolean equal(State s) {
        return s != null && this.getId().equals(s.getId());
    }

    public String toString() {
        if (parent!=null)
        return "State [id:"+id+"] [initial:"+firstChild+"] [parent:"+ parent.getId() +"]" + "[transition:" + transitions.toString() + "]";

        else
            return "State [id:"+id+"] [initial:"+firstChild+"] [parent:null] " + "[transition:" + transitions.toString() + "]" ;
    }

    public void onEnter() {
        System.out.println("enter state " + id);
        if (firstChild != null) getChildrens().get(firstChild).onEnter();
    }

    public void onExit(State targetParent) {
        System.out.println("exit state " + id);
        if (parent != null){
            if (!parent.equal(targetParent))
                parent.onExit(targetParent);
        }
    }
}
