package g2.fsm;


import g2.fsm.machine.StateMachine;
import g2.fsm.machine.StateMachineBuilder;
import g2.fsm.object.State;

/**
 * Created by danial on 03/03/17.
 */
public class Main {

    public static void main(String[] args){
        String[] event = {"e1","e1","e1","e2","e2","e3","e3","e2","e2"};

        StateMachineBuilder smBuilder = new StateMachineBuilder("resources/test.scxml");
        State state = smBuilder.build();

        StateMachine stateMachine = new StateMachine(state);
        for (int i = 0; i < event.length ; i++) {

            System.out.println(event[i]);
            stateMachine.execute(event[i]);
        }
    }
}
