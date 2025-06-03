package engine.brain;

public abstract class Direction {

	final private int angle;

	protected Direction(int a) {
		angle = a;
	}

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
		N = new Absolute(0);
		E = new Absolute(90);
		S = new Absolute(180);
		W = new Absolute(270);
		F = new Relative(0);
		B = new Relative(180);
		L = new Relative(270);
		R = new Relative(90);
	}

	// must be static
	// should be private
	private static class Absolute extends Direction {

		protected Absolute(int angle) {
			super(angle);
		}

		@Override
		public boolean isRelative() {
			return false;
		}

		@Override
		public int degrees() {

			return super.angle;
		}

		@Override
		public boolean equals(Direction d) {
			return !isRelative() && (d.degrees() == super.angle);
		}

		@Override
		public Direction rotate(int angle) {

			return new Absolute(degrees() + angle);
		}

		@Override
		public Direction cardinalOf() {
			int a = degrees();
			if (a >= 315 && a < 45) {
				return N;
			} else if (a < 135) {
				return E;
			} else if (a < 225) {
				return S;
			} else {
				return W;
			}
		}

	}

	// must be static
	// should be private
	private static class Relative extends Direction {

		protected Relative(int angle) {
			super(angle);
		}

		@Override
		public boolean isRelative() {
			return true;
		}

		@Override
		public int degrees() {
			return super.angle;
		}

		@Override
		public boolean equals(Direction d) {

			return d.isRelative() && (d.degrees() == super.angle);
		}

		@Override
		public Direction rotate(int angle) {

			return new Relative(degrees() + angle);
		}

		@Override
		public Direction cardinalOf() {
			return this;
		}

	}
}