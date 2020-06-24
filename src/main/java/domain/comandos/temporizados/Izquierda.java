package domain.comandos.temporizados;

import domain.tracer.Tracer;

public class Izquierda extends ComandoConTiempo {

    @Override
    public void ejecutar() {
        Tracer.getInstance().showTrace("Ejecutando comando Izquierda");
        super.receptorAdapter.izquierda(super.segundos);
    }
}