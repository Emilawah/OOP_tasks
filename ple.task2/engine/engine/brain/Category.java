package engine.brain;

public abstract class Category {

	//
	public static final Category Category;

	public static final Category Player;
	public static final Category Obstacle;
	public static final Category Adversary;

	public static final Category Tree;
	public static final Category Rock;
	public static final Category Wall;

	public static final Category Ghost;
	public static final Category Assassin;

	/*
	 * true if the two objects represent the same category
	 */
	public abstract boolean equals(Category c);

	/*
	 * true if this category specializes the given one.
	 */
	public abstract boolean specializes(Category c);

	static {
		// Category
		Category = new Type(null);

		Player = new Type(Category);
		Obstacle = new Type(Category);
		Adversary = new Type(Category);

		Tree = new Type(Obstacle);
		Rock = new Type(Obstacle);
		Wall = new Type(Obstacle);
		Ghost = new Type(Adversary);
		Assassin = new Type(Adversary);

	}

	private static class Type extends Category {

		private Category parent;

		protected Type(Category parent) {
			this.parent = parent;
		}

		@Override
		public boolean equals(Category c) {
			return this == c;
		}

		@Override
		public boolean specializes(Category c) {
			Category current = this;
			if (current == c) {
				return true;
			}
			if (current instanceof Type) {
				current = ((Type) current).parent;
			}
			return false;
		}

	}
}
