package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import controller.AddressSelectionBehavior;
import controller.IfAnySelectionBehavior;
import controller.MachineSelectionStrategy;
import controller.MachineTypeSelectionBehavior;
import controller.MinQueueSelectionBehavior;
import models.Address;
import models.Capability;
import models.CoffeeController;
import models.Condiment;
import models.Machine;
import models.Order;

class SelectionTesting {

	@Test
	void testAddressSelection() {
		List<Condiment> condiments = new ArrayList<Condiment>();
		Order order = new Order(0, new Address("123 Address St.", 11111), "Coffee", condiments);
		CoffeeController controllerA = new CoffeeController(0, "Simple", new Address("123 Address St.", 11111));
		CoffeeController controllerB = new CoffeeController(1, "Simple", new Address("Other Road St.", 55555));
		List<Machine> machines = new ArrayList<Machine>();
		List<Capability> capabilitiesSA = new ArrayList<Capability>();
		capabilitiesSA.add(Capability.Simple);
		capabilitiesSA.add(Capability.Automated);
		List<Capability> capabilitiesS = new ArrayList<Capability>();
		capabilitiesS.add(Capability.Simple);
		Machine machine0 = new Machine(0, controllerA, capabilitiesSA);
		Machine machine1 = new Machine(1, controllerA, capabilitiesS);
		Machine machine2 = new Machine(2, controllerB, capabilitiesSA);
		machines.add(machine0);
		machines.add(machine1);
		machines.add(machine2);
		
		MachineSelectionStrategy selection = new AddressSelectionBehavior();
		
		List<Machine> result = selection.selectMachines(order, machines);
		
		List<Machine> expectedResult = new ArrayList<Machine>();
		expectedResult.add(machine0);
		expectedResult.add(machine1);
		
		assertEquals(expectedResult.size(), result.size());
		for(int i = 0; i < expectedResult.size(); i++) {
			assertEquals(expectedResult.get(i), result.get(i));
		}
	}

	@Test
	void testTypeSelectionSimple() {
		List<Condiment> condiments = new ArrayList<Condiment>();
		Order order = new Order(0, new Address("123 Address St.", 11111), "Coffee", condiments);
		CoffeeController controllerA = new CoffeeController(0, "Simple", new Address("123 Address St.", 11111));
		CoffeeController controllerB = new CoffeeController(1, "Simple", new Address("Other Road St.", 55555));
		List<Machine> machines = new ArrayList<Machine>();
		List<Capability> capabilitiesSA = new ArrayList<Capability>();
		capabilitiesSA.add(Capability.Simple);
		capabilitiesSA.add(Capability.Automated);
		List<Capability> capabilitiesS = new ArrayList<Capability>();
		capabilitiesS.add(Capability.Simple);
		Machine machine0 = new Machine(0, controllerA, capabilitiesSA);
		Machine machine1 = new Machine(1, controllerA, capabilitiesS);
		Machine machine2 = new Machine(2, controllerB, capabilitiesSA);
		machines.add(machine0);
		machines.add(machine1);
		machines.add(machine2);
		
		MachineSelectionStrategy selection = new MachineTypeSelectionBehavior();
		
		List<Machine> result = selection.selectMachines(order, machines);
		
		List<Machine> expectedResult = new ArrayList<Machine>();
		expectedResult.add(machine0);
		expectedResult.add(machine1);
		expectedResult.add(machine2);
		
		assertEquals(expectedResult.size(), result.size());
		for(int i = 0; i < expectedResult.size(); i++) {
			assertEquals(expectedResult.get(i), result.get(i));
		}
	}
	
	@Test
	void testTypeSelectionAutomated() {
		List<Condiment> condiments = new ArrayList<Condiment>();
		condiments.add(new Condiment("Cream", 1));
		Order order = new Order(0, new Address("123 Address St.", 11111), "Coffee", condiments);
		CoffeeController controllerA = new CoffeeController(0, "Simple", new Address("123 Address St.", 11111));
		CoffeeController controllerB = new CoffeeController(1, "Simple", new Address("Other Road St.", 55555));
		List<Machine> machines = new ArrayList<Machine>();
		List<Capability> capabilitiesSA = new ArrayList<Capability>();
		capabilitiesSA.add(Capability.Simple);
		capabilitiesSA.add(Capability.Automated);
		List<Capability> capabilitiesS = new ArrayList<Capability>();
		capabilitiesS.add(Capability.Simple);
		Machine machine0 = new Machine(0, controllerA, capabilitiesSA);
		Machine machine1 = new Machine(1, controllerA, capabilitiesS);
		Machine machine2 = new Machine(2, controllerB, capabilitiesSA);
		machines.add(machine0);
		machines.add(machine1);
		machines.add(machine2);
		
		MachineSelectionStrategy selection = new MachineTypeSelectionBehavior();
		
		List<Machine> result = selection.selectMachines(order, machines);
		
		List<Machine> expectedResult = new ArrayList<Machine>();
		expectedResult.add(machine0);
		expectedResult.add(machine2);
		
		assertEquals(expectedResult.size(), result.size());
		for(int i = 0; i < expectedResult.size(); i++) {
			assertEquals(expectedResult.get(i), result.get(i));
		}
	}
	
	@Test
	void testQueueSelectionEmpty() {
		List<Condiment> condiments = new ArrayList<Condiment>();
		Order order = new Order(0, new Address("123 Address St.", 11111), "Coffee", condiments);
		CoffeeController controllerA = new CoffeeController(0, "Simple", new Address("123 Address St.", 11111));
		CoffeeController controllerB = new CoffeeController(1, "Simple", new Address("Other Road St.", 55555));
		List<Machine> machines = new ArrayList<Machine>();
		List<Capability> capabilitiesSA = new ArrayList<Capability>();
		capabilitiesSA.add(Capability.Simple);
		capabilitiesSA.add(Capability.Automated);
		List<Capability> capabilitiesS = new ArrayList<Capability>();
		capabilitiesS.add(Capability.Simple);
		Machine machine0 = new Machine(0, controllerA, capabilitiesSA);
		Machine machine1 = new Machine(1, controllerA, capabilitiesS);
		Machine machine2 = new Machine(2, controllerB, capabilitiesSA);
		machines.add(machine0);
		machines.add(machine1);
		machines.add(machine2);
		
		MachineSelectionStrategy selection = new MinQueueSelectionBehavior();
		
		List<Machine> result = selection.selectMachines(order, machines);
		
		List<Machine> expectedResult = new ArrayList<Machine>();
		expectedResult.add(machine0);
		expectedResult.add(machine1);
		expectedResult.add(machine2);
		
		assertEquals(expectedResult.size(), result.size());
		for(int i = 0; i < expectedResult.size(); i++) {
			assertEquals(expectedResult.get(i), result.get(i));
		}
	}
	
	@Test
	void testQueueSelectionOneUsed() {
		List<Condiment> condiments = new ArrayList<Condiment>();
		Order order = new Order(0, new Address("123 Address St.", 11111), "Coffee", condiments);
		CoffeeController controllerA = new CoffeeController(0, "Simple", new Address("123 Address St.", 11111));
		CoffeeController controllerB = new CoffeeController(1, "Simple", new Address("Other Road St.", 55555));
		List<Machine> machines = new ArrayList<Machine>();
		List<Capability> capabilitiesSA = new ArrayList<Capability>();
		capabilitiesSA.add(Capability.Simple);
		capabilitiesSA.add(Capability.Automated);
		List<Capability> capabilitiesS = new ArrayList<Capability>();
		capabilitiesS.add(Capability.Simple);
		Machine machine0 = new Machine(0, controllerA, capabilitiesSA);
		machine0.setNumberOfOrder(1);
		Machine machine1 = new Machine(1, controllerA, capabilitiesS);
		Machine machine2 = new Machine(2, controllerB, capabilitiesSA);
		machines.add(machine0);
		machines.add(machine1);
		machines.add(machine2);
		
		MachineSelectionStrategy selection = new MinQueueSelectionBehavior();
		
		List<Machine> result = selection.selectMachines(order, machines);
		
		List<Machine> expectedResult = new ArrayList<Machine>();
		expectedResult.add(machine1);
		expectedResult.add(machine2);
		
		assertEquals(expectedResult.size(), result.size());
		for(int i = 0; i < expectedResult.size(); i++) {
			assertEquals(expectedResult.get(i), result.get(i));
		}
	}
	
	@Test
	void testQueueSelectionAllUsed() {
		List<Condiment> condiments = new ArrayList<Condiment>();
		Order order = new Order(0, new Address("123 Address St.", 11111), "Coffee", condiments);
		CoffeeController controllerA = new CoffeeController(0, "Simple", new Address("123 Address St.", 11111));
		CoffeeController controllerB = new CoffeeController(1, "Simple", new Address("Other Road St.", 55555));
		List<Machine> machines = new ArrayList<Machine>();
		List<Capability> capabilitiesSA = new ArrayList<Capability>();
		capabilitiesSA.add(Capability.Simple);
		capabilitiesSA.add(Capability.Automated);
		List<Capability> capabilitiesS = new ArrayList<Capability>();
		capabilitiesS.add(Capability.Simple);
		Machine machine0 = new Machine(0, controllerA, capabilitiesSA);
		machine0.setNumberOfOrder(1);
		Machine machine1 = new Machine(1, controllerA, capabilitiesS);
		machine1.setNumberOfOrder(1);
		Machine machine2 = new Machine(2, controllerB, capabilitiesSA);
		machine2.setNumberOfOrder(1);
		machines.add(machine0);
		machines.add(machine1);
		machines.add(machine2);
		
		MachineSelectionStrategy selection = new MinQueueSelectionBehavior();
		
		List<Machine> result = selection.selectMachines(order, machines);
		
		List<Machine> expectedResult = new ArrayList<Machine>();
		expectedResult.add(machine0);
		expectedResult.add(machine1);
		expectedResult.add(machine2);
		
		assertEquals(expectedResult.size(), result.size());
		for(int i = 0; i < expectedResult.size(); i++) {
			assertEquals(expectedResult.get(i), result.get(i));
		}
	}
	
	@Test
	void testQueueSelectionMoreFull() {
		List<Condiment> condiments = new ArrayList<Condiment>();
		Order order = new Order(0, new Address("123 Address St.", 11111), "Coffee", condiments);
		CoffeeController controllerA = new CoffeeController(0, "Simple", new Address("123 Address St.", 11111));
		CoffeeController controllerB = new CoffeeController(1, "Simple", new Address("Other Road St.", 55555));
		List<Machine> machines = new ArrayList<Machine>();
		List<Capability> capabilitiesSA = new ArrayList<Capability>();
		capabilitiesSA.add(Capability.Simple);
		capabilitiesSA.add(Capability.Automated);
		List<Capability> capabilitiesS = new ArrayList<Capability>();
		capabilitiesS.add(Capability.Simple);
		Machine machine0 = new Machine(0, controllerA, capabilitiesSA);
		machine0.setNumberOfOrder(3);
		Machine machine1 = new Machine(1, controllerA, capabilitiesS);
		machine1.setNumberOfOrder(2);
		Machine machine2 = new Machine(2, controllerB, capabilitiesSA);
		machine2.setNumberOfOrder(4);
		machines.add(machine0);
		machines.add(machine1);
		machines.add(machine2);
		
		MachineSelectionStrategy selection = new MinQueueSelectionBehavior();
		
		List<Machine> result = selection.selectMachines(order, machines);
		
		List<Machine> expectedResult = new ArrayList<Machine>();
		expectedResult.add(machine1);
		
		assertEquals(expectedResult.size(), result.size());
		for(int i = 0; i < expectedResult.size(); i++) {
			assertEquals(expectedResult.get(i), result.get(i));
		}
	}
	
	@Test
	void testIfAnySelectionAny() {
		List<Condiment> condiments = new ArrayList<Condiment>();
		Order order = new Order(0, new Address("123 Address St.", 11111), "Coffee", condiments);
		CoffeeController controllerA = new CoffeeController(0, "Simple", new Address("123 Address St.", 11111));
		CoffeeController controllerB = new CoffeeController(1, "Simple", new Address("Other Road St.", 55555));
		List<Machine> machines = new ArrayList<Machine>();
		List<Capability> capabilitiesSA = new ArrayList<Capability>();
		capabilitiesSA.add(Capability.Simple);
		capabilitiesSA.add(Capability.Automated);
		List<Capability> capabilitiesS = new ArrayList<Capability>();
		capabilitiesS.add(Capability.Simple);
		Machine machine0 = new Machine(0, controllerA, capabilitiesSA);
		Machine machine1 = new Machine(1, controllerA, capabilitiesS);
		Machine machine2 = new Machine(2, controllerB, capabilitiesSA);
		machines.add(machine0);
		machines.add(machine1);
		machines.add(machine2);
		
		MachineSelectionStrategy selection = new AddressSelectionBehavior();
		selection = new IfAnySelectionBehavior(selection);
		
		List<Machine> result = selection.selectMachines(order, machines);
		
		List<Machine> expectedResult = new ArrayList<Machine>();
		expectedResult.add(machine0);
		expectedResult.add(machine1);
		
		assertEquals(expectedResult.size(), result.size());
		for(int i = 0; i < expectedResult.size(); i++) {
			assertEquals(expectedResult.get(i), result.get(i));
		}
	}
	
	@Test
	void testIfAnySelectionNone() {
		List<Condiment> condiments = new ArrayList<Condiment>();
		Order order = new Order(0, new Address("Fake Address", 99999), "Coffee", condiments);
		CoffeeController controllerA = new CoffeeController(0, "Simple", new Address("123 Address St.", 11111));
		CoffeeController controllerB = new CoffeeController(1, "Simple", new Address("Other Road St.", 55555));
		List<Machine> machines = new ArrayList<Machine>();
		List<Capability> capabilitiesSA = new ArrayList<Capability>();
		capabilitiesSA.add(Capability.Simple);
		capabilitiesSA.add(Capability.Automated);
		List<Capability> capabilitiesS = new ArrayList<Capability>();
		capabilitiesS.add(Capability.Simple);
		Machine machine0 = new Machine(0, controllerA, capabilitiesSA);
		Machine machine1 = new Machine(1, controllerA, capabilitiesS);
		Machine machine2 = new Machine(2, controllerB, capabilitiesSA);
		machines.add(machine0);
		machines.add(machine1);
		machines.add(machine2);
		
		MachineSelectionStrategy selection = new AddressSelectionBehavior();
		selection = new IfAnySelectionBehavior(selection);
		
		List<Machine> result = selection.selectMachines(order, machines);
		
		List<Machine> expectedResult = new ArrayList<Machine>();
		expectedResult.add(machine0);
		expectedResult.add(machine1);
		expectedResult.add(machine2);
		
		assertEquals(expectedResult.size(), result.size());
		for(int i = 0; i < expectedResult.size(); i++) {
			assertEquals(expectedResult.get(i), result.get(i));
		}
	}
	
}
