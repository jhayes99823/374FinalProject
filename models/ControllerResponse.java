package models;

public class ControllerResponse {
	private int orderID;
	private int status;
	private String errordesc;
	private int errorcode;

	public ControllerResponse(int orderID, int status, String errordesc, int errorcode) {
		this.orderID = orderID;
		this.status = status;
		this.errordesc = errordesc;
		this.errorcode = errorcode;
	}

	public int getOrderID() {
		return this.orderID;
	}

	public int getStatus() {
		return this.status;
	}

	public String getErrorDesc() {
		return this.errordesc;
	}

	public int getErrorCode() {
		return this.errorcode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ControllerResponse other = (ControllerResponse) obj;
		if (this.status == 0) {
			return (this.orderID == other.orderID) && (this.status == other.status);
		} else {
			return (this.orderID == other.orderID) && (this.status == other.status)
					&& (this.errorcode == other.errorcode) && (this.errordesc.equals(other.errordesc));
		}
	}
}
