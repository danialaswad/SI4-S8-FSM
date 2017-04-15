package finite.state.machine.generator.scenario.simple;

import finite.state.machine.engine.StateMachineReader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by danial on 15/04/2017.
 */
public class SimpleScenarioTest {

    WorkSpaceMySimple work;
    StateMachineReader reader;


    @Before
    public void setUp(){
        work = new WorkSpaceMySimple();
        reader =work.reader();
    }

    @Test
    public void currentState(){
        Assert.assertEquals(reader.getCurrentState().getId(), "State1");
    }
    @Test
    public void transitionTest(){

        work.submitEvent("e1");
        Assert.assertEquals(reader.getCurrentState().getId(),"State2");
    }
}
