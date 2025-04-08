package oop.board;

public class Board {

	
	public interface Listener{
		
		public void players(char player1, char player2);
		
		public void played(int row, int column, char player);
		
		public void winner(char player);
		
	}
	
	
	
	private char[][] board;
	private char player1;
	private char player2;
	private Listener listener;

	public Board(char p1, char p2) {
		board = new char[3][3];
		this.player1 = p1;
		this.player2 = p2;
		
		set(listener);
		for(int i = 0 ; i < 3 ;i++) {
			for(int j = 0 ; j < 3 ; j++) {
				set(i,j,' ');
			}
		}
		if(listener != null) {
			listener.players(player1, player2);
		}
	}

	public char get(int row, int col) {
		return board[row][col];
	}

	public void set(int row, int col, char player) {
		board[row][col] = player;
		if(listener != null) {
			listener.played(row, col, player);
		}
	}

	public char winner() {
		for (int i = 0; i < 3; i++) {

			if (((get(i, 0) == player1 && get(i, 0) != ' ') 
					&& (get(i, 1) == player1 && get(i, 1) != ' ')
					&& (get(i, 2) == player1 && get(i, 2) != ' '))
			|| ((get(0, i) == player1 && get(0, i) != ' ')
					&& (get(1, i) == player1 && get(1, i) != ' ')
					&& (get(2, i) == player1 && get(2, i) != ' '))) {
				if (listener != null) {
					listener.winner(player1);
				}
				return player1;
			}
			if (((get(i, 0) == player2 && get(i, 0) != ' ') 
					&& (get(i, 1) == player2 && get(i, 1) != ' ')
					&& (get(i, 2) == player2 && get(i, 2) != ' '))
			|| ((get(0, i) == player1 && get(0, i) != ' ')
					&& (get(1, i) == player2 && get(1, i) != ' ')	
					&& (get(2, i) == player2 && get(2, i) != ' '))) {
				if (listener != null) {
					listener.winner(player2);
				}
				return player2;
		}
			
		}
		return ' ';
	}
	
	public void set(Listener l) {
		this.listener = l;
		if(listener != null) {
			listener.players(player1, player2);
		}
	}
}
