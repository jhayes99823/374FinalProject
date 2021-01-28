package controller;

import java.util.List;

import models.Machine;

public class ControllerManager {
	private static final int WAIT_TIME = 3;
	
	public static String getControllerInfo(int orderId) throws InterruptedException {
		Thread.sleep(1000);
		String response = null;
		switch(orderId) {
			case 1:
				response = "{\n"
						+ "  \"drinkresponse\": {\n"
						+ "    \"orderID\": 1,\n"
						+ "    \"status\": 0\n"
						+ "  }\n"
						+ "}";
				break;
			case 2:
				response = "{\n"
						+ "  \"drinkresponse\": {\n"
						+ "    \"orderID\": 2,\n"
						+ "    \"status\": 1,\n"
						+ "    \"errordesc\": \"Out of milk, drink cancelled\",\n"
						+ "    \"errorcode\": 2\n"
						+ "  }\n"
						+ "}";
				break;
			case 3:
				Thread.sleep(5000);
				response = "{\n"
						+ "  \"drinkresponse\": {\n"
						+ "    \"orderID\": 3,\n"
						+ "    \"status\": 1,\n"
						+ "    \"errordesc\": \"Machine jammed.\",\n"
						+ "    \"errorcode\": 26\n"
						+ "  }\n"
						+ "}\n"
						+ "";
				break;
		}
		return response;
	}
	
	public static String dispatchCommand(String commandString, int orderId) {
		String[] output = {null};
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					output[0] = getControllerInfo(orderId);
				} catch (InterruptedException e) {
				}
			}
		});
		thread.start();
		long startTime = System.currentTimeMillis();
		while(startTime+WAIT_TIME*1000 >= System.currentTimeMillis()) {
			if(output[0]!=null) {
				break;
			}
		}
		thread.interrupt();
		if(output[0]==null) {
			output[0] = "{\"drinkresponse\":{\"orderID\":"+orderId+",\"status\":1,\"errordesc\":\"Machine not responding.\",\"errorcode\":1}}";
		}
		return output[0];
	}
	
	public static List<Machine> getMachinesByQueueSizes() {
		
		return null;
	}
}
