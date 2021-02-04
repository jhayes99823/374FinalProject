package controller;
import java.util.ArrayList;
import java.util.List;

import models.Machine;
import models.Order;

public abstract class Subject {

	private List<Observer> observers = new ArrayList<Observer>();
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver(Observer observer) {
		int i = observers.indexOf(observer);
		
		if (i >= 0) {
			observers.remove(i);
		}
	}

	public void notifyObservers(Order order, Machine machine) {
		for (Observer observer : observers) {
			observer.notify(order, machine);
		}
	}
}
