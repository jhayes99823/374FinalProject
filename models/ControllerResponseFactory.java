package models;

import org.json.simple.JSONObject;

public abstract class ControllerResponseFactory {

	private static final String CONTROLLER_RESPONSE_ORDER_ID_KEY = "orderID";
	private static final String CONTROLLER_RESPONSE_STATUS_KEY = "status";
	private static final String CONTROLLER_RESPONSE_ERROR_DESCRIPTION_KEY = "errordesc";
	private static final String CONTROLLER_RESPONSE_ERROR_CODE_KEY = "errorcode";

	public static ControllerResponse createControllerResponse(JSONObject drinkResponse) {
		int orderID = ((Long) drinkResponse.get(CONTROLLER_RESPONSE_ORDER_ID_KEY)).intValue();
		int status = ((Long) drinkResponse.get(CONTROLLER_RESPONSE_STATUS_KEY)).intValue();
		String errordesc = "";
		int errorcode = -1;
		if (status != 0) {
			errordesc = (String)drinkResponse.get(CONTROLLER_RESPONSE_ERROR_DESCRIPTION_KEY);
			errorcode = ((Long)drinkResponse.get(CONTROLLER_RESPONSE_ERROR_CODE_KEY)).intValue();
		}
		return new ControllerResponse(orderID, status, errordesc, errorcode);
	}

}
