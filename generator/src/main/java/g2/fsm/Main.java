package g2.fsm;


import g2.fsm.machine.StateMachineReader;
import g2.fsm.parser.SCXMLParser;


public class Main {

    public static void main(String[] args){

        SCXMLParser parse = new SCXMLParser();
        
        parse.parse();


        StateMachineReader reader = new StateMachineReader();

        reader.execute("e1");
        reader.execute("e1");
        reader.execute("e4");
    }
}
