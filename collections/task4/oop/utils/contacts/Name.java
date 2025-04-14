package oop.utils.contacts;
import oop.contacts.IContacts.IName;

import oop.contacts.IContacts.IValue;

public class Name implements IName{

	private String first;
	private String last;
	
	public Name(String last, String first) {
		this.first = first;
		this.last = last;
	}
	
	@Override
	public boolean equals(IValue o) {
		if(o==null) {
			return false;
		}
		IName name = (IName)o;
		return name.first().equals(first) && name.last().equals(last);
	}

	@Override
	public String value() { 
		return toString();
	}

	@Override
	public String last() {
		return last;
	}

	@Override
	public String first() {
		return first;
	}
	
	@Override
    public String toString() {
        return last + " " + first;
    }

}
