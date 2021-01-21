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
		observers.remove(observer);
	}

	@Override
	public void notifyObservers(Order order, Machine macine) {
		// TODO
	}
	
	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}
}
