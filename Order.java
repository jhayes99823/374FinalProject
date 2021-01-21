import java.util.List;

public class Order {
	private long orderID;
	private Address address;
	private String drink;
	private List<Condiment> condiments;
	
	public Order(long orderID /*, Address address, String drink, List<Condiment> condiments */) {
		this.orderID = orderID;
	}
	
	public long getOrderID() {
		return this.orderID;
	}
}
