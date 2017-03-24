package finite.state.machine.workspace;

import finite.state.machine.engine.StateMachineReader;
import finite.state.machine.parser.SCXMLParser;

/**
 * Created by danial on 25/03/2017.
 */
public class MyWorkSpaceImpl implements MyWorkSpace {

    private  StateMachineReader reader;


    public MyWorkSpaceImpl(){
        SCXMLParser parse = new SCXMLParser();
        parse.parse();
    }
    protected void start(MyWorkSpaceImpl workSpace){
        reader = new StateMachineReader(workSpace);
    }

    protected void bind(){

    }

    protected void submitEvent(String e){
        reader.execute(e);
    }

    public void execute(){
    }
}
