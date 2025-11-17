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

	public static final Direction HERE;

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
		HERE = new Here();
	}

	public static Direction toAbsolute(int angle) {
		return new Absolute(angle);
	}

	public static Direction cardinalOrient(int orientation, Direction d) {
		if (d.isRelative()) {
			// on combine l'orientation actuelle avec l'angle relatif
			int absAngle = (orientation + d.degrees()) % 360;
			return Direction.toAbsolute(absAngle);
		} else {
			// si la direction est déjà absolue, on la retourne normalisée
			return d.cardinalOf();
		}
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
			return !d.isRelative() && (d.degrees() == super.angle);
		}

		@Override
		public Direction rotate(int angle) {

			return new Absolute(degrees() + angle);
		}

		@Override
		public Direction cardinalOf() {
			int a = degrees();
			if ((a >= 315 && a < 360) || (a >= 0 && a < 45)) {
				return N;
			} else if (a >= 45 && a < 135) {
				return E;
			} else if (a >= 135 && a < 225) {
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

	private static class Here extends Direction {

		protected Here() {
			super(0);
		}

		@Override
		public boolean isRelative() {
			return false;
		}

		@Override
		public int degrees() {
			return 0;
		}

		@Override
		public boolean equals(Direction d) {
			return d instanceof Here;
		}

		@Override
		public Direction rotate(int angle) {
			return this;
		}

		@Override
		public Direction cardinalOf() {
			return this;
		}

	}
}