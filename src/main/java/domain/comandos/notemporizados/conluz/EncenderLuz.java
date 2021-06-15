package domain.comandos.notemporizados.conluz;

import domain.tracer.Tracer;

public class EncenderLuz extends ComandoConLuz {

    @Override
    public void ejecutar() {
        Tracer.getInstance().showTrace("Ejecutando comando Encender Luz " + super.color.equivalente(super.color));
        super.receptorAdapter.encenderLuz(super.color);
    }
}