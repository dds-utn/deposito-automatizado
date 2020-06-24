package domain.comandos.temporizados;

import domain.tracer.Tracer;

public class TocarBocina extends ComandoConTiempo {

    @Override
    public void ejecutar() {
        Tracer.getInstance().showTrace("Ejecutando comando Tocar Bocina");
        super.receptorAdapter.tocarBocina(super.segundos);
    }
}