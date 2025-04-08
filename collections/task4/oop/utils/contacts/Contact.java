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

	public Contact(IName name, IPhoneNumber pn) {

		m_contacts = new HashTable();
		m_contacts.put("name", name);
		m_contacts.put("phone", pn);
	}

	@Override
	public IPhoneNumber phone() {
		return (IPhoneNumber) m_contacts.get("phone");
	}

	@Override
	public IName name() {
		return (IName) m_contacts.get("name");
	}

	@Override
	public Iterator fields() {
		return m_contacts.iterator();
	}

	@Override
	public IValue field(String name) {
		return (IValue) m_contacts.get(name);
	}

	@Override
	public void field(String name, IValue value) {
		m_contacts.put(name, value);

	}

}
