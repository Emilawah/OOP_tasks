package oop.term;

public class Text {

	private char[][] text;

	public Text(int row, int col) {
		text = new char[row][col];
		clear();
	}

	public void delete(int row, int col) {
		if (col < text[row].length - 1) {
			for (int i = col; i < text[row].length - 1; i++) {
				text[row][i] = text[row][i + 1];
			}
		}
		text[row][text[row].length - 1] = '\0';
	}

	public void backspace(int row, int col) {
		if (col > 0) {
			col--;
			for (int i = col; i < text[row].length - 1; i++) {
				text[row][i] = text[row][i + 1];
			}
			text[row][text[row].length - 1] = '\0';
		}
	}

	public void clear() {
		for (int i = 0; i < text.length; i++) {
			clearRow(i);
		}

	}

	public void clearRow(int row) {
		if (row >= 0 && row < text.length) {
			for (int i = 0; i < text[row].length; i++) {
				text[row][i] = '\0';
			}
		}
	}

	public void insert(int row, int col, char c) {
		if (row >= 0 && row < text.length && col >= 0 && col < text[row].length) {
			for (int i = text[row].length - 1; i > col; i--) {
				text[row][i] = text[row][i - 1];
			}
			text[row][col] = c;
		}
	}

	public char getCharAt(int row, int col) {
		if (row >= 0 && row < text.length && col >= 0 && col < text[row].length) {
			return text[row][col];
		}
		return '\0';
	}

}