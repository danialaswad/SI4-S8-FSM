package g2.fsm;


import g2.fsm.machine.StateMachine;

/**
 * Created by danial on 03/03/17.
 */
public class Main {

    public static void main(String[] args){
        String[] event = {"e1", "e2", "e3", "e4", "e1", "e2", "e3", "e1", "e2"};
        StateMachine stateMachine = new StateMachine("resources/test.scxml");
        for (int i = 0; i < event.length ; i++) {
            stateMachine.execute(event[i]);
        }
    }
}
