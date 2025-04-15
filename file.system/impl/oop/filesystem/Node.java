package oop.filesystem;

public abstract class Node {

	private String path;
	private String value;
	private Node next;
	private Node parent;
	private Node root;

	protected Node() {
		this.next = null;
		this.root = parent;
		this.value = "";
		this.path = "/";
		

	}

	protected Node(Node node, String name) {
		new Iterator(root);
	
		this.parent = node;
		this.value = name;
		this.next = null;
		
		
		this.path = modifyPath(parent.path,name);

	}


	
	public class Iterator  {

		private Node node;

		public Iterator(Node n) {
			this.node = n;
		}

		public boolean hasNext() {
			return node != null;
		}

		public void next() {
			if (!hasNext()) {
				throw new IndexOutOfBoundsException("No more nodes");
			}
			node = node.next;

		}
	}

	public String modifyPath(String s1, String s2) {
		if(s1=="/") {
			return "/"+s2;
		}
		return s1+"/"+s2;
	}
	
	public Node parent() {
		return parent;
	}

	public Iterator children() {
		return new Iterator(parent);
	}

	public boolean valid() {
		if(parent != null || children().hasNext()) {
			return true;
		}
		return false;
	}

	public String name() {
		return parent.value;
	}

	public String path() {
		return path;
	}

	protected void add(Node node) {
	
		node.parent = parent;
		parent.next = node;
		path = node.parent.value + node.value;
	}

	protected void remove(Node node) {

		Node cell = node;
		parent.next = cell;
		cell = null;
		
	}

	protected void removed() {
		System.out.println("Node" + parent.next + "has been removed");
	}

	protected boolean empty() {
		return parent.next == null;
	}

	////// FILTER ///////

	public interface Filter {
		boolean match(Node n);
	}

	protected Node find(Filter filter) {
		Iterator it = new Iterator(parent);
		while (it.hasNext()) {
			filter.match(parent);
		}
		return null;

	}

	protected int findSome(Node[] array, Filter f) {
		return 0;

	}

	protected Node[] findAll(Filter f) {
		return null;
	}

}
