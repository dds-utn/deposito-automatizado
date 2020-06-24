package domain.comandos;

import domain.robots.AdapterComunicadorRobot;

public abstract class ComandoRobot implements Comando {
    protected AdapterComunicadorRobot receptorAdapter;

    public void setReceptorAdapter(AdapterComunicadorRobot receptorAdapter) {
        this.receptorAdapter = receptorAdapter;
    }
}