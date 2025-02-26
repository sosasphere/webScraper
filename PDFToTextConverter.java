package so.sa;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PDFToTextConverter {
    public static void main(String[] args) {
        String pdfPath = "CCNA 200-301 Hands-on.pdf";  // Change this to your PDF file
        String txtPath = "output.txt";

        try (PDDocument document = PDDocument.load(new File(pdfPath));
             FileWriter writer = new FileWriter(txtPath)) {

            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setSortByPosition(true); // Ensures text is extracted in reading order
            String text = pdfStripper.getText(document);

            writer.write(text);
            System.out.println("Text extraction completed: " + txtPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
