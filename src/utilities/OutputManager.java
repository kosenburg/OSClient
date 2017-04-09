package utilities;

import java.io.*;

/**
 * Created by Kevin on 4/8/2017.
 */
public class OutputManager {
    public OutputManager() {

    }

    public void writeTo(String fileLocation, InputStream input) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            if (!isError(reader.readLine())) {
                BufferedWriter output = new BufferedWriter(new FileWriter(fileLocation));
                String line;
                while ((line = reader.readLine()) != null) {
                    output.write(line);
                    output.newLine();
                }
                output.close();
            }
        } catch (IOException e) {
            System.err.println("Issue writing the output to file " + fileLocation + " due to: " + e.getMessage());
        }
    }

    private static boolean isError(String s) {
        if (s.equals("Command executing.")) {
            System.out.println("Message from server: " +  s);
            return false;
        } else {
            System.out.println("An error occurred on the server side.");
            System.out.println("Message from server: " + s);
            return true;
        }
    }

    public void display(InputStream input) {

    }

    public void appendTo(String fileLocation, InputStream input) {

    }

}
