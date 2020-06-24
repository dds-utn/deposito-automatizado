package domain.observers;

public interface IObservable {
    public void agregarObservers(IObserver ... observers);
    public void eliminarObserver(IObserver observer);
    public void notificar();
}
