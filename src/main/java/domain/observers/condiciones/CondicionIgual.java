package domain.observers.condiciones;

public class CondicionIgual extends Condicion{

    @Override
    public boolean teCumplisCon(Double unValor, Double otroValor) {
        return unValor.equals(otroValor);
    }
}