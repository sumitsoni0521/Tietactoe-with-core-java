// use of Arrays , Looping, if-else, class , Object, & Inheritance.
//class is not exit in reality object of class is exit.
//2D array

import java.util.*;
class TicTacToe {
	static char board[][];
	// to present the array index we use constructor and this constructor does not
	// have a return type
	public TicTacToe() {
		/* here i give reference of board */
		// If somebody creates an object Constructor gets called Constructor will create
		// a three cross three array and it is going to give it as board.
		board = new char[3][3];/*default value is present in unicode formate buz java follow unicode formate, which is called null char.*/
		inBoard();// call the function.
	}
	void inBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';// empty
			}
		}
	}
	static void dispBoard() {// display the board of 3 rows & 3 columns.
		System.out.println("-------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	static void placeMark(int row, int col, char mark) {
		if (row >= 0 && row <= 2 && col >= 0 && row <= 2) {
			board[row][col] = mark;
		} else {
			System.out.println("Invalid Position");
		}
	}

	static boolean checkRow() {
		for (int i = 0; i <= 2; i++) {
			if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		return false;
	}

	static boolean checkCol() {
		for (int j = 0; j <= 2; j++) {
			if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		return false;
	}

	static boolean checkCross() {
		if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
				|| board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		} else {
			return false;
		}
	}

	static boolean checkDrow() {
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

}

abstract class Player {
	String name;
	char mark;

	abstract void makeMove();

	boolean isValidMove(int row, int col) {
		if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
			if (TicTacToe.board[row][col] == ' ') {
				return true;
			}
		}
		return false;
	}
}

class HumanPlayer extends Player {
	String name;
	char mark;

	HumanPlayer(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}

	void makeMove() {
		Scanner sc = new Scanner(System.in);
		int row;
		int col;
		do {
			System.out.println("Enter the index of row & col :");
			row = sc.nextInt();
			col = sc.nextInt();
		} while (!isValidMove(row, col));
		TicTacToe.placeMark(row, col, mark);
	}

}

class CompPlayer extends Player {
	String name;
	char mark;

	CompPlayer(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}

	void makeMove() {
		Scanner sc = new Scanner(System.in);
		int row;
		int col;
		do {
			Random r = new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);
		} while (!isValidMove(row, col));
		TicTacToe.placeMark(row, col, mark);
	}

}

public class JGame {
	public static void main(String[] args) {
		new TicTacToe();

		HumanPlayer p1 = new HumanPlayer("Player1", 'x');
		CompPlayer p2 = new CompPlayer("A.I", '0');
		Player cp;
		cp = p1;
		while (true) {
			System.out.println(cp.name + " turn");
			cp.makeMove();
			TicTacToe.dispBoard();
			if (TicTacToe.checkCol() || TicTacToe.checkRow() || TicTacToe.checkCross()) {
				System.out.println(cp.name + " has won");
				break;
			} else if (TicTacToe.checkDrow()) {
				System.out.println(" Game is Over! ");
				break;
			} else {
				if (cp == p1) {
					cp = p2;
				} else {
					cp = p1;
				}
			}
		}
	}
}
