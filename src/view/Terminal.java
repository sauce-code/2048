package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Stellt Methoden zum Einlesen von Eingaben aus der Konsole bereit.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class Terminal {

	/**
	 * Liefert die Zeichen aus der Konsole.
	 */
	private BufferedReader console;

	/**
	 * Erzeugt ein neues Terminal, welches auf die Konsole zugreifen kann und
	 * initialisiert ist.
	 */
	public Terminal() {
		console = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * Gibt die n&auml;chste Eingabezeile als String zur&uuml;ck.
	 * 
	 * @return n&auml;chste Eingabezeile als String
	 */
	public String readLine() {
		try {
			return console.readLine();
		} catch (IOException e) {
			return "\n";
		}
	}

	/**
	 * Gibt die n&auml;chste Eingabezeile als int-Wert zur&uuml;ck. Es werden
	 * nur die ersten Ziffern inklusive Vorzeichen ber&uuml;cksichtigt.
	 * 
	 * @return n&auml;chste Eingabezeile als int-Wert
	 */
	public int readInt() {
		return parseInt(readLine());
	}

	/**
	 * Wandelt {@code string} in einen int-Wert.
	 * 
	 * @param string
	 *            String, welcher gewandelt werden soll
	 * @return {@code string} als int-Wert
	 */
	private int parseInt(String string) {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

}
