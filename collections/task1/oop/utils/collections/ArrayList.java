package oop.utils.collections;

import oop.collections.ICollection;
import oop.collections.IList;

public class ArrayList implements IList {
	
	public static class Iterator implements ICollection.Iterator{
		
		private int index = 0;
		private Object[] m_obj;
		private int size;
		
		public Iterator(ArrayList m_list){
			this.m_obj = m_list.m_obj;
			this.size = m_list.size;
		}
		
		@Override
		public boolean hasNext() {
			return index < size;
		}

	    @Override
	    public Object next() {
	    	if(!hasNext()) {
	    		throw new IndexOutOfBoundsException("No more elements");
	    	}
	    	return m_obj[index++];
	    }
	}
	

	private Object[] m_obj;
	private int size;

	/**
	 * Constructs an empty list.
	 */
	public ArrayList() {
		this.m_obj = new Object[20];
		this.size = 0;
	}

	/**
	 * Constructs a list, initialized with the elements from the given array.
	 */
	public ArrayList(Object array[]) {
		this.m_obj = array;
		this.size = array.length;
	}

	/**
	 * Constructs a list, initialized with the elements from the given list.
	 */
	public ArrayList(ArrayList v) {
		this.m_obj = v.m_obj;
		this.size = v.size;
	}

	/**
	 * Constructs a list, initialized with the elements from the given collection.
	 */
	public ArrayList(ICollection c) {
		this.m_obj = new Object[c.length()];
		c.toArray(m_obj);
		this.size = c.length();
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
		} else {
			return m_obj[index];
		}
	}

	@Override
	public Object updateAt(int index, Object niu) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index");

		} else {
			Object elem = m_obj[index];
			elem = niu;
			return elem;
		}
	}

	@Override
	public void insertAt(int index, Object elem) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		if (index >= size) {
			size = index + 1;
		}
		m_obj[index] = elem;

	}

	@Override
	public Object removeAt(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index");

		} else {
			Object obj_suppr = m_obj[index];
			for (int i = index; i < size - 1; i++) {
				m_obj[i] = m_obj[i + 1];
			}
			size--;
			return obj_suppr;
		}
	}

	@Override
	public boolean remove(Object elem) {
		for(int i = 0 ; i < size ; i++) {
			if(m_obj[i].equals(elem)) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean contains(Object elem) {
		for (int i = 0 ; i < size ; i++) {
			if(m_obj[i].equals(elem)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void toArray(Object elems[]) {
		for(int i = 0 ; i < size ; i++) {
			m_obj[i] = elems[i];
		}
	}
	

}
