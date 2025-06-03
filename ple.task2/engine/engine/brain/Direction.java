package engine.brain;

public abstract class Direction {

	// Absolute
	public static final Direction N; // north
	public static final Direction E; // east
	public static final Direction S; // south
	public static final Direction W; // west
	// Relative
	public static final Direction F; // forward
	public static final Direction B; // backward
	public static final Direction L; // left
	public static final Direction R; // right

	public abstract boolean isRelative();

	public abstract int degrees();

	public abstract boolean equals(Direction d);

	public abstract Direction rotate(int angle);

	public abstract Direction cardinalOf();

	static {
		N = new Absolute(270);
		E = new Absolute(0);
		S = new Absolute(90);
		W = new Absolute(180);
		F = new Relative(0);
		B = new Relative(180);
		L = new Relative(270);
		R = new Relative(90);
	}

	// must be static
	// should be private
	private static
	class Absolute extends Direction {

		@Override
		public boolean isRelative() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int degrees() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean equals(Direction d) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Direction rotate(int angle) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Direction cardinalOf() {
			// TODO Auto-generated method stub
			return null;
		}
	
	}

	// must be static
	// should be private
	private static
	class Relative extends Direction {

		@Override
		public boolean isRelative() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int degrees() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean equals(Direction d) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Direction rotate(int angle) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Direction cardinalOf() {
			// TODO Auto-generated method stub
			return null;
		}
	
	}
}