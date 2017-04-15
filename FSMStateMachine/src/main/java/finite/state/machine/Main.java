package finite.state.machine;

import finite.state.machine.parser.SCXMLParser;
import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args){

        Options options = new Options();

        Option input = new Option("f", "input", true, "input file path");
        input.setRequired(true);
        options.addOption(input);

        Option name = new Option("n", "name", true, "file name");
        name.setRequired(false);
        options.addOption(name);

        Option work = new Option("w", "work", true, "workspace");
        work.setRequired(false);
        options.addOption(work);



        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
            return;
        }

        SCXMLParser scxmlParser = new SCXMLParser(cmd.getOptionValue("input"));
        scxmlParser.parse();

        if (cmd.hasOption("name")){
            scxmlParser.generator().build(cmd.getOptionValue("name"));
        } else {
            scxmlParser.generator().build();
        }

        String smName = scxmlParser.generator().getJavaClass().getName();

        if (cmd.hasOption("work") && cmd.getOptionValue("work").equals("t")){
            scxmlParser.wsgenerator().setConstructor(smName);
            scxmlParser.wsgenerator().build();

        }

        System.out.println("done");
    }
}
