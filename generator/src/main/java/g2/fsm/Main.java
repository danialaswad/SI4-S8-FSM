package g2.fsm;


import g2.fsm.machine.StateMachine;
import g2.fsm.builder.CodeBuilder;
import g2.fsm.object.State;

/**
 * Created by danial on 03/03/17.
 */
public class Main {

    public static void main(String[] args){
        String[] event = {"e1","e1","e1","e2","e2","e1","e2","e4","e1"};
        //String[] event ={"e4", "e6"};
        CodeBuilder smBuilder = new CodeBuilder();
        smBuilder.build();

        /*StateMachine stateMachine = new StateMachine(state);
        for (int i = 0; i < event.length ; i++) {
            stateMachine.execute(event[i]);
        }*/
    }
}
