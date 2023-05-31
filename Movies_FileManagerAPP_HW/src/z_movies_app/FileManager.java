package z_movies_app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;






public class FileManager {
	
	public static void CsvReader() {
		
		String columnLine = " | ";
		
		try {
			 BufferedReader readInFile = new BufferedReader(new FileReader("m_data/movies.csv"));
			 String stringLine = readInFile.readLine();
			 stringLine = readInFile.readLine();
			 
			 while(stringLine != null ) {
				 	String[] stringArray = stringLine.split(";");
				 	System.out.println(stringArray[0]+columnLine+stringArray[1]+columnLine+stringArray[2]+columnLine+stringArray[3]+columnLine+stringArray[4]+columnLine+stringArray[5]+columnLine+stringArray[6]+columnLine+stringArray[7]);
				 	stringLine=readInFile.readLine();				 	
			 }			 
			 readInFile.close();
			 
		} catch (IOException ioExc) {
				System.out.println("CsvReader: "+ioExc.getMessage());
			
		}
		
		
	}
	
	public static void removeLine( int lineIndex) {
		try {
			// Olvassuk be a fájlt
			String filePath = "m_data/data.csv";
			File file = new File(filePath);
			BufferedReader reader = new BufferedReader(new FileReader(file));

			// Készítsünk egy ideiglenes fájlt, ahova átmásoljuk a tartalmat, kivéve az
			// adott sort
			File tempFile = new File(file.getAbsolutePath() + ".tmp");
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			// Távolítsuk el az adott sort
			int currentLineIndex = 0;
			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				currentLineIndex++;
				if (currentLineIndex != lineIndex) {
					writer.write(currentLine);
					writer.newLine();
				}
			}

			// Zárjuk a fájlokat és cseréljük ki az eredeti fájlt az ideiglenes fájllal
			writer.close();
			reader.close();
			if (!file.delete()) {
				throw new IOException("Nem sikerült törölni a fájlt: " + file);
			}
			if (!tempFile.renameTo(file)) {
				throw new IOException("Nem sikerült átnevezni a fájlt: " + tempFile);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void exportToPDF(File inputFile, File outputFile) throws IOException {
	    // Read the input file content
	    List<String> lines = Files.readAllLines(inputFile.toPath());
	    
	    

	    // Create a new PDF document
	    PDDocument document = new PDDocument();

	    // Define the font to use
	    /*PDFont font = PDType1Font ;
	    PDFont pdfFont = PDType1Font.Arial;
	    */
	   
	    // Create font and mark it to be embedded
	    //PDFont font = new PdfFont(PdfFontFamily.Helvetica, 14f);


	    // Create a new page
	    PDPage page = new PDPage(PDRectangle.A4);
	    document.addPage(page);

	    // Create a new content stream for the page
	    PDPageContentStream contentStream = new PDPageContentStream(document, page);

	    // Set the font and font size
	    /*
	    contentStream.setFont(font, 12);
	     */
	    // Define the starting position of the text
	    float x = 50;
	    float y = page.getMediaBox().getHeight() - 50;

	    // Write each line of the input file to the PDF document
	    for (String line : lines) {
	        // Write the text
	        contentStream.beginText();
	        contentStream.newLineAtOffset(x, y);
	        contentStream.showText(line);
	        contentStream.endText();

	        // Move to the next line
	        y -= 15;

	        // If the bottom of the page has been reached, create a new page
	        if (y < 50) {
	            contentStream.close();
	            page = new PDPage(PDRectangle.A4);
	            document.addPage(page);
	            contentStream = new PDPageContentStream(document, page);
	            //contentStream.setFont(font, 12);
	            y = page.getMediaBox().getHeight() - 50;
	        }
	    }

	    // Close the content stream
	    contentStream.close();

	    // Save the PDF document
	    document.save(outputFile);

	    // Close the document
	    document.close();
	}
	

}
