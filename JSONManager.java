import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JSONManager {

	public Order parseOrderInput(String json) {
		JSONObject jsonObject = (JSONObject) JSONValue.parse(json);
		jsonObject = (JSONObject) jsonObject.get("order");
		int orderID = (int)(long)jsonObject.get("orderID");
		String streetAddress = (String)((JSONObject)jsonObject.get("address")).get("street");
		int zipAddress = (int)(long)((JSONObject)jsonObject.get("address")).get("ZIP");
		Address address = new Address(streetAddress, zipAddress);
		String drink = (String)jsonObject.get("drink");
		JSONArray condimentsArray = (JSONArray)jsonObject.get("condiments");
		List<Condiment> condiments = new ArrayList<Condiment>();
		if(condimentsArray!=null) {
			for (int i = 0; i < condimentsArray.size(); i++) {
				String condimentName = (String) ((JSONObject) condimentsArray.get(i)).get("name");
				int condimentQty = (int) (long) ((JSONObject) condimentsArray.get(i)).get("qty");
				condiments.add(new Condiment(condimentName, condimentQty));
			}
		}
		return new Order(orderID, address, drink, condiments);
	}
	
	public ControllerResponse parseControllerResponse(String json) {
		JSONObject jsonObject = (JSONObject) JSONValue.parse(json);
		jsonObject = (JSONObject) jsonObject.get("drinkresponse");
		int orderID = (int)(long)jsonObject.get("orderID");
		int status = (int)(long)jsonObject.get("status");
		String errordesc = (String)jsonObject.get("errordesc");
		Object obj = jsonObject.get("errorcode");
		int errorcode = obj==null ? -1 : (int) obj;
		return new ControllerResponse(orderID, status, errordesc, errorcode);
	}
	public String createCommmandStream(Order order, Machine machine) {
		return null;
	}
	
	public String createAppResponse(ControllerResponse controllerResponse, Machine machine) {
		return null;
	}
	
	public static void main(String args[]) {
		JSONManager manager = new JSONManager();
		ControllerResponse response = manager.parseControllerResponse("{\r\n" + 
				"  \"drinkresponse\": {\r\n" + 
				"    \"orderID\": 1,\r\n" + 
				"    \"status\": 0\r\n" + 
				"  }\r\n" + 
				"}");
		System.out.println(response.getOrderID());
		System.out.println(response.getStatus());
		System.out.println(response.getErrorDesc());
		System.out.println(response.getErrorCode());
	}
}
