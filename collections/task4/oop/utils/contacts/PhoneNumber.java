package oop.utils.contacts;
import oop.contacts.IContacts.IPhoneNumber;
import oop.contacts.IContacts.IValue;

public class PhoneNumber implements IPhoneNumber{

	private String number;
	private int codeCountry;
	
	public PhoneNumber(int codeCountry, String number) {
		this.codeCountry = codeCountry;
		this.number = number;
	}
	
	@Override
	public String toString() {
	    return value();
	}
	
	@Override
	public boolean equals(IValue o) {
		if (o == null) {
			return false;
		}
		IPhoneNumber pn = (IPhoneNumber) o;
		return equals(pn); // méthode faite en dessous
	}

	@Override
	public String value() {
		return "(" + codeCountry + ") " + number;
	}

	@Override
	public int country() {
		return codeCountry;
	}

	@Override
	public String number() {
		return number;
	}

	@Override
	public boolean equals(IPhoneNumber o) {
		if (o == null) {
			return false;
		}
		if(o.country() == 0 || codeCountry == 0) {
			return number.equals(o.number());
		}
		return codeCountry == o.country() && number.equals(o.number());
	}

}
