package controller;
import models.Machine;
import models.Drink;

public interface Observer {
	public void notify(Drink drink, Machine machine);
}