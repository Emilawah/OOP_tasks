package oop.utils.collections;

import oop.collections.ICollection;
import oop.collections.IList;

public class LinkedList implements IList {

	private static class Node {

		Object value;
		Node next;

		Node(Object value) {
			this.value = value;
			this.next = null;
		}
	}

	public static class Iterator implements ICollection.Iterator {

		private Node cell;

		public Iterator(LinkedList m_list) {
			this.cell = m_list.head;
		}

		@Override
		public boolean hasNext() {
			return cell != null;
		}

		@Override
		public Object next() {
			if (!hasNext()) {
				throw new IndexOutOfBoundsException("No more elements");
			}
			Object elem = cell.value;
			cell = cell.next;
			return elem;
		}
	}

	// Fields
	private Node head;
	private int size;

	/**
	 * Constructs an empty list.
	 */
	public LinkedList() {
		this.head = null;
		this.size = 0;
	}

	/**
	 * Constructs a list, initialized with the elements from the given array.
	 */
	public LinkedList(Object array[]) {
		for (Object obj : array) {
			insertAt(size, obj);
		}
	}

	/**
	 * Constructs a list, initialized with the elements from the given list.
	 */
	public LinkedList(LinkedList v) {
		Node cell = v.head;
		while (cell != null) {
			insertAt(size, cell.value);
			cell = cell.next;
		}
	}

	/**
	 * Constructs a list, initialized with the elements from the given collection.
	 */
	public LinkedList(ICollection c) {
		Object[] m_obj = new Object[c.length()];
		c.toArray(m_obj);
		for (Object obj : m_obj) {
			insertAt(size, obj);
		}
	}

	@Override
	public Iterator iterator() {
		return new Iterator(this);
	}

	@Override
	public int length() {
		return size;
	}

	@Override
	public Object elementAt(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		Node cell = head;
		for (int i = 0; i < index; i++) {
			cell = cell.next;
		}
		return cell.value;

	}

	@Override
	public Object updateAt(int index, Object niu) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid Index");
		}
		Node cell = head;
		for (int i = 0; i < index; i++) {
			cell = cell.next;
		}
		Object elem = cell.value;
		elem = niu;
		cell.value = elem;
		return elem;
	}

	@Override
	public void insertAt(int index, Object elem) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Invalid Index");
		}

		// Cas où index > size
		while (size < index) {
			insertAt(size, null);
		}

		Node cell = new Node(elem);
		if (index == 0) {
			cell.next = head;
			head = cell;
		} else {
			Node current = head;
			for (int i = 0; i < index - 1; i++) {
				// on se place à la position où on veut insérer
				current = current.next;
			}
			cell.next = current.next;
			current.next = cell;
		}
		size++;

	}

	@Override
	public Object removeAt(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid Index");
		}
		
		Object obj_suppr;
		if (index == 0) {
			obj_suppr = head.value;
			head = head.next;
		} else {
			Node cell = head;
			for (int i = 0; i < index - 1; i++) {
				cell = cell.next;
			}
			Node toRemove = cell.next;
			obj_suppr = toRemove.value;
			cell.next = toRemove.next;
		
		}
		size--;
		return obj_suppr;
	}

	@Override
	public boolean remove(Object elem) {
		if (head == null) {
			return false;
		}
		if (head.value.equals(elem)) {
			head = head.next;
			size--;
			return true;
		}
		Node cell = head;
		while (cell.next != null) {
			if (cell.next.value.equals(elem)) {
				cell.next = cell.next.next;
				size--;
				return true;
			}
			cell = cell.next;
		}
		return false;
	}

	@Override
	public boolean contains(Object elem) {
		Node cell = head;
		while (cell != null) {
			if (cell.value.equals(elem)) {
				return true;
			}
			cell = cell.next;
		}
		return false;
	}

	@Override
	public void toArray(Object[] elems) {
		head = null;
		size = 0;
		for (Object obj : elems) {
			insertAt(size, obj);
		}
	}

}