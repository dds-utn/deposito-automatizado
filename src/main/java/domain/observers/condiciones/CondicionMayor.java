package domain.observers.condiciones;

public class CondicionMayor extends Condicion{

    @Override
    public boolean teCumplisCon(Double unValor, Double otroValor) {
        return unValor > otroValor;
    }
}