import java.util.List;

public class NotificationManager extends Subject {
	private List<Observer> observers;

	public NotificationManager() {
		
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
	public void notifyObservers(Order order, Machine macine) {
		for (Observer observer : observers) {
			observer.notify(order, macine);
		}
	}
	
	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}
}
