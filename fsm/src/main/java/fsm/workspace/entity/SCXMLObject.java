package fsm.workspace.entity;


public abstract class SCXMLObject {

    public abstract String toString();

    public abstract void onEnter();

    public abstract void onExit(State targetParent);
}
