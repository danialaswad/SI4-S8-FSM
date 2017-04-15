package finite.state.machine.generator.scenario;

import finite.state.machine.engine.StateMachineReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class HierarchiqueScenarioTest {

    WorkSpaceMyHierarchique work;
    StateMachineReader reader;
    @Before
    public void setUp(){
        work = new WorkSpaceMyHierarchique();
        reader = work.reader();
    }

    @Test
    public void currentStateTest(){
        Assert.assertEquals("State3", reader.getCurrentState().getId());
    }

    @Test
    public void transition1Test(){
        work.submitEvent("e1");
        Assert.assertEquals("State4", reader.getCurrentState().getId());
        work.submitEvent("e1");
        Assert.assertEquals("State2", reader.getCurrentState().getId());
    }
}
