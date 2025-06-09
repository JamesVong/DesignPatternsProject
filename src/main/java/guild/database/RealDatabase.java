package guild.database;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class RealDatabase implements Database {
    private final String filePath;

    public RealDatabase(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> readData() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading from " + filePath + ": " + e.getMessage());
        }
        return lines;
    }

    @Override
    public void writeData(List<String> data) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            for (String entry : data) {
                writer.write(entry);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to " + filePath + ": " + e.getMessage());
        }
    }
}
