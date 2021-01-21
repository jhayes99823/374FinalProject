import java.util.List;

import models.Machine;
import models.Order;

public class DatabaseConnection {
	private static final String CONNECTION = "TEST_CONN";
	
	public DatabaseConnection() {
		
	}
	
	public List<Machine> getMachinesForOrder(Order order) {
		return null;
	}

	public static String getConnection() {
		return CONNECTION;
	}
}
