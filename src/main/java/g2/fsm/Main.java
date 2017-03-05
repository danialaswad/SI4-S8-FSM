package g2.fsm;

import g2.fsm.parser.SCXMLParser;

/**
 * Created by danial on 03/03/17.
 */
public class Main {

    public static void main(String[] args){
        SCXMLParser scxml = new SCXMLParser();


        scxml.read("resources/test.scxml");
    }
}
