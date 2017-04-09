package rpccommands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by Kevin on 4/8/2017.
 */
public abstract class RPCCommand {
    private static CommandLine commandLineOptions;

    public abstract void execute(Socket socket) throws IOException;

    public static RPCCommand getCommand(CommandLine options) {
        commandLineOptions = options;

        String command = getOptionValue("command");
        if (command.equals("copy")) {
            return new RPCCopy(getOptionValue("file"), getOptionValue("fileTo"));
        } else if (command.equals("tail")) {
            return new RPCTail(getOptionValue("file"));
        } else {
            return new RPCNullCommand();
        }

    }

    private static String getOptionValue(String option) {
        String commandOption = commandLineOptions.getOptionValue(option);

        if (commandOption != null) {
            return commandOption;
        } else {
            throw new IllegalArgumentException();
        }
    }


}
