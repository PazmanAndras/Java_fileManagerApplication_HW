package z_movies_app;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class DataCheck {

	public static boolean idCheck(String idInput) {

		try {
			@SuppressWarnings("unused")
			double number = Double.parseDouble(idInput);

			// JOptionPane.showMessageDialog(null, "A megadott érték: " + number);

			return true;
		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(null, "Hibás formátum! Kérem, adjon meg egy számot.", "Hiba",
					JOptionPane.ERROR_MESSAGE);
			return false;

		}

	}

	public static boolean isDateFormatValid(String date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		dateFormat.setLenient(false); // a dátum formátumának ellenőrzése

		try {

			// a dátum ellenőrzése a formátum alapján
			dateFormat.parse(date);

			return true;
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "A megadott dátum nem megfelelő formátumú (yyyy-MM-dd)!");
			return false;
		}
	}

	public static boolean isYearValid(String yearString) {
		try {
			// az év int típusúvá alakítása
			int year = Integer.parseInt(yearString);

			if (year > 1850) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "A megadott év nem megfelelő (nagyobbnak kell lennie, mint 1850)!");
				return false; // ha az év nem megfelelő, hamis értékkel térünk vissza és megjelenítjük az
								// üzenetet
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "A megadott érték nem szám!");
			return false; // ha az érték nem szám, hamis értékkel térünk vissza és megjelenítjük az
							// üzenetet
		}
	}

	public static boolean isNumber(String input) {
		try {
			// az érték double típusúvá alakítása
			Double.parseDouble(input);

			// ha az érték szám, igaz értékkel térünk vissza
			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "A megadott érték nem szám!");
			return false;
		}
	}

	public static boolean isString(String input) {

		if (input instanceof String) {
			return true;
		} else {

			JOptionPane.showMessageDialog(null, "A megadott érték nem szöveg!");
			return false;
		}
	}

}
