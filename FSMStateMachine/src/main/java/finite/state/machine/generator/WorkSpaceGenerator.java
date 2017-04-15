package finite.state.machine.generator;

import finite.state.machine.annotation.FSMEvent;
import finite.state.machine.workspace.MyWorkSpaceImpl;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by danial on 15/04/2017.
 */
public class WorkSpaceGenerator {

    private JavaClassSource javaClass;
    private StringBuilder stringBuilder;


    private static final String CLASSNAME = "WorkSpace";
    private static final String CLASSPACKAGE = "statemachine";
    private static final String PATH = "../statemachine/";

    public WorkSpaceGenerator(){
        javaClass = Roaster.create(JavaClassSource.class);
        javaClass.setName(CLASSNAME).setPublic().extendSuperType(MyWorkSpaceImpl.class);
        stringBuilder = new StringBuilder();
    }


    public void createMethods(List<String> eventList){
        for (int i= 0; i < eventList.size(); i++){
            addMethod(eventList.get(i));
        }
    }

    private void addMethod(String name){
        javaClass.addMethod().setReturnTypeVoid().setName(name).setPublic().setBody("")
                .addAnnotation(FSMEvent.class).setLiteralValue("event", "\""+name+"\"");
    }

    private void addMain(){
        javaClass.addMethod().setPublic().setReturnTypeVoid().setStatic(true).setName("main").setBody("")
                .addParameter(String[].class,"args");
    }

    public void build(){
        build(CLASSNAME);
    }


    public void setConstructor(String name){
        javaClass.addMethod().setConstructor(true).setPublic().setBody("start(this,new " + name + "());");
    }

    public void build(String name){
        javaClass.setName(name);
        addMain();
        try (FileOutputStream oS = new FileOutputStream(new File(  PATH + name+ ".java"))) {
            oS.write(javaClass.toString().getBytes());
            oS.getFD().sync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
