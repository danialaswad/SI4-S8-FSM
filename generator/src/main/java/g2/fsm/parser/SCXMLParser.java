package g2.fsm.parser;

import g2.fsm.generator.StateGenerator;
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

    private static final String PATH = "resources/Source.scxml";
    private static final String INITIAL = "initial";
    private static final String ID = "id";
    private static final String STATE = "state";
    private static final String TRANSITION = "transition";
    private static final String TARGET = "target";
    private static final String EVENT = "event";

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

    private void parseState(Element element, Element parent){
        if (parent != null){
            generator.addState(element.getAttribute(ID),parent.getAttribute(ID));

        }else{
            generator.addState(element.getAttribute(ID));

        }


        Node childNode = element.getFirstChild();

        while( childNode.getNextSibling()!=null ){
            childNode = childNode.getNextSibling();
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) childNode;
                if (childElement.getNodeName().equals(STATE)){
                    parseState(childElement, element);
                }
                else if (childElement.getNodeName().equals(TRANSITION)){
                    generator.addTransition(childElement.getAttribute(EVENT), element.getAttribute(ID), childElement.getAttribute(TARGET));
                }
            }
        }

        if (element.hasAttribute(INITIAL)) generator.setFirstChild(element.getAttribute(ID), element.getAttribute(INITIAL));

    }

    public void parse(){
        String rootState = document.getDocumentElement().getAttribute(INITIAL);

        generator.setInitial(rootState);

        Element docEl = document.getDocumentElement();
        Node childNode = docEl.getFirstChild();
        while( childNode.getNextSibling()!=null ){
            childNode = childNode.getNextSibling();
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) childNode;
                parseState(childElement,null);
            }
        }

        generator.build();
    }
}
