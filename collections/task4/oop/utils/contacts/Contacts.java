package oop.utils.contacts;

import oop.collections.ICollection.Iterator;

import oop.collections.IList;
import oop.contacts.IContacts;

import oop.utils.collections.HashTable;
import oop.utils.collections.LinkedList;
import oop.collections.IMap;

public class Contacts implements IContacts {

	private IMap m_contacts;

	public Contacts() {
		m_contacts = new HashTable();
	}

	@Override
	public IContact get(IPhoneNumber phone) {
		return (IContact) m_contacts.get(phone);
	}

	@Override
	public void remove(IContact c) {
		m_contacts.remove(c.phone());
	}

	@Override
	public IContact add(IName name, IPhoneNumber phone) {
		if (m_contacts.get(phone) != null) {
			throw new IllegalArgumentException("Phone number already exists.");
		}
		IContact c = new Contact(name, phone);
		m_contacts.put(phone, c);
		return c;
	}

	@Override
	public void update(IContact c, IList names, IList values) {
		Iterator itNames = names.iterator();
		Iterator itValues = values.iterator();

		while (itNames.hasNext() && itValues.hasNext()) {
			String name = (String) itNames.next();
			IValue value = (IValue) itValues.next();
			c.field(name, value);
		}
	}

	@Override
	public Iterator select(String name, String filter) {
		IList result = new LinkedList(); // liste de résultats à retourner

		Iterator it = m_contacts.values(); // contactsMap = map de téléphone vers IContact
		while (it.hasNext()) {
			IContact contact = (IContact) it.next();
			IValue field = contact.field(name);

			if (field != null && matches(field.value(), filter)) {
				result.insertAt(result.length(), contact);
			}
		}

		return result.iterator();
	}

	@Override
	public IName newName(String last, String first) {
		return new Name(last, first);
	}

	@Override
	public IPhoneNumber newPhoneNumber(int country, String number) {
		return new PhoneNumber(country, number);
	}

	@Override
	public IValue newValue(String value) {
		return new Value(value);
	}

	private boolean matches(String value, String filter) {
		if (filter.equals("*")) {
			// renvoyer directement la chaine filtre
			return true;
		} else if (filter.startsWith("*") && filter.endsWith("*")) {
			// String : "*hello*" -> renvoie la sous chaine "hello"
			String s = filter.substring(1, filter.length() - 1);
			return value.contains(s);
		} else if (filter.startsWith("*")) {
			// String "*email" -> renvoie la sous chaine "email"
			String s1 = filter.substring(1);
			return value.endsWith(s1);
		} else if (filter.endsWith("*")) {
			// String "phone*" -> renvoie la sous chaine "phone"
			String s2 = filter.substring(0, filter.length() - 1);
			return value.startsWith(s2);
		} else {
			// renvoyer directement la chaine
			return value.equals(filter);
		}
	}

	public static class Contact implements IContact {

		private IMap m_contacts;
		private IPhoneNumber pn;
		private IName name;

		public Contact(IName name, IPhoneNumber pn) {
			this.name = name;
			this.pn = pn;
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
			if (name.equals("name")) {
				this.name = (IName) value;
			}
			if (name.equals("phone")) {
				this.pn = (IPhoneNumber) value;
			}

		}

	}

}
