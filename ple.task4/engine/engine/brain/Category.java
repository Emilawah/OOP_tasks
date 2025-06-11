package engine.brain;

public abstract class Category {

	//
	public static final Category Category;

	public static final Category PLAYER;
	public static final Category Obstacle;
	public static final Category ADVERSARY;
	public static final Category TEAM;
	public static final Category VOID;
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

		PLAYER = new Type(Category);
		Obstacle = new Type(Category);
		ADVERSARY = new Type(Category);
		TEAM = new Type(Category);
		VOID = new Type(Category);
		
		// unused atm
		Tree = new Type(Obstacle);
		Rock = new Type(Obstacle);
		Wall = new Type(Obstacle);
		Ghost = new Type(ADVERSARY);
		Assassin = new Type(ADVERSARY);

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
			while (current != null) {
				if(current.equals(c)) {
					return true;
				}
				current = ((Type) current).parent;
			}
			return false;
		}
	}
}
