package g2.fsm.generator;

import g2.fsm.object.State;
import g2.fsm.object.Transition;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by danial on 15/03/2017.
 */
public class StateGenerator {


    private final JavaClassSource javaClass;

    private StringBuilder stateBuilder;
    private StringBuilder transitionBuilder;

    private static final String CLASSNAME = "StateMachine";

    private static final String CLASSPACKAGE = "g2.fsm.machine";



    public StateGenerator(){
        javaClass = Roaster.create(JavaClassSource.class);
        javaClass.setPackage(CLASSPACKAGE).setName(CLASSNAME);
        stateBuilder = new StringBuilder();
        transitionBuilder = new StringBuilder();
    }


    public void addState(String id){
        javaClass.addProperty(State.class, id);
        stateBuilder.append("this.").append(id).append(" = new State(\"").append(id).append("\");");

    }

    public void addState(String id,String parent){
        javaClass.addProperty(State.class, id);
        stateBuilder.append("this.").append(id).append(" = new State(\"").append(id).append("\",").append(parent).append(");");
    }


    public void addTransition(String event,String source, String target){
        javaClass.addProperty(Transition.class, event+source);
        transitionBuilder.append("this.").append(event).append(source).append(" = new Transition(\"").append(event).append("\",").append(source).append(",").append(target).append(");");
    }


    public void setInitial(String initial){
        javaClass.addMethod().setReturnType(State.class).setName("getInitial").setPublic().setBody("return " + initial + ";");
    }

    public void setFirstChild(String state, String child){
        stateBuilder.append("this.").append(state).append(".setFirstChild(").append(child).append(");");
    }

    public void build(){
        javaClass.addMethod().setConstructor(true).setPublic().setBody(stateBuilder.toString() + transitionBuilder.toString());
        try (FileOutputStream oS = new FileOutputStream(new File("generator/src/main/java/g2/fsm/machine/" + CLASSNAME+".java"))) {
            oS.write(javaClass.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
