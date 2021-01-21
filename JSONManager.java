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
		for(int i = 0; i < condimentsArray.size(); i++) {
			String condimentName = (String)((JSONObject)condimentsArray.get(i)).get("name");
			int condimentQty = (int)(long)((JSONObject)condimentsArray.get(i)).get("qty");
			condiments.add(new Condiment(condimentName, condimentQty));
		}
		Order order = new Order(orderID, address, drink, condiments);
		return order;
	}
	
	public ControllerResponse parseControllerResponse(String json) {
		return null;
	}
	public String createCommmandStream(Order order, Machine machine) {
		return null;
	}
	
	public String createAppResponse(ControllerResponse controllerResponse, Machine machine) {
		return null;
	}
	
	public static void main(String args[]) {
		JSONManager manager = new JSONManager();
		Order order = manager.parseOrderInput("{\r\n" + 
				"  \"order\": { \"orderID\": 1,\r\n" + 
				"            \"address\": {\r\n" + 
				"                  \"street\": \"200 N Main\",\r\n" + 
				"                  \"ZIP\": 47803\r\n" + 
				"            },\r\n" + 
				"            \"drink\": \"Americano\",\r\n" + 
				"            \"condiments\": [\r\n" + 
				"                {\"name\": \"Sugar\", \"qty\": 1},\r\n" + 
				"                {\"name\": \"Cream\", \"qty\": 2}\r\n" + 
				"            ]\r\n" + 
				"            }\r\n" + 
				"}");
		System.out.println(order.getOrderID());
		System.out.println(order.getAddress().getStreet());
		System.out.println(order.getAddress().getZIP());
		System.out.println(order.getDrink());
		System.out.println(order.getCondiment(0).getName());
		System.out.println(order.getCondiment(1).getQuantity());
	}
}
