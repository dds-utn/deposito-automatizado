package domain.comandos.notemporizados.conluz;

import domain.comandos.ComandoRobot;

public abstract class ComandoConLuz extends ComandoRobot {
    protected Color color = null;

    public void setColor(Color color) {
        this.color = color;
    }
}