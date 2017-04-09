package rpccommands;

import utilities.OutputManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Kevin on 4/8/2017.
 */
public class RPCTail extends RPCCommand {
    private String remoteFile;
    private Socket socket;

    public RPCTail(String remoteFile) {
        setRemoteFile(remoteFile);
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

        output.write("tail," + remoteFile);
        output.newLine();
        output.flush();
    }

    private void setRemoteFile(String remoteFile) {
        this.remoteFile = remoteFile;
    }

    private void setSocket(Socket socket) {
        this.socket = socket;
    }
}
