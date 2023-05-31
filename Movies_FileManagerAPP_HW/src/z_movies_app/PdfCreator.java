package z_movies_app;

import java.io.FileOutputStream;
import javax.swing.JTable;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfCreator {
	

	
	 public static void exportToPDF2(JTable table, String filePath) {
	        try {
	            Document document = new Document();
	            
	            FileOutputStream fileOut  =new FileOutputStream("newfile.pdf");
	            PdfWriter.getInstance(document,  fileOut);
	            document.open();
	            

	            // létrehozunk egy PdfPTable objektumot a JTable alapján
	            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
	            
	            
	            
	            // táblázat fejléc beállítás
	            for (int i = 0; i < table.getColumnCount(); i++) {
	                pdfTable.addCell(table.getColumnName(i));
	            }
	            // hozzáadjuk az adatokat a táblázathoz
	            for (int rows = 0; rows < table.getRowCount(); rows++) {
	                for (int cols = 0; cols < table.getColumnCount(); cols++) {
	                    pdfTable.addCell(table.getModel().getValueAt(rows, cols).toString());
	                }
	            }

	            // hozzáadjuk a táblázatot a dokumentumhoz
	            document.add(pdfTable);
	            document.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	 
		
		


}
