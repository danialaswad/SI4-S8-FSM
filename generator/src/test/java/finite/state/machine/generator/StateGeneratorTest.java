package finite.state.machine.generator;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StateGeneratorTest {

    StateGenerator generator;

    @Before
    public void setUp(){
        generator = new StateGenerator();
    }

    @Test
    public void initTest(){
        Assert.assertTrue(generator.getJavaClass().isPackagePrivate());
        Assert.assertEquals("Package name", "finite.state.engine.generator",generator.getJavaClass().getPackage());
        Assert.assertEquals("Class name", "StateMachineImpl",generator.getJavaClass().getName());
    }

    @Test
    public void addStateTest1(){
        generator.addState("TestState");
        Assert.assertTrue(generator.getJavaClass().hasProperty("TestState"));
        Assert.assertEquals("Add State with no parent", "this.TestState = new State(\"TestState\");",generator.getStateBuilder().toString());
    }

    @Test
    public void addStateTest2(){
        generator.addState("TestState","ParentState");
        Assert.assertTrue(generator.getJavaClass().hasProperty("TestState"));
        Assert.assertEquals("Add State with parent", "this.TestState = new State(\"TestState\",ParentState);",generator.getStateBuilder().toString());
    }

    @Test
    public void addTransitionTest(){
        generator.addTransition("event", "sourceState", "targetState");
        Assert.assertTrue(generator.getJavaClass().hasProperty("eventsourceState"));
        Assert.assertEquals("Add Transition", "this.eventsourceState = new Transition(\"event\",sourceState,targetState);", generator.getTransitionBuilder().toString());
    }

    @Test
    public void setFirstChild(){
        generator.setFirstChild("state","child");
        Assert.assertEquals("Set child to State", "this.state.setFirstChild(child);", generator.getStateBuilder().toString());
    }

    @Test
    public void setInitialTest(){
        generator.setInitial("first");
        Assert.assertTrue(generator.getJavaClass().hasMethodSignature("getInitial"));
        Assert.assertTrue(generator.getJavaClass().getMethod("getInitial").isPackagePrivate());
        Assert.assertEquals("getInitial body","return first;",generator.getJavaClass().getMethod("getInitial").getBody());
    }

}
