package finite.state.machine.generator.scenario.onenterexit;

import finite.state.machine.engine.StateMachineReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by danial on 15/04/2017.
 */
public class OnEnterExitScenarioTest {

    WorkSpaceMyOnEnterExit work;
    StateMachineReader reader;


    @Before
    public void setUp(){
        work = new WorkSpaceMyOnEnterExit();
        reader = work.reader();
    }


    @Test
    public void currentState(){
        Assert.assertEquals("State1", reader.getCurrentState().getId());
    }

    @Test
    public void onEnterExitTest(){
        Assert.assertFalse(work.getEnter());
        Assert.assertFalse(work.getExit());

        work.submitEvent("e1");
        Assert.assertEquals("State2", reader.getCurrentState().getId());
        Assert.assertTrue(work.getEnter());
        Assert.assertTrue(work.getExit());

    }
}
