package model;

/**
 * Stellt eine Kachel dar, die ihren eigenen Wert kennt.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class Tile {

	/**
	 * Der Wert der Kachel.
	 */
	private int value;

	/**
	 * Erzeugt eine neue Kachel ohne Wert.
	 */
	public Tile() {
		value = 0;
	}

	/**
	 * Liefert den Wert.
	 * 
	 * @return aktueller Wert
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Setzt den Wert der Kachel.
	 * 
	 * @param value
	 *            zu setzender Wert
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * L&auml;sst die Kachel wachsen.
	 */
	public void grow() {
		value++;
	}

	// /**
	// * Gibt an, ob die Kachel mit einer anderen identisch ist.
	// *
	// * @param other
	// * Kachel, mit der verglichen werden soll
	// * @return {@code true}, falls beide Kacheln identisch<br>
	// * {@code false}, falss nicht
	// */
	// public boolean equals(Tile other) {
	// return (this.value == other.value);
	// }

	public boolean equals(Object other) {
		return (other != null && this.getClass() == other.getClass() && this.value == ((Tile) other).value);
	}

	// TODO clone() ?

}
