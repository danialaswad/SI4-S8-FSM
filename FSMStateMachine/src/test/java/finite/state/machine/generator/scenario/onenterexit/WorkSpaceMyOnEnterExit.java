package finite.state.machine.generator.scenario.onenterexit;

import finite.state.machine.annotation.FSMEvent;
import finite.state.machine.workspace.MyWorkSpaceImpl;

/**
 * Created by danial on 15/04/2017.
 */
public class WorkSpaceMyOnEnterExit extends MyWorkSpaceImpl{

    private boolean enter = false;
    private boolean exit = false;

    public WorkSpaceMyOnEnterExit(){
        start(this, new MyOnEnterExit());
    }



    @FSMEvent(event="action1")
    public void action1(){
        exit = true;
    }

    @FSMEvent(event = "action2")
    public void action2(){
        enter = true;
    }

    public boolean getEnter(){
        return enter;
    }

    public boolean getExit(){
        return exit;
    }
}
