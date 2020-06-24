package domain.comandos.temporizados;

import domain.comandos.ComandoRobot;

public abstract class ComandoConTiempo extends ComandoRobot {
    protected Integer segundos;

    public void setSegundos(Integer segundos) {
        this.segundos = segundos;
    }
}