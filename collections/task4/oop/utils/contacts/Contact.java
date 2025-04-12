package oop.utils.contacts;

import oop.collections.ICollection.Iterator;
import oop.contacts.IContacts.IContact;
import oop.contacts.IContacts.IName;
import oop.contacts.IContacts.IPhoneNumber;
import oop.contacts.IContacts.IValue;
import oop.utils.collections.HashTable;
import oop.collections.IMap;

public class Contact implements IContact {

	private IMap m_contacts;
	private IPhoneNumber pn;
	private IName name;
	
	
	public Contact(IName name, IPhoneNumber pn) {
		this.name=name;
		this.pn=pn;
		m_contacts = new HashTable();
		m_contacts.put("name", name);
		m_contacts.put("phone", pn);
		
	}

	@Override
	public IPhoneNumber phone() {
		return pn;
	}

	@Override
	public IName name() {
		return name;
	}

	@Override
	public Iterator fields() {
		return m_contacts.keys();
	}

	@Override
	public IValue field(String name) {
		IValue value = (IValue) m_contacts.get(name);
		if (value == null) {
			throw new IllegalArgumentException("Field '" + name + "' not found in contact.");
		}
		return value;
	}


	@Override
	public void field(String name, IValue value) {
		m_contacts.put(name, value);
		if(name.equals("name")) {
			this.name = (IName) value;
		}
		if(name.equals("phone")) {
			this.pn = (IPhoneNumber)value;
		}

	}

}
