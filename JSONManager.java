import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JSONManager {

	public Order parseOrderInput(String json) {
		JSONObject jsonObject = (JSONObject) JSONValue.parse(json);
		jsonObject = (JSONObject) jsonObject.get("order");
		Order order = new Order((long)jsonObject.get("orderID"));
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
		System.out.println(manager.parseOrderInput("{\r\n" + 
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
				"}").getOrderID());
	}
}
