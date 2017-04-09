package rpccommands;

import utilities.OutputManager;

import java.io.*;
import java.net.Socket;

/**
 * Created by Kevin on 4/8/2017.
 */
public class RPCCopy extends RPCCommand {
    private String remoteFile;
    private String localFile;
    private Socket socket;

    public RPCCopy(String remoteFile, String localFile) {
        setRemoteFile(remoteFile);
        setLocalFile(localFile);
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
        writer.write("copy," + remoteFile);
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
        outputManager.writeTo(localFile, socket.getInputStream());
    }

    private void setLocalFile(String localFile) {
        this.localFile = localFile;
    }
}
