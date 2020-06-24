package domain.comandos.temporizados;

import domain.tracer.Tracer;

public class Derecha extends ComandoConTiempo {

    @Override
    public void ejecutar() {
        Tracer.getInstance().showTrace("Ejecutando comando Derecha");
        super.receptorAdapter.derecha(super.segundos);
    }
}