package oop.term;

public class Cursor {

	private int col;
	private int row;
	private int nrows;
	private int ncols;

	public Cursor(int r, int c) {
		this.col = c;
		this.row = r;
		this.ncols = 0;
		this.nrows = 0;
	}

	public void setCursor(int r, int c) {
		if (r >= 0 && r < nrows)
			setRow(r);
		if (c >= 0 && c < ncols)
			setCol(c);

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

	public void setSize(int r, int c) {
		nrows = r;
		ncols = c;
	}

	public void enter() {
		if (getRow() < nrows - 1) {
			setRow(getRow() + 1);
			setCol(0);
		}
	}

}
