package oop.utils.contacts;

import oop.contacts.IContacts.IValue;

public class Value implements IValue {

	private String value;

	public Value(String val) {
		this.value = val;
	}

	@Override
	public boolean equals(IValue o) {
		if (o == null) {
			return false;
		}
		return value.equals(o.value());
	}

	@Override
	public String value() {
		return value;
	}

}
