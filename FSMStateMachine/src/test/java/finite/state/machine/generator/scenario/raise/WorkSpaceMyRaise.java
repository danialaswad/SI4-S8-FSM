package finite.state.machine.generator.scenario.raise;

import finite.state.machine.annotation.FSMEvent;
import finite.state.machine.workspace.MyWorkSpaceImpl;

/**
 * Created by danial on 15/04/2017.
 */
public class WorkSpaceMyRaise extends MyWorkSpaceImpl {
    private int number = 0;

    @FSMEvent(event="action1")
    public void action(){
        number = 1;
    }

    public int getNumber(){
        return number;
    }
    public WorkSpaceMyRaise() {
        start(this, new MyRaise());
    }
}
