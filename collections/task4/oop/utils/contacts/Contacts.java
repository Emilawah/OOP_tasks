package oop.utils.contacts;

import oop.collections.ICollection.Iterator;

import oop.collections.IList;
import oop.contacts.IContacts;
import oop.utils.collections.HashTable;
import oop.utils.collections.LinkedList;
import oop.collections.IMap;

public class Contacts implements IContacts{

	private IMap m_contacts;
	
	public Contacts() {
		m_contacts = new HashTable();
	}
	
	
	@Override
	public IContact get(IPhoneNumber phone) {
		return (IContact)m_contacts.get(phone);
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
		return new Name(last,first);
	}

	@Override
	public IPhoneNumber newPhoneNumber(int country, String number) {
		return new PhoneNumber(country,number);
	}

	@Override
	public IValue newValue(String value) {
		return new Value(value);
	}
	
	private boolean matches(String value, String filter) {
	    if (filter.equals("*")) {
	        return true;
	    } else if (filter.startsWith("*") && filter.endsWith("*")) {
	        String inner = filter.substring(1, filter.length() - 1);
	        return value.contains(inner);
	    } else if (filter.startsWith("*")) {
	        String suffix = filter.substring(1);
	        return value.endsWith(suffix);
	    } else if (filter.endsWith("*")) {
	        String prefix = filter.substring(0, filter.length() - 1);
	        return value.startsWith(prefix);
	    } else {
	        return value.equals(filter);
	    }
	}

}
