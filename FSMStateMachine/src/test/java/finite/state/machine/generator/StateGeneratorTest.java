package finite.state.machine.generator;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StateGeneratorTest {

    private StateGenerator generator;

    @Before
    public void setUp() {
        generator = new StateGenerator();
    }


    @Test
    public void initTest() {
        Assert.assertTrue(generator.getJavaClass().isPublic());
        Assert.assertEquals(generator.getJavaClass().getInterfaces().size(), 1);
    }

    @Test
    public void addStateTest1() {
        generator.addState("TestState");
        Assert.assertTrue(generator.getJavaClass().hasProperty("TestState"));
        Assert.assertEquals("Add State with no parent", "this.TestState = new State(\"TestState\");", generator.getStateBuilder().toString());
    }

    @Test
    public void addStateTest2() {
        generator.addState("TestState", "ParentState");
        Assert.assertTrue(generator.getJavaClass().hasProperty("TestState"));
        Assert.assertEquals("Add State with parent", "this.TestState = new State(\"TestState\",ParentState);", generator.getStateBuilder().toString());
    }

    @Test
    public void addExternalTransitionTest() {
        generator.addExternalTransition("event", "sourceState", "targetState");
        Assert.assertTrue(generator.getJavaClass().hasProperty("eventsourceState"));
        Assert.assertEquals("Add Transition", "this.eventsourceState = new Transition(\"event\",sourceState,targetState);", generator.getTransitionBuilder().toString());
    }

    @Test
    public void addInternalTransitionTest() {
        generator.addInternalTransition("event", "sourceState2");
        Assert.assertTrue(generator.getJavaClass().hasProperty("eventsourceState2"));
        Assert.assertEquals("Add Transition", "this.eventsourceState2 = new Transition(\"event\",sourceState2);", generator.getTransitionBuilder().toString());
    }

    @Test
    public void setFirstChild() {
        generator.setFirstChild("state", "child");
        Assert.assertEquals("Set child to State", "this.state.setFirstChild(child);", generator.getStateBuilder().toString());
    }

    @Test
    public void setInitialTest() {
        generator.setInitial("first");
        Assert.assertTrue(generator.getJavaClass().hasMethodSignature("getInitial"));
        Assert.assertTrue(generator.getJavaClass().getMethod("getInitial").isPublic());
        Assert.assertEquals("getInitial body", "return first;", generator.getJavaClass().getMethod("getInitial").getBody());
    }

}
