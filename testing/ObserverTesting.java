package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import controller.Barista;
import controller.NotificationManager;
import models.Drink;
import models.Machine;

class ObserverTesting {

	@Test
	void testNotifySeparateMachines() {
		Machine machine0 = new Machine(0, null, null);
		Machine machine1 = new Machine(1, null, null);
		Machine machine2 = new Machine(2, null, null);
		Machine machine3 = new Machine(3, null, null);
		List<Barista> baristas = new ArrayList<Barista>();
		baristas.add(new Barista(machine0));
		baristas.add(new Barista(machine1));
		baristas.add(new Barista(machine2));
		baristas.add(new Barista(machine3));

		NotificationManager notifications = new NotificationManager();
		for (Barista barista : baristas) {
			notifications.addObserver(barista);
		}

		Drink drink = new Drink(0, null, null);

		notifications.notifyObservers(drink, machine0);

		boolean[] attendingStates = { true, false, false, false };
		for (int i = 0; i < attendingStates.length; i++) {
			assertEquals(attendingStates[i], baristas.get(i).isAttendingTo());
		}
	}
	
	@Test
	void testNotifyDuplicateMachines() {
		Machine machine0 = new Machine(0, null, null);
		Machine machine1 = new Machine(1, null, null);
		List<Barista> baristas = new ArrayList<Barista>();
		baristas.add(new Barista(machine0));
		baristas.add(new Barista(machine1));
		baristas.add(new Barista(machine0));
		baristas.add(new Barista(machine1));

		NotificationManager notifications = new NotificationManager();
		for (Barista barista : baristas) {
			notifications.addObserver(barista);
		}

		Drink drink = new Drink(0, null, null);

		notifications.notifyObservers(drink, machine0);

		boolean[] attendingStates = { true, false, true, false };
		for (int i = 0; i < attendingStates.length; i++) {
			assertEquals(attendingStates[i], baristas.get(i).isAttendingTo());
		}
	}
	
	@Test
	void testNotifySameMachines() {
		Machine machine0 = new Machine(0, null, null);
		List<Barista> baristas = new ArrayList<Barista>();
		baristas.add(new Barista(machine0));
		baristas.add(new Barista(machine0));
		baristas.add(new Barista(machine0));
		baristas.add(new Barista(machine0));

		NotificationManager notifications = new NotificationManager();
		for (Barista barista : baristas) {
			notifications.addObserver(barista);
		}

		Drink drink = new Drink(0, null, null);

		notifications.notifyObservers(drink, machine0);

		boolean[] attendingStates = { true, true, true, true };
		for (int i = 0; i < attendingStates.length; i++) {
			assertEquals(attendingStates[i], baristas.get(i).isAttendingTo());
		}
	}

}
