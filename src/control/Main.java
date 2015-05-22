package control;

import view.Terminal;
import model.Board;
import model.Direction;

/**
 * Klasse zum Ausf&uuml;hren des Programms.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class Main {

	/**
	 * Startet ein Spiel und l&auml;sst es so lange durchlaufen, bis es zu Ende
	 * ist.
	 * 
	 * @param args
	 *            Parameter, ungenutzt
	 */
	public static void main(String[] args) {
		Terminal terminal = new Terminal();
		Board board = new Board(4, 4);
		System.out.println(board);
		while (board.isAnyMoveLeft()) {
			switch (terminal.readLine()) {
			case "w":
				board.move(Direction.UP);
				break;
			case "d":
				board.move(Direction.RIGHT);
				break;
			case "s":
				board.move(Direction.DOWN);
				break;
			case "a":
				board.move(Direction.LEFT);
				break;
			default:
				break;
			}
			System.out.println(board);
		}
	}

}
