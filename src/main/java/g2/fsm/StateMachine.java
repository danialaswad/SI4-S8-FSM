package g2.fsm;

import g2.fsm.object.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by danial on 03/03/17.
 */
public class StateMachine {

    private List<State> states;
    private Map<String,State> stateMap;

    public StateMachine(){
        states = new ArrayList<State>();
        stateMap = new HashMap<String, State>();
    }

    public void addState(String id, State state){
        states.add(state);
        stateMap.put(id, state);
    }

    public State execute(String id){
        return stateMap.get(id);
    }
}
