public abstract class Subject {
	private List<Observer> observers;

	public void addObserver(Observer observer) {}
	
	public void removeObserver(Observer observer) {}
	
	public void notifyObservers(Order order, Machine macine) {}
}
