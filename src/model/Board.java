package model;

import java.util.LinkedList;

/**
 * Stellt ein Spielfeld dar.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class Board {

	/**
	 * Die Basis jeden Feldes.
	 */
	public static final int BASE_STD = 2;

	/**
	 * Exponent einer kleinen Zahl.
	 */
	public static final int SMALL_STD = 1;

	/**
	 * Exponent einer gro&szlig;en Zahl.
	 */
	public static final int BIG_STD = 2;

	/**
	 * Entstehungswahrscheinlichkeit einer gro&szlig;en Zahl.
	 */
	public static final double SPAWN_POSS_SMALL_STD = .75;

	/**
	 * Der Startwert f&uuml;r den Punktestand.
	 */
	public static final int SCORE_INIT = 0;

	/**
	 * Der Startwert f&uuml;r die absolvierten Z&uuml;ge.
	 */
	public static final int MOVES_DONE_INIT = 0;

	/**
	 * Die Anzahl der Spalten.
	 */
	private int columns;

	/**
	 * Die Anzahl der Reihen
	 */
	private int rows;

	/**
	 * Der Punktestand.
	 */
	private int score;

	/**
	 * Anzahl der Z&uuml;ge, die bisher absolviert wurden.
	 */
	private int movesDone;

	/**
	 * Das Spielfeld.
	 */
	private Tile[][] tiles;

	// TODO
	public Board(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		this.score = SCORE_INIT;
		this.movesDone = MOVES_DONE_INIT;
		this.tiles = new Tile[rows][columns];
		initField();
		spawn();
		spawn();
	}

	/**
	 * Initialisiert das Spielfeld.
	 */
	private void initField() {
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				tiles[row][column] = new Tile();
			}
		}
	}

	/**
	 * Fertigt eine Kopie des &Uuml;bergebenen Spielbretts an.
	 * 
	 * @param orig
	 *            Spielbrett, welches kopiert werden soll
	 */
	private Board(Board orig) {
		this.columns = orig.columns;
		this.rows = orig.rows;
		this.score = orig.score;
		this.movesDone = orig.movesDone;
		this.tiles = orig.tiles.clone();
	}

	// TODO
	private boolean spawn() {
		LinkedList<Tile> list = new LinkedList<Tile>();
		for (int row = 0; row < tiles.length; row++) {
			for (int column = 0; column < tiles[row].length; column++) {
				if (tiles[row][column].getValue() == 0) {
					list.add(tiles[row][column]);
				}
			}
		}
		boolean ret = list.size() > 0;
		if (ret) {
			list.get(random(list.size())).setValue(smallBig());
			// list.set(random(list.size()), smallBig());
		}
		return ret;
	}

	/**
	 * Liefert eine Zufallszahl zwischen einschlie&szlig;lich {@code 0} und
	 * ausschlie&szlig;lich {@code i}.
	 * 
	 * @param i
	 *            obere Grenze
	 * @return berechnete Zufallszahl
	 */
	private int random(int i) {
		return (int) (Math.random() * i);
	}

	/**
	 * Entscheidet per Zufall, ob {@link #SMALL_STD} oder {@link #BIG_STD}
	 * geliefert wird.
	 * 
	 * @return {@link #SMALL_STD} zu P = {@link #SPAWN_POSS_SMALL_STD}<br>
	 *         {@link #BIG_STD}, sonst
	 */
	private int smallBig() {
		return Math.random() < SPAWN_POSS_SMALL_STD ? SMALL_STD : BIG_STD;
	}

	/**
	 * F&uuml;hrt einen Zug auf dem Spielfeld aus.
	 * 
	 * @param direction
	 *            Richtung, in welche geschoben wird
	 * @return {@code true}, falls Spielzug g&uuml;tig<br>
	 *         {@code false}, sonst
	 */
	public boolean move(Direction direction) {
		boolean ret = false;
		switch (direction) {
		case UP:
			for (int column = 0; column < columns; column++) {
				int a = -1;
				for (int row = 0; row < rows; row++) {
					if (tiles[column][row].getValue() != 0) {
						int temp = tiles[column][row].getValue();
						tiles[column][row].setValue(0);
						if (a == -1) {
							tiles[column][a + 1].setValue(temp);
						} else if (a != row) {
							if (temp == (tiles[column][a].getValue())) {
								grow(tiles[column][a]);
							} else {
								tiles[column][a + 1].setValue(temp);
							}
						}
						a++;
					}
				}
			}
			// TODO
			break;
		case RIGHT:
			// TODO
			break;
		case DOWN:
			// TODO
			break;
		case LEFT:
			// TODO
			break;
		default:
			// TODO
			break;
		}
		if (ret) {
			spawn();
			movesDone++;
		}
		return ret;
	}

	/**
	 * L&auml;sst ein Tile wachsen und passt die Punktzahl entsprechend an.
	 * 
	 * @param tile
	 *            Tile, welches wachsen soll
	 */
	private void grow(Tile tile) {
		tile.grow();
		score += Math.pow(BASE_STD, tile.getValue());
	}

	/**
	 * Liefert die Punktzahl.
	 * 
	 * @return die Punktzahl
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Liefert eine Kopie des Spielfeldes.
	 * 
	 * @return Kopie des Spielfeldes
	 */
	public Tile[][] getField() {
		return tiles.clone();
	}

	/**
	 * Gibt an, ob noch mindestens ein weiterer Zug m&ouml;glich ist.
	 * 
	 * @return {@code true}, falls mindestens ein weiterer Zug m&ouml;glich<br>
	 *         {@code false}, falls nicht
	 */
	public boolean isAnyMoveLeft() {
		return isAnyTileEmpty() || isMergePossibleHorizontal()
				|| isMergePossibleVertical();
	}

	/**
	 * Gibt an, ob mindestens ein Tile existiert, welches leer ist.
	 * 
	 * @return {@code true}, falls mindestens ein Tile leer ist<br>
	 *         {@code false}, falls nicht
	 */
	private boolean isAnyTileEmpty() {
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; columns++) {
				if (tiles[row][column].getValue() == 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Gibt an, ob mindestens ein Merge in vertikaler Richtung m&ouml;glich ist.
	 * Funktioniert nur garantiert, <b>wenn das Feld voll ist</b>.
	 * 
	 * @return {@code true,}, falls mindestens ein Merge m&ouml;glich ist<br>
	 *         {@code false}, falls nicht
	 */
	private boolean isMergePossibleVertical() {
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns - 1; column++) {
				if (tiles[row][column].equals(tiles[row][column + 1])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Gibt an, ob mindestens ein Merge in horizontaler Richtung m&ouml;glich
	 * ist. Funktioniert nur garantiert, <b>wenn das Feld voll ist</b>.
	 * 
	 * @return {@code true,}, falls mindestens ein Merge m&ouml;glich ist<br>
	 *         {@code false}, falls nicht
	 */
	private boolean isMergePossibleHorizontal() {
		for (int column = 0; column < columns; column++) {
			for (int row = 0; row < rows - 1; row++) {
				if (tiles[row][column] == tiles[row + 1][column]) {
					return true;
				}
			}
		}
		return false;
	}

	public Board clone() {
		return new Board(this);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("score: ");
		sb.append(score);
		sb.append('\n');
		sb.append("moves done: ");
		sb.append(movesDone);
		sb.append('\n');
		for (int row = 0; row < tiles.length; row++) {
			for (int column = 0; column < tiles[row].length; column++) {
				sb.append(tiles[column][row].getValue());
			}
			sb.append('\n');
		}
		return sb.toString();
	}

}
