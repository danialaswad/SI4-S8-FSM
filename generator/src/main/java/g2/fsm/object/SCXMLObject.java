package g2.fsm.object;


public abstract class SCXMLObject {

    public abstract String toString();

    public abstract SCXMLObject onEnter();

    public abstract SCXMLObject onExit(State target);
}
