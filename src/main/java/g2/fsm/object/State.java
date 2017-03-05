package g2.fsm.object;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by danial on 03/03/17.
 */
public class State extends FSM{

    private Map<String, Transition> transitionMap;

    public State(String id){
        super(id);
        transitionMap = new HashMap<String, Transition>();
    }

    public void addTransition(String id, Transition transition){
        transitionMap.put(id,transition);
    }

    public String execute(String id){
        return transitionMap.get(id).execute();
    }

}
