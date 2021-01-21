import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JSONManager {

	public static Order parseOrderInput(String json) {
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
	
	public static ControllerResponse parseControllerResponse(String json) {
		JSONObject jsonObject = (JSONObject) JSONValue.parse(json);
		jsonObject = (JSONObject) jsonObject.get("drinkresponse");
		int orderID = (int)(long)jsonObject.get("orderID");
		int status = (int)(long)jsonObject.get("status");
		String errordesc = (String)jsonObject.get("errordesc");
		Object obj = jsonObject.get("errorcode");
		int errorcode = obj==null ? -1 : (int) obj;
		return new ControllerResponse(orderID, status, errordesc, errorcode);
	}
	public static String createCommmandStream(Order order, Machine machine) {
		JSONObject jsonCommand = new JSONObject();
		JSONObject commandBody = new JSONObject();
		commandBody.put("controller_id", machine.getController().getID());
		commandBody.put("coffee_machine_id", machine.getID());
		commandBody.put("orderID", order.getOrderID());
		commandBody.put("DrinkName", order.getDrink());
		commandBody.put("Requesttype", "SOMETHING"); //NEEDS TO BE FIXED
		JSONArray condimentsArray = new JSONArray();
		for(int i = 0; i < order.getCondiments().size(); i++) {
			JSONObject condiment = new JSONObject();
			condiment.put("Name",order.getCondiment(i).getName());
			condiment.put("qty", order.getCondiment(i).getQuantity());
			condimentsArray.add(condiment);
		}
		commandBody.put("Options:", condimentsArray);
		jsonCommand.put("command", commandBody);
		return jsonCommand.toString();
	}
	
	public static String createAppResponse(ControllerResponse controllerResponse, Machine machine) {
		JSONObject jsonResponse = new JSONObject();
		JSONObject responseBody = new JSONObject();
		responseBody.put("orderID", controllerResponse.getOrderID());
		responseBody.put("coffee_machine_id", machine.getID());
		responseBody.put("status", controllerResponse.getStatus());
		if(controllerResponse.getStatus()==0) {
			responseBody.put("status-message", "Your coffee has been prepared with your desired options.");
		}else {
			responseBody.put("status-message", "Your coffee order has been cancelled.");
			responseBody.put("error-message", controllerResponse.getErrorDesc());
		}
		jsonResponse.put("user-response", responseBody);
		return jsonResponse.toString();
	}
}
