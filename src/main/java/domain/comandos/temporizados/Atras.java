package domain.comandos.temporizados;

import domain.tracer.Tracer;

public class Atras extends ComandoConTiempo{

    @Override
    public void ejecutar() {
        Tracer.getInstance().showTrace("Ejecutando comando Atras");
        super.receptorAdapter.atras(super.segundos);
    }
}