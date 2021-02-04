package models;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public abstract class OrderFactory {

	private static final String ORDER_ID_KEY = "orderID";
	private static final String ORDER_ADDRESS_KEY = "address";
	private static final String ORDER_CONDIMENTS_KEY = "condiments";
	
	private static final String ADDRESS_STREET_KEY = "street";
	private static final String ADDRESS_ZIP_KEY = "ZIP";
	
	private static final String CONDIMENT_NAME_KEY = "name";
	private static final String CONDIMENT_QUANTITY_KEY = "qty";
	
	public static Order createOrder(JSONObject orderInput) {
		int ID = ((Long)orderInput.get(ORDER_ID_KEY)).intValue();
		JSONObject addressInput = (JSONObject)orderInput.get(ORDER_ADDRESS_KEY);
		Address address = OrderFactory.createAddress(addressInput);
		Drink drink = DrinkFactory.createDrink(orderInput);
		JSONArray condimentsArray = (JSONArray)orderInput.get(ORDER_CONDIMENTS_KEY);
		List<Condiment> condiments = OrderFactory.createCondiments(condimentsArray);
		return new Order(ID, address, drink, condiments);
	}
	
	public static Address createAddress(JSONObject addressInput) {
		String street = (String)addressInput.get(ADDRESS_STREET_KEY);
		int ZIP = ((Long)addressInput.get(ADDRESS_ZIP_KEY)).intValue();
		return new Address(street, ZIP);
	}
	
	public static List<Condiment> createCondiments(JSONArray condimentsArray) {
		List<Condiment> condiments = new ArrayList<Condiment>(condimentsArray.size());
		for(int i = 0; i < condimentsArray.size(); i++) {
			JSONObject condimentInput = (JSONObject)condimentsArray.get(i);
			Condiment condiment = OrderFactory.createCondiment(condimentInput);
			condiments.add(condiment);
		}
		return condiments;
	}
	
	public static Condiment createCondiment(JSONObject condimentInput) {
		String name = (String)condimentInput.get(CONDIMENT_NAME_KEY);
		int quantity = ((Long)condimentInput.get(CONDIMENT_QUANTITY_KEY)).intValue();
		return new Condiment(name, quantity);
	}
	
}
