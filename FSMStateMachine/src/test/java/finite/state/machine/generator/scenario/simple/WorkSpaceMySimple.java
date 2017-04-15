package finite.state.machine.generator.scenario.simple;

import finite.state.machine.workspace.MyWorkSpaceImpl;

/**
 * Created by danial on 15/04/2017.
 */
public class WorkSpaceMySimple extends MyWorkSpaceImpl {

    public WorkSpaceMySimple(){
        start(this,new MySimple());
    }
}
