import org.apache.commons.cli.*;
import rpccommands.RPCCommand;

import java.io.IOException;
import java.net.Socket;

public class Main {
    private static Options commandOptions;
    private static CommandLineParser cmdParser;
    private static HelpFormatter helpList;
    private static CommandLine commandLine = null;
    private static final String allowedCommands = "copy,tail,append,shutdown";

    private static int portNumber = 60010;
    private static String serverIP = "127.0.0.1";


    public static void main(String[] args) {
        createCommandLineObjects();
        setCommandOptions();
        parseCommandArguments(args);
        checkForHelpRequest();


        RPCCommand command = RPCCommand.getCommand(commandLine);
        executeCommand(command);
    }

    private static void executeCommand(RPCCommand command) {
        try {
            command.execute(new Socket(serverIP,portNumber));
        } catch (IOException e) {
            System.err.println("An error occurred executing your command due to: " + e.getMessage());
        }
    }

    private static void createCommandLineObjects() {
        commandOptions = new Options();
        cmdParser = new DefaultParser();
        helpList = new HelpFormatter();
    }

    private static void setCommandOptions() {
        Option option = new Option("c", "command",true,"Remote command you would like executed " + allowedCommands);
        option.setRequired(true);
        commandOptions.addOption(option);

        option = new Option("f", "file", true, "The file you want to execute the command on");
        commandOptions.addOption(option);

        option = new Option("to", "fileTo", true, "The file you want to return results to");
        commandOptions.addOption(option);
    }

    private static void parseCommandArguments(String[] args) {
        try {
            commandLine = cmdParser.parse(commandOptions,args);
        } catch (ParseException e) {
            System.out.println("Invalid or missing argument. Displaying options..");
            helpList.printHelp("LogClient", commandOptions);
            System.exit(1);
        }
    }

    private static void checkForHelpRequest() {
        if (commandLine.hasOption("help")) {
            helpList.printHelp("LogClient", commandOptions);
            System.exit(0);
        }
    }
}
