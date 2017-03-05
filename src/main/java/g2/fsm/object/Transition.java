package g2.fsm.object;

import java.awt.*;

public class Transition extends FSM{

    private String target;
    private String event;

    public Transition(String id, String event, String target){
        super(id);
        this.event = event;
        this.target = target;
    }


    String execute(){
        return target;
    }
}
