package model;


/**
 * Stellt ein 2048-Spiel dar.
 * 
 * @author Torben Kr&uuml;ger
 * @version 1.0
 *
 */
public class Game {

	/**
	 * Minimale Anzahl an Reihen.
	 */
	public static final int ROWS_MIN = 2;

	/**
	 * Maximalen Anzahl an Reihen.
	 */
	public static final int ROWS_MAX = 10;

	/**
	 * Standardm&auml;&szlig;ige Anzahl an Reihen.
	 */
	public static final int ROWS_STD = 4;

	/**
	 * Minimale Anzahl an Spalten.
	 */
	public static final int COLUMNS_MIN = 2;

	/**
	 * Maximalen Anzahl an Spalten.
	 */
	public static final int COLUMNS_MAX = 10;

	/**
	 * Standardm&auml;&szlig;ige Anzahl an Spalten.
	 */
	public static final int COLUMNS_STD = 4;

	/**
	 * Die Basis jeden Feldes.
	 */
	public static final int BASE_STD = 2;

	/**
	 * Standardm&auml;&szlig;ige Anzahl an maximalen Undos.
	 */
	public static final int UNDOS_STD = 20;

	/**
	 * Anzahl der Reihen.
	 */
	private int rows;

	/**
	 * Anzahl der Spalten.
	 */
	private int columns;

	/**
	 * Anzahl der maximalen Undos.
	 */
	private int undos;

	/**
	 * Das Spielbrett.
	 */
	private Board board;

	/**
	 * Speichert alte Spielbretter.
	 */
	private History<Board> history;

	/**
	 * Erzeugt ein neues Spiel mit Standard-Werten.
	 */
	public Game() {
		rows = ROWS_STD;
		columns = COLUMNS_STD;
		undos = UNDOS_STD;
		init();
	}

	/**
	 * Initialisiert das Spiel.
	 */
	private void init() {
		board = new Board(columns, rows);
		history = new History<Board>(undos);
	}

	/**
	 * F&uuml;hrt einen Spielzug durch.
	 * 
	 * @param direction
	 *            Richtung, in welche geschoben wird
	 * @return {@code true}, falls Spielzug g&uuml;tig<br>
	 *         {@code false}, sonst
	 */
	public boolean move(Direction direction) {
		Board clone = board.clone();
		boolean ret = board.move(direction);
		if (ret) {
			history.add(clone);
		}
		return ret;
	}

	/**
	 * Liefert die aktuelle Punktzahl.
	 * 
	 * @return die aktuelle Punktzahl
	 */
	public int getScore() {
		return board.getScore();
	}

	/**
	 * Liefert die Anzahl der verbleibenden Undos.
	 * 
	 * @return die Anzahl der verbleibenden Undos
	 */
	public int getUndosLeft() {
		return history.size();
	}

	/**
	 * Gibt an, ob das Spiel beendet ist.
	 * 
	 * @return {@code true}, falls Spiel beendet<br>
	 *         {@code false}, sonst
	 */
	public boolean isGameOver() {
		return !board.isAnyMoveLeft();
	}

	/**
	 * Liefert das aktuelle Spielfeld.
	 * 
	 * @return das aktuelle Spielfeld
	 */
	public Tile[][] getField() {
		return board.getField();
	}

	/**
	 * Macht einen Zug r&uuml;ckg&auml;ngig, falls m&ouml;glich.
	 */
	public void undo() {
		board = history.remove();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("undos left: ");
		sb.append(history.size());
		sb.append('\n');
		sb.append(board.toString());
		return sb.toString();
	}

}
