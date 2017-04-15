package rpccommands;

import utilities.OutputManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Kevin on 4/15/2017.
 */
public class RPCList extends RPCCommand {
    private String directoryToList;
    private Socket socket;


    public RPCList(String directoryToList) {
        setDirectoryToList(directoryToList);
    }

    @Override
    public void execute(Socket socket) throws IOException {
        setSocket(socket);
        sendMessage();
        returnCommandOutput();
    }

    private void returnCommandOutput() throws IOException {
        OutputManager outputManager = new OutputManager();
        outputManager.display(socket.getInputStream());
    }

    private void sendMessage() throws IOException {
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        output.write("ls," + directoryToList);
        output.newLine();
        output.flush();
    }

    private void setDirectoryToList(String directoryToList) {
        this.directoryToList = directoryToList;
    }

    private void setSocket(Socket socket) {
        this.socket = socket;
    }
}
