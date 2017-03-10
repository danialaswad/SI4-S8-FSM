package g2.fsm.builder;

import g2.fsm.object.State;
import g2.fsm.object.Transition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class CodeBuilder {


    private Document document;

    // temporary state holder
    // joinedStates contains states that has been joined to the state tree
    private Map<String, State> joinedStates = new HashMap<String, State>();
    // holds untarget transitions until target is created
    private Map<String, List<Transition>> untargetedTransitions = new HashMap<String, List<Transition>>();

    private Map<String, Transition> transitionMap = new HashMap<String, Transition>();

    private StringBuilder sourceBuilder = new StringBuilder();
    private StringBuilder reflectBuilder = new StringBuilder();

    private String rootState;

    // Common string used
    private static final String PATH = "resources/Source.scxml";
    private static final String ID = "id";
    private static final String INITIAL = "initial";
    private static final String STATE = "state";
    private static final String TRANSITION = "transition";
    private static final String TARGET = "target";
    private static final String EVENT = "event";

    private static final String REFLECT = "StateReflect";
    private static final String MACHINE = "StateMachine";
    private static final String REFLECTOBJECT = "stateReflect";

    private static final String PACKAGE = "fsm.workspace";



    /**
     * Parse the xml file
     */
    public CodeBuilder(){
        try {
            File input = new File(PATH);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(input);
            document.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        sourceBuilder.append("package "+PACKAGE+";\n\n")
                        .append("import java.util.*;\n\n")
                        .append("public class " + REFLECT+ " {\n\n");

    }

    /**
     * Create a state if the object is not found in the tmp map
     * if state is in the tmp map, retrieve the object, delete the object in tmp and stores it in
     * joinedStates
     * @param id state id
     * @return state
     */
    private State createState(String id){
        State state = new State(id);
        joinedStates.put(id,state);

        if (untargetedTransitions.containsKey(id)){
            for (int i = 0; i < untargetedTransitions.get(id).size(); i++)
                untargetedTransitions.get(id).get(i).setTarget(state);
        }
        untargetedTransitions.remove(id);
        return state;
    }

    /**
     *
     * @param target
     * @param transition
     */
    private void insertUntargetedTransition(String target, Transition transition){
        if (untargetedTransitions.containsKey(target)){
            untargetedTransitions.get(target).add(transition);
        }
        else {
            ArrayList<Transition> list = new ArrayList<Transition>();
            list.add(transition);
            untargetedTransitions.put(target, list);
        }
    }

    /**
     *
     * @param element
     * @return
     */
    private Transition createTransition(Element element){

        Transition t = new Transition(element.getAttribute(EVENT));
        if (joinedStates.containsKey(element.getAttribute(TARGET))){
            t.setTarget(joinedStates.get(element.getAttribute(TARGET)));
        }else {
            insertUntargetedTransition(element.getAttribute(TARGET),t);
        }

        return t;
    }


    /**
     * Creates a hierarchical states
     * @param element xml element
     * @param parent parent to the state to be created
     * @return state
     */
    private State createStates(Element element, State parent){
        State state = createState(element.getAttribute(ID));

        if (element.hasAttribute(INITIAL)){
            state.setFirstChild(element.getAttribute(INITIAL));
        }
        if (parent != null){
            state.setParent(parent);
        }

        Node childNode = element.getFirstChild();
        while( childNode.getNextSibling()!=null ){
            childNode = childNode.getNextSibling();
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) childNode;

                if (childElement.getNodeName().equals(STATE)){

                    state.setChildrens(createStates(childElement,state));
                }
                else if (childElement.getNodeName().equals(TRANSITION)){
                    Transition t =  createTransition(childElement);
                    state.setTransitions(childElement.getAttribute(EVENT), t);
                    appendTransitionMethod(state, t);
                }

            }
        }

        return state;
    }

    public void appendTransitionMethod(State state, Transition transition){

        sourceBuilder.append("\tpublic void " + state.getId()+ "_" + transition.getEvent() + "(){\n" )
                .append("\t\tSystem.out.println(\"Hello from event :" + transition.getEvent() +" \");\n")
                .append("\t}\n\n");

    }

    public void buildReflect(){

        // Class header and instances
        reflectBuilder.append("package "+PACKAGE+";\n\n")
                .append("import java.util.*;\n\n")
                .append("import java.lang.reflect.*;\n\n")
                .append("public class " + MACHINE+" {\n\n")
                .append("\tprivate " + REFLECT +" " + REFLECTOBJECT+ " = new " + REFLECT+ "();\n\n")
                .append("\tprivate Class reflectClass;\n\n")
                .append("\tprivate Map<String,Class[]> methodMap = new HashMap<String,Class[]>();\n\n");



        // Constructor
        reflectBuilder.append("\tpublic " + MACHINE +"(){\n\n")
                .append("\t\treflectClass = " + REFLECTOBJECT + ".getClass();\n")
                .append("\t\tMethod[] methods = reflectClass.getDeclaredMethods(); \n")
                .append("\t\tfor (Method method : methods){ \n")
                .append("\t\t\t methodMap.put(method.getName(),method.getParameterTypes());\n\t\t}\n")
                .append("\t}\n\n");

        // public method
        reflectBuilder.append("\tprivate void switchState(String state, String transition){\n")
                .append("\t\tString methodName = state + \"_\" + transition;\n")
                .append("\t\tMethod method = null;\n")
                .append("\t\ttry{\n")
                .append("\t\t\tmethod = reflectClass.getMethod(methodName,methodMap.get(methodName));\n")
                .append("\t\t\tmethod.invoke(" + REFLECTOBJECT+",null);\n")
                .append("\t\t}catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {\n")
                .append("\t\t\te.printStackTrace();\n")
                .append("\t\t}\n")
                .append("\t}\n");


    }


    public void fillWithStates(){
        reflectBuilder.append("\tprivate String[] states= { ");
        for (State s : joinedStates.values()) {
            reflectBuilder.append(" \""+s.getId()+"\", ");
        }
        reflectBuilder.append("};\n\n");


        reflectBuilder.append("\tString currentState = \"" + rootState+"\";\n\n");



        reflectBuilder.append("\tpublic void connectTo(){\n")
                .append("\t}\n\n");


        reflectBuilder.append("\tpublic void switchState(String Transition){\n\n")
                .append("\t}\n");

        reflectBuilder.append("\tpublic static void main(String[] args){\n")
                .append("")
                .append("\t}\n");


    }
    /**
     * creates a network of states from the scxml file given
     * @return initial state of the network
     */
    public void build(){
        rootState = document.getDocumentElement().getAttribute(INITIAL);

        buildReflect();
        Element docEl = document.getDocumentElement();
        Node childNode = docEl.getFirstChild();
        while( childNode.getNextSibling()!=null ){
            childNode = childNode.getNextSibling();
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) childNode;
                createStates(childElement,null);
            }
        }


        sourceBuilder.append("}\n\n");
        fillWithStates();
        reflectBuilder.append("}\n\n");
        System.out.println(sourceBuilder.toString());
        System.out.println(reflectBuilder.toString());


        try (FileOutputStream oS = new FileOutputStream(new File("fsm/src/main/java/fsm/workspace/" + REFLECT+".java"))) {
            oS.write(sourceBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream oS = new FileOutputStream(new File("fsm/src/main/java/fsm/workspace/" + MACHINE+".java"))) {
            oS.write(reflectBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
