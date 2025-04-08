package oop.utils.contacts;

import oop.collections.ICollection.Iterator;
import oop.collections.IList;
import oop.contacts.IContacts;

public class Contacts implements IContacts{

	@Override
	public IContact get(IPhoneNumber phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(IContact c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IContact add(IName name, IPhoneNumber phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(IContact c, IList names, IList values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator select(String name, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IName newName(String last, String first) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPhoneNumber newPhoneNumber(int country, String number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IValue newValue(String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
