package finite.state.machine;

import finite.state.machine.parser.SCXMLParser;


public class Main {

    public static void main(String[] args){
        if (args.length != 1){
            System.out.println("[Usage] arg(path to scxml file)");
            System.exit(0);
        }

        String path = args[0];
        System.out.println("Parsing " + path);
        SCXMLParser parser = new SCXMLParser(path);

        parser.parse();
        System.out.println("done");
    }
}
