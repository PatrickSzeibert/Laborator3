// data/FileManager.java
package data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    // Salvare în fișier text
    public static void saveToTextFile(String filePath, List<String> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    // Încărcare din fișier text
    public static List<String> loadFromTextFile(String filePath) throws IOException {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        }
        return data;
    }
}
