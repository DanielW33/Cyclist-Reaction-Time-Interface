package PSU.Group2.Util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IO {
    private BufferedWriter writer;
    private BufferedReader reader;

    public BufferedWriter setupWriter(String FilePath, boolean append)
            throws FileNotFoundException {      //This will set up a file writer

        FileOutputStream Fwriter = new FileOutputStream(FilePath, append);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Fwriter, StandardCharsets.UTF_8);

        writer = new BufferedWriter(outputStreamWriter);
        return writer;
    }

    public void write(String vals) throws IOException {
        writer.append(vals);                    //This writes the info to it's respective file associated with the writer.
        writer.newLine();
    }

    public BufferedReader setupReader(String FilePath)
            throws FileNotFoundException {

        FileReader FReader = new FileReader(FilePath);
        reader = new BufferedReader(FReader);

        return reader;
    }

    public String getEnvironment(String Environment) { //Environment "Stationary Bicycle", "Road Test"
        if (Environment.equals("Stationary Bicycle")) {
            return "1";
        }
        return "0";
    }
    public String RevertEnvironment(String Environment){ //Changes is back to user friendly form.
        if (Environment.equals("1")) {
            return "Stationary Bicycle";
        }
        return "Road Test";
    }
    public void ERRORED(Exception e) throws IOException {
        IO setup = new IO();
        BufferedWriter writer = setup.setupWriter("RunInstructions.txt",true);
        setup.write(e.toString());
    }
}
