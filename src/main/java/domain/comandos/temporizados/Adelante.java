package domain.comandos.temporizados;

import domain.tracer.Tracer;

public class Adelante extends ComandoConTiempo {

    @Override
    public void ejecutar() {
        Tracer.getInstance().showTrace("Ejecutando comando Adelante");
        super.receptorAdapter.adelante(super.segundos);
    }
}