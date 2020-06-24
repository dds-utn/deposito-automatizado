package domain.observers.condiciones;

public class CondicionMenorIgual extends Condicion{

    @Override
    public boolean teCumplisCon(Double unValor, Double otroValor) {
        return unValor <= otroValor;
    }
}