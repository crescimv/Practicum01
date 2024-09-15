import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

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
                System.out.printf("\n%-10s%-10s%-40s%-9s", "ID", "Name", "Description", "Cost");
                System.out.println("\n=================================================================");

                int line = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    //rec = rec.replace(",", "");
                    String[] productInfo = rec.split(",\\s*");
                    if (productInfo.length == 4) {
                        System.out.printf("\n%-10s%-10s%-40s%-8.1f",
                                productInfo[0],
                                productInfo[1],
                                productInfo[2],
                                Double.parseDouble(productInfo[3]));
                        }
                    }
                    reader.close();
                    System.out.println("\n\nData file read!");

                } else {
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


