package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Address;
import models.Capability;
import models.CoffeeController;
import models.Machine;
import models.Order;

public class DatabaseConnection {
	private static final String CONNECTION = "TEST_CONN";
	private static final Map<String, List<Machine>> db = new HashMap<String, List<Machine>>();
	
	public DatabaseConnection() {
		
	}
	
	public static void setUpData() {
		List<Machine> addr1Machines = new ArrayList<Machine>();
		String addr1 ="200 N Main, 47803";
		List<Capability> machine1Cap = new ArrayList<Capability>();
		machine1Cap.add(Capability.Simple);
		List<Capability> machine2Cap = new ArrayList<Capability>();
		machine2Cap.add(Capability.Automated);
		Machine machine1 = new Machine(1, new CoffeeController(1, "Simple", new Address("200 N Main", 47803)), machine1Cap);
		addr1Machines.add(machine1);
		
		Machine machine2 = new Machine(2, new CoffeeController(1, "Automated", new Address("200 N Main", 47803)), machine2Cap);
		addr1Machines.add(machine2);
		
		db.put(addr1, addr1Machines);
	}
	
	public static List<Machine> getMachinesForOrderByAddress(Order order) {
		DatabaseConnection.setUpData();
		
		int zip = order.getAddress().getZIP();
		String street = order.getAddress().getStreet();
		return db.get(street+", "+zip);
	}

	public static String getConnection() {
		return CONNECTION;
	}
	
	public static List<Machine> getMachines() {
		List<Machine> addr1Machines = new ArrayList<Machine>();
		String addr1 ="200 N Main, 47803";
		List<Capability> machine1Cap = new ArrayList<Capability>();
		machine1Cap.add(Capability.Simple);
		List<Capability> machine2Cap = new ArrayList<Capability>();
		machine2Cap.add(Capability.Automated);
		Machine machine1 = new Machine(1, new CoffeeController(1, "Simple", new Address("200 N Main", 47803)), machine1Cap);
		addr1Machines.add(machine1);
		
		Machine machine2 = new Machine(2, new CoffeeController(1, "Automated", new Address("200 N Main", 47803)), machine2Cap);
		addr1Machines.add(machine2);
		return addr1Machines;
	}

}
