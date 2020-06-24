package domain.comandos.temporizados;

import domain.comandos.ComandoRobotBuilder;
import domain.robots.AdapterComunicadorRobot;

public class ComandoTemporizadoBuilder<T extends ComandoConTiempo> extends ComandoRobotBuilder<T> {

    public ComandoTemporizadoBuilder(Class<T> clase) {
        super(clase);
    }

    @Override
    public ComandoTemporizadoBuilder<T> conReceptor(AdapterComunicadorRobot receptor) {
        return (ComandoTemporizadoBuilder<T>) super.conReceptor(receptor);
    }

    public ComandoTemporizadoBuilder<T> conSegundos(Integer segundos){
        ComandoConTiempo comandoConTiempo = (ComandoConTiempo) super.comando;
        comandoConTiempo.setSegundos(segundos);
        return this;
    }
}