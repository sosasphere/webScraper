package so.sa;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class ExtractCodeFiles {
    public static void main(String[] args) {
        String repoPath = "./Cisco-Packet-Tracer"; // Change to your repo directory
        String outputFile = "Cisco-Packet-Tracer.txt";

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {
            Files.walk(Paths.get(repoPath))
                    .filter(Files::isRegularFile)
                    .forEach(file -> writeFileContent(writer, file));
            System.out.println("All code extracted into " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFileContent(BufferedWriter writer, Path file) {
        if (file.toString().endsWith(".txt")) return; // Skip the output file

        writerWrite(writer, "===== " + file.toString() + " =====\n");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.toFile()), Charset.forName("ISO-8859-1")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writerWrite(writer, line + "\n");
            }
            writerWrite(writer, "\n");
        } catch (IOException e) {
            System.err.println("Error reading " + file + ": " + e.getMessage());
        }
    }

    private static void writerWrite(BufferedWriter writer, String text) {
        try {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
