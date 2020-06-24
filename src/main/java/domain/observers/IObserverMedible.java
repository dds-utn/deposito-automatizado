package domain.observers;

public interface IObserverMedible extends IObserver{

    @Override
    default void serNotificadoPor(IObservable observable) {
        serNotificadoPor((Medible) observable);
    }

    public void serNotificadoPor(Medible medible);
}
