package finite.state.machine.generator;

import finite.state.machine.object.FinalState;
import finite.state.machine.object.State;
import finite.state.machine.object.Transition;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StateGenerator {


    private final JavaClassSource javaClass;

    private StringBuilder stateBuilder;
    private StringBuilder transitionBuilder;

    private static final String CLASSNAME = "StateMachineImpl";
    private static final String CLASSPACKAGE = "finite.state.machine.engine";
    private static final String PATH = "../FSMCompiler/src/main/java/finite/state/machine/engine/";

    private Map<String,String> eventMap;

    public StateGenerator(){
        javaClass = Roaster.create(JavaClassSource.class);
        javaClass.setPackage(CLASSPACKAGE).setName(CLASSNAME).setPackagePrivate().addInterface(StateMachine.class);
        stateBuilder = new StringBuilder();
        transitionBuilder = new StringBuilder();
        eventMap = new HashMap<>();
    }


    public void addState(String id){
        javaClass.addProperty(State.class, id).setMutable(false).setAccessible(false);
        stateBuilder.append("this.").append(id).append(" = new State(\"").append(id).append("\");");
    }

    public void addState(String id,String parent){
        javaClass.addProperty(State.class, id).setMutable(false).setAccessible(false);
        stateBuilder.append("this.").append(id).append(" = new State(\"").append(id).append("\",").append(parent).append(");");
    }


    public void addExternalTransition(String event, String source, String target){
        javaClass.addProperty(Transition.class, event+source).setMutable(false);
        eventMap.put(event,event+source);
        transitionBuilder.append("this.").append(event).append(source).append(" = new Transition(\"").append(event).append("\",").append(source).append(",").append(target).append(");");
    }

    public void addInternalTransition(String event, String source){
        javaClass.addProperty(Transition.class, event+source).setMutable(false);
        eventMap.put(event,event+source);
        transitionBuilder.append("this.").append(event).append(source).append(" = new Transition(\"").append(event).append("\",").append(source).append(");");
    }

    public void setEvent(String event, String send, String type){
        transitionBuilder.append("this.").append(eventMap.get(event)).append(".setEvent(\"").append(send).append("\",\"").append(type).append("\");");
    }


    public void addOnEntery(String source, String event, String type){
        stateBuilder.append("this.").append(source).append(".setOnEnter(\"").append(event).append("\", \"").append(type).append("\");");
    }

    public void addOnExit(String source, String event, String type){
        stateBuilder.append("this.").append(source).append(".setOnExit(\"").append(event).append("\",\"").append(type).append("\");");
    }

    public void addFinal(String id){
        javaClass.addProperty(FinalState.class, id).setMutable(false).setAccessible(false);
        stateBuilder.append("this.").append(id).append(" = new FinalState(\"").append(id).append("\");");
    }

    public void setInitial(String initial){
        javaClass.addMethod().setReturnType(State.class).setName("getInitial").setPublic().setBody("return " + initial + ";");
    }

    public void setFirstChild(String state, String child){
        stateBuilder.append("this.").append(state).append(".setFirstChild(").append(child).append(");");
    }

    public void build(){
        javaClass.addMethod().setConstructor(true).setPackagePrivate().setBody(stateBuilder.toString() + transitionBuilder.toString());
        try (FileOutputStream oS = new FileOutputStream(new File(  PATH + CLASSNAME+".java"))) {
            oS.write(javaClass.toString().getBytes());
            oS.getFD().sync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
            Getter for test purposes
     */

    JavaClassSource getJavaClass() {
        return javaClass;
    }

    StringBuilder getStateBuilder() {
        return stateBuilder;
    }


    StringBuilder getTransitionBuilder() {
        return transitionBuilder;
    }

}
