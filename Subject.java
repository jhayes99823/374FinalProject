import java.util.List;

public abstract class Subject {
	public abstract void addObserver(Observer observer);
	
	public abstract void removeObserver(Observer observer);
	
	public abstract void notifyObservers(Order order, Machine macine);
}
