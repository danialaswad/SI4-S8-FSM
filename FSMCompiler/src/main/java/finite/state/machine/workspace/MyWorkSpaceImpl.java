package finite.state.machine.workspace;

import finite.state.machine.engine.StateMachineReader;
import finite.state.machine.parser.SCXMLParser;


public class MyWorkSpaceImpl implements MyWorkSpace {

    private StateMachineReader reader;

    public void start(MyWorkSpaceImpl workSpace){
        reader = new StateMachineReader(workSpace);
    }


    public void submitEvent(String e){
        reader.activate(e);
    }


}
