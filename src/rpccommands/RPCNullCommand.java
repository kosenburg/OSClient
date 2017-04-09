package rpccommands;

import java.net.Socket;

/**
 * Created by Kevin on 4/8/2017.
 */
public class RPCNullCommand extends RPCCommand {

    @Override
    public void execute(Socket socket) {
        System.out.println("Command not found");
    }
}
