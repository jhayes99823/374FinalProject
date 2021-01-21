package controller;
import models.Machine;
import models.Order;

public interface Observer {
	public void notify(Order order, Machine machine);
}