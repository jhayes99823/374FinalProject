package models;


public class Address {
	private String street;
	private int ZIP;
	
	public Address(String street, int ZIP) {
		this.street = street;
		this.ZIP = ZIP;
	}
	
	public String getStreet() {
		return this.street;
	}
	
	public int getZIP() {
		return this.ZIP;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (ZIP != other.ZIP)
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}
	
	
}
