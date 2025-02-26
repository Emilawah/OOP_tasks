package oop.term;

public class Text {

	private char[][] buffer;
	private int rows;
	private int cols;
	private Cursor cursor;

	public Text(int row, int col) {
		this.rows = row;
		this.cols = col;
		this.buffer = new char[row][col];
		this.cursor = new Cursor(row, col);
	}

	public void delete() {
		int row = cursor.getRow();
		int col = cursor.getCol();
		for (int c = col; c < cols - 1; c++) {
			buffer[row][c] = buffer[row][c + 1];
		}
		buffer[row][cols - 1] = ' ';
	}
		
	public void backspace() {
		if (cursor.getCol() > 0) {
			cursor.left();
			delete();
		} else if (cursor.getRow() > 0) {
			cursor.setRow(cursor.getRow() - 1);
			cursor.setCol(cols - 1);
		}
	}

	public void clear() {
		for (int r = 0; r < rows; r++) {
			clearRow(r);
		}
	}

	public void clearRow(int row) {
		if (row >= 0 && row < rows) {
			for (int c = 0; c < cols; c++) {
				buffer[row][c] = ' ';
			}
		}
	}

	public void enter() {
		if (cursor.getRow() < rows - 1) {
			cursor.setRow(cursor.getRow() + 1);
			cursor.setCol(0);
		}
	}

	public void insert(char c) {
		int row = cursor.getRow();
		int col = cursor.getCol();
		if (col < cols - 1) {
			for (int i = cols - 1; i > col; i--) {
				buffer[row][i] = buffer[row][i - 1];
			}
			buffer[row][col] = c;
			cursor.right(); 
		}
	}
}

