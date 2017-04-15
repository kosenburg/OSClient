package rpccommands;

import utilities.OutputManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Kevin on 4/15/2017.
 */
public class RPCDelete extends RPCCommand {
    private String remoteFile;
    private String localFile;
    private Socket socket;

    public RPCDelete(String remoteFile, String localFile) {
        setRemoteFile(remoteFile);
    }

    @Override
    public void execute(Socket socket) throws IOException {
        try {
            setSocket(socket);
            sendMessage();
            outputResults();
        } finally {
            closeConnections();
        }
    }

    private void closeConnections() throws IOException {
        socket.close();
    }

    private void sendMessage() throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("delete," + remoteFile);
        writer.newLine();
        writer.flush();
    }

    private void setRemoteFile(String remoteFile) {
        this.remoteFile = remoteFile;
    }

    private void setSocket(Socket socket) {
        this.socket = socket;
    }

    private void outputResults() throws IOException {
        OutputManager outputManager = new OutputManager();
        outputManager.display(socket.getInputStream());
    }
}