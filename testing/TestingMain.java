package testing;
import controller.ConcreteSelectionBehavior;
import controller.RequestManager;

public class TestingMain {

	private static final String[] jsons = {
		"{\r\n" + 
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
		"}",
		"{\r\n" + 
		"  \"order\": { \"orderID\": 2,\r\n" + 
		"            \"address\": {\r\n" + 
		"                  \"street\": \"200 N Main\",\r\n" + 
		"                  \"ZIP\": 47803\r\n" + 
		"            },\r\n" + 
		"            \"drink\": \"Expresso\"\r\n" + 
		"            }\r\n" + 
		"}",
		"{\r\n" + 
		"  \"order\": { \"orderID\": 3,\r\n" + 
		"            \"address\": {\r\n" + 
		"                  \"street\": \"200 N Main\",\r\n" + 
		"                  \"ZIP\": 47803\r\n" + 
		"            },\r\n" + 
		"            \"drink\": \"Pumpkin Spice\",\r\n" + 
		"            \"condiments\": [\r\n" + 
		"                {\"name\": \"Cream\", \"qty\": 1}\r\n" + 
		"            ]\r\n" + 
		"            }\r\n" + 
		"}"
	};
	
	public static void main(String[] args) {
		RequestManager manager = new RequestManager();
		manager.setSelectionBehavior(new ConcreteSelectionBehavior());
		String response = manager.handleRequest(jsons[1]);
		System.out.println(response);
	}

}
