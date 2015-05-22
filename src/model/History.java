package model;

import java.util.LinkedList;

/**
 * Merkt sich vergangene Elemente.
 * 
 * @author Torben Kr&uuml;ger
 * @version 1.0
 *
 */
public class History<E> {

	/**
	 * Speichert die Elemente.
	 */
	private LinkedList<E> elements;

	/**
	 * Anzahl der maximal gespeicherten Elemente.
	 */
	private int limit;

	/**
	 * Erzeugt eine neue History.
	 * 
	 * @param undos
	 *            Anzahl der maximal gespeicherten Elemente
	 */
	public History(int limit) {
		elements = new LinkedList<E>();
		this.limit = limit;
	}

	/**
	 * Liefert die Anzahl der gespeicherten Elemente.
	 * 
	 * @return Anzahl der gespeicherten Elemente
	 */
	public int size() {
		return elements.size();
	}

	/**
	 * F&uuml;gt der History ein Element hinzu. Sollte dabei {@link #undos}
	 * dabei &uuml;berschritten werden, so wird das &auml;lteste Element
	 * gel&ouml;scht.
	 * 
	 * @param element
	 */
	public void add(E element) {
		elements.addLast(element);
		if (size() > limit) {
			elements.removeFirst();
		}
	}

	/**
	 * Liefert das letzte Element, welches hinzugef&uuml;gt wurde und
	 * l&ouml;scht es aus der History.
	 * 
	 * @return das letzte Element, welches hinzugef&uuml;gt wurde<br>
	 *         {@code null}, falls keines vorhanden
	 */
	public E remove() {
		return (size() > 0 ? elements.removeLast() : null);
	}

}
