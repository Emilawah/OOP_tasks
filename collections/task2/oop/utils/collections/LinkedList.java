package oop.utils.collections;

import oop.collections.ICollection;
import oop.collections.IList;

public class LinkedList implements IList {
	
	public static class Iterator implements ICollection.Iterator{
		
		public Iterator(LinkedList m_list) {
			
		}
		
		@Override
		public boolean hasNext() {
			return false;
		}
		
		@Override
		public Object next() {
			return 0;
		}
	}

	/**
	 * Constructs an empty list.
	 */
	public LinkedList() {

	}

	/**
	 * Constructs a list, initialized with the elements from the given array.
	 */
	public LinkedList(Object array[]) {

	}

	/**
	 * Constructs a list, initialized with the elements from the given list.
	 */
	public LinkedList(LinkedList v) {

	}

	/**
	 * Constructs a list, initialized with the elements from the given collection.
	 */
	public LinkedList(ICollection c) {

	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object elementAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object updateAt(int index, Object niu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertAt(int index, Object elem) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object removeAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object elem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object elem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void toArray(Object[] elems) {
		// TODO Auto-generated method stub

	}

}