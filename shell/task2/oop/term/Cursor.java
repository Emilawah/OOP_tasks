package oop.term;

public class Cursor {

	private int col;
	private int row;
	private int nrows;
	private int ncols;

	public Cursor(int nrows, int ncols) {
		this.col = 0;
		this.row = 0;
		this.ncols = ncols;
		this.nrows = nrows;
	}

	public void setCursor(int row, int col) {
		this.row = row;
		this.col = col;

	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public void setCol(int c) {
		this.col = c;
	}

	public void setRow(int r) {
		this.row = r;
	}

	public void left() {
		if (col > 0) {
			col--;
		}
	}

	public void right() {
		if (col < ncols - 1) {
			col++;
		}
	}

	public void up() {
		if (row > 0) {
			row--;
		}
	}

	public void down() {
		if (row < nrows - 1) {
			row++;
		}
	}
}
