
public class ControllerManager {
	public static String GetControllerInfo(int orderId) throws InterruptedException {
		Thread.sleep(2000);
		String response = null;
		switch(orderId) {
			case 1:
				response = "{\n"
						+ "  \"drinkresponse\": {\n"
						+ "    \"orderID\": 1,\n"
						+ "    \"status\": 0\n"
						+ "  }\n"
						+ "}";
			case 2:
				response = "{\n"
						+ "  \"drinkresponse\": {\n"
						+ "    \"orderID\": 2,\n"
						+ "    \"status\": 1,\n"
						+ "    \"errordesc\": \"Out of milk, drink cancelled\",\n"
						+ "    \"errorcode\": 2\n"
						+ "  }\n"
						+ "}";
			case 3:
				response = "{\n"
						+ "  \"drinkresponse\": {\n"
						+ "    \"orderID\": 3,\n"
						+ "    \"status\": 1,\n"
						+ "    \"errordesc\": \"Machine jammed.\",\n"
						+ "    \"errorcode\": 26\n"
						+ "  }\n"
						+ "}\n"
						+ "";
		}
		return response;
	}
}
