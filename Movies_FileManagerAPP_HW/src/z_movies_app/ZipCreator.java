package z_movies_app;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCreator {
	
	
	public static void zipCreator() throws IOException {
		String file1 = "m_data/data.csv";
		String file2 = "m_data/movies.csv";

		final List<String> srcFiles = Arrays.asList(file1, file2);

		final FileOutputStream fileOut = new FileOutputStream(Paths.get(file1).getParent().toAbsolutePath() + "/tomoritet.zip");
		ZipOutputStream zipOut = new ZipOutputStream(fileOut);

		for (String srcFile : srcFiles) {
			File fileToZip = new File(srcFile);
			FileInputStream fis = new FileInputStream(fileToZip);
			ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
			zipOut.putNextEntry(zipEntry);

			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zipOut.write(bytes, 0, length);
			}
			fis.close();
		}

		zipOut.close();
		fileOut.close();
	}
	

}
