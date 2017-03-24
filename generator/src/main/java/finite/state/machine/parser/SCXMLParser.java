package finite.state.machine.parser;

import finite.state.machine.generator.StateGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class SCXMLParser {

    private Document document;

    private StateGenerator generator;

    private static final String PATH = "resources/Simple.scxml";
    private static final String INITIAL = "initial";
    private static final String ID = "id";
    private static final String STATE = "state";
    private static final String TRANSITION = "transition";
    private static final String TARGET = "target";
    private static final String EVENT = "event";
    private static final String SEND = "send";

    public SCXMLParser(){
        generator = new StateGenerator();
        try {
            File input = new File(PATH);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(input);
            document.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(){
        String rootState = document.getDocumentElement().getAttribute(INITIAL);

        generator.setInitial(rootState);

        Element docEl = document.getDocumentElement();
        Node childNode = docEl.getFirstChild();
        while( childNode.getNextSibling()!=null ){
            childNode = childNode.getNextSibling();
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                parse(STATE, (Element) childNode);
            }
        }

        generator.build();
    }

    private void parse(String nodeName, Element element, Element ... args){
        switch (nodeName){
            case STATE :
                if (args.length > 0 )state(element, args[0]);
                else state(element); break;
            case TRANSITION : transition(element, args[0]); break;
            case SEND : send(element, args[0]); break;
            default:break;
        }
    }

    private void child(Element element){
        if(element.hasChildNodes()) {
            Node childNode = element.getFirstChild();
            while (childNode.getNextSibling() != null) {
                childNode = childNode.getNextSibling();
                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) childNode;
                    parse(childElement.getNodeName(), childElement, element);
                }
            }
        }
    }

    private void state(Element element){
        generator.addState(element.getAttribute(ID));
        child(element);
        if (element.hasAttribute(INITIAL)) generator.setFirstChild(element.getAttribute(ID), element.getAttribute(INITIAL));
    }

    private void state(Element element, Element parent){
        generator.addState(element.getAttribute(ID),parent.getAttribute(ID));
        child(element);
        if (element.hasAttribute(INITIAL)) generator.setFirstChild(element.getAttribute(ID), element.getAttribute(INITIAL));
    }

    private void transition(Element element, Element parent){
        generator.addTransition(element.getAttribute(EVENT), parent.getAttribute(ID), element.getAttribute(TARGET));
        child(element);
    }


    private void send(Element element, Element parent){

        generator.setSendEvent(parent.getAttribute(EVENT), element.getAttribute(EVENT));
    }

}