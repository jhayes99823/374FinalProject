package controller;
import java.util.ArrayList;
import java.util.List;

import models.Machine;
import models.Order;

public class NotificationManager extends Subject {
	private List<Observer> observers;

	public NotificationManager() {
		observers = new ArrayList<Observer>();
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		int i = observers.indexOf(observer);
		
		if (i >= 0) {
			observers.remove(i);
		}
	}

	@Override
	public void notifyObservers(Order order, Machine machine) {
		for (Observer observer : observers) {
			observer.notify(order, machine);
		}
	}
	
	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}
}
