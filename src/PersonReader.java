import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int yob = 0;

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                System.out.printf("\n%-10s%-15s%-15s%-6s%-6s", "ID", "First Name", "Last Name", "Title", "YOB");
                System.out.println("\n==================================================");

                int line = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    rec = rec.replace(",", "");
                    String[] personInfo = rec.split("\\s+");
                    if (personInfo.length == 5) {
                        System.out.printf("\n%-10s%-15s%-15s%-6s%-6d",
                                personInfo[0],
                                personInfo[1],
                                personInfo[2],
                                personInfo[3],
                                Integer.parseInt(personInfo[4]));
                    }

                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");


            } else  // User closed the chooser without selecting a file
            {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
