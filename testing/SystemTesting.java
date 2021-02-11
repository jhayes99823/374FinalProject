package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import controller.AddressSelectionBehavior;
import controller.ControllerManager;
import controller.JSONManager;
import controller.RequestManager;
import models.Address;
import models.Capability;
import models.Controller;
import models.ControllerResponse;
import models.ControllerResponseFactory;
import models.ControllerType;
import models.Drink;
import models.DrinkFactory;
import models.Machine;

class SystemTesting {

	@Test
	void testRequestSteps() {
		// Run Request manager manually
		RequestManager requestManager = new RequestManager();
		requestManager.addSelectionStrategy(new AddressSelectionBehavior());
		String inputString = "{\r\n" + 
				"  \"order\": { \"orderID\": 2,\r\n" + 
				"            \"address\": {\r\n" + 
				"                  \"street\": \"200 N Main\",\r\n" + 
				"                  \"ZIP\": 47803\r\n" + 
				"            },\r\n" + 
				"            \"drink\": \"Espresso\"\r\n" + 
				"            }\r\n" + 
				"}";
		Drink drink = DrinkFactory.parseOrder(inputString);
		List<Machine> machines = requestManager.performSelection(drink);
		Machine selectedMachine = machines.get(0);
		String commandStream = JSONManager.createCommmandStream(drink, selectedMachine);
		String controllerResponseString = ControllerManager.dispatchCommand(commandStream, drink.getOrderID());
		ControllerResponse controllerResponse = ControllerResponseFactory.parseResponse(controllerResponseString);
		String appResponse = JSONManager.createAppResponse(controllerResponse, selectedMachine);
		// Setup expected results
		Drink expectedDrink = new Drink(2, new Address("200 N Main", 47803), "Espresso");
		List<Machine> expectedMachines = new ArrayList<Machine>();
		Controller controller1 = new Controller(1, ControllerType.Advanced, new Address("200 N Main", 47803));
		List<Capability> machine1Capabilities = new ArrayList<Capability>();
		machine1Capabilities.add(Capability.Simple);
		machine1Capabilities.add(Capability.Automated);
		Machine machine1 = new Machine(1, controller1, machine1Capabilities);
		expectedMachines.add(machine1);
		List<Capability> machine2Capabilities = new ArrayList<Capability>();
		machine2Capabilities.add(Capability.Simple);
		machine2Capabilities.add(Capability.Automated);
		machine2Capabilities.add(Capability.Programmable);
		Machine machine2 = new Machine(2, controller1, machine2Capabilities);
		expectedMachines.add(machine2);
		Machine expectedMachine = expectedMachines.get(0);
		String expectedCommand = "{\"command\":{\"controller_id\":1,\"orderID\":2,\"coffee_machine_id\":1,\"DrinkName\":\"Espresso\",\"Requesttype\":\"Simple\"}}";
		String expectedResponseString = "{\n"
				+ "  \"drinkresponse\": {\n"
				+ "    \"orderID\": 2,\n"
				+ "    \"status\": 1,\n"
				+ "    \"errordesc\": \"Out of milk, drink cancelled\",\n"
				+ "    \"errorcode\": 2\n"
				+ "  }\n"
				+ "}";
		ControllerResponse expectedResponse = new ControllerResponse(2, 1, "Out of milk, drink cancelled", 2);
		String expectedAppResponse = "{\"user-response\":{\"status-message\":\"Your coffee order has been cancelled.\",\"error-message\":\"Out of milk, drink cancelled\",\"orderID\":2,\"coffee_machine_id\":1,\"status\":1}}";
		// Compare values
		assertEquals(expectedDrink.toJSON(), drink.toJSON());
		for(int i = 0; i < expectedMachines.size(); i++) {
			assertEquals(expectedMachines.get(i), machines.get(i));
		}
		assertEquals(expectedMachine, selectedMachine);
		assertEquals(expectedCommand, commandStream);
		assertEquals(expectedResponseString, controllerResponseString);
		assertEquals(expectedResponse, controllerResponse);
		assertEquals(expectedAppResponse, appResponse);
	}
	
	@Test
	void testRequestOverall() {
		// Run Request manager manually
		RequestManager requestManager = new RequestManager();
		requestManager.addSelectionStrategy(new AddressSelectionBehavior());
		String inputString = "{\r\n" + 
				"  \"order\": { \"orderID\": 2,\r\n" + 
				"            \"address\": {\r\n" + 
				"                  \"street\": \"200 N Main\",\r\n" + 
				"                  \"ZIP\": 47803\r\n" + 
				"            },\r\n" + 
				"            \"drink\": \"Espresso\"\r\n" + 
				"            }\r\n" + 
				"}";
		String actualResponse = requestManager.handleRequest(inputString);
		String expectedResponse = "{\"user-response\":{\"status-message\":\"Your coffee order has been cancelled.\",\"error-message\":\"Out of milk, drink cancelled\",\"orderID\":2,\"coffee_machine_id\":1,\"status\":1}}";
		// Compare values
		assertEquals(expectedResponse, actualResponse);
	}

}
