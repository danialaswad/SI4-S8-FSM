package finite.state.machine.workspace;

import finite.state.machine.engine.StateMachineReader;
import finite.state.machine.generator.StateMachine;


public class MyWorkSpaceImpl implements MyWorkSpace {

    private StateMachineReader reader;

    public void start(MyWorkSpaceImpl workSpace, StateMachine stateMachine){
        reader = new StateMachineReader(workSpace, stateMachine);
    }


    public void submitEvent(String e){
        reader.activate(e);
    }

    /*
      test purposes
     */

    public StateMachineReader reader(){
        return reader;
    }
}
