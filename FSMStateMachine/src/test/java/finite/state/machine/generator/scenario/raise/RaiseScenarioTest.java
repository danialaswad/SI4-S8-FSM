package finite.state.machine.generator.scenario.raise;

import finite.state.machine.engine.StateMachineReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by danial on 15/04/2017.
 */
public class RaiseScenarioTest {

    WorkSpaceMyRaise work;
    StateMachineReader reader;


    @Before
    public void setUp(){
        work = new WorkSpaceMyRaise();
        reader = work.reader();
    }

    @Test
    public void currentStateTest(){
        Assert.assertEquals(reader.getCurrentState().getId(), "State1");
    }

    @Test
    public void transitionTest(){
        Assert.assertEquals(work.getNumber(), 0);
        work.submitEvent("in1");
        Assert.assertEquals(reader.getCurrentState().getId(), "State1");
        Assert.assertEquals(work.getNumber(),1);
    }

}
