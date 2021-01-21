
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
}
