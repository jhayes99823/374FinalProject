package controller;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;

import models.Drink;
import models.Machine;

public class Barista implements Observer {

	private Subject subject;

	private Callable<Void> updateFunc;
	
	private Machine workingAt;
	private Queue<Drink> drinksToAttendTo;

	public Barista(Machine workingAt) {
		this.workingAt = workingAt;
		this.drinksToAttendTo = new LinkedList<Drink>();
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
		subject.addObserver(this);
	}

	public void doneObserving() {
		if (subject != null)
			subject.removeObserver(this);
	}

	public void setCall(Callable<Void> func) {
		this.updateFunc = func;
	}
	
	@Override
	public void notify(Drink drink, Machine machine) {
		if (this.workingAt.equals(machine)) {
			this.drinksToAttendTo.add(drink);
			if(updateFunc!=null) {
				try {
					updateFunc.call();
				} catch (Exception e) {
					
				}
			}
		}
	}

	public boolean isAttendingTo() {
		return !this.drinksToAttendTo.isEmpty();
	}

	public int getNumInQueue() {
		return this.drinksToAttendTo.size();
	}

	public Drink currentlyWorkingOn() {
		return drinksToAttendTo.peek();
	}

	public void finishDrink() {
		if (isAttendingTo()) {
			drinksToAttendTo.poll();
			if(updateFunc!=null) {
				try {
					updateFunc.call();
				} catch (Exception e) {
					
				}
			}
		}
	}

	public Machine getMachine() {
		return this.workingAt;
	}

}
