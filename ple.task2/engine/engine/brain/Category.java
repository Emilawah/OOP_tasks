package engine.brain;

public abstract class Category {
	/*
	* true if the two objects represent
	* the same category
	*/
	public abstract boolean equals(Category c);
	/*
	* true if this category specializes
	* the given one.
	*/
	public abstract boolean specializes(Category c);
}
