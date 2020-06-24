package domain.comandos.notemporizados;

import domain.comandos.ComandoRobotBuilder;
import domain.robots.AdapterComunicadorRobot;

public class ComandoConLuzBuilder<T extends ComandoConLuz> extends ComandoRobotBuilder<T> {

    public ComandoConLuzBuilder(Class<T> clase) {
        super(clase);
    }

    @Override
    public ComandoConLuzBuilder<T> conReceptor(AdapterComunicadorRobot receptor) {
        return (ComandoConLuzBuilder<T>) super.conReceptor(receptor);
    }

    public ComandoConLuzBuilder<T> conLuzDeColor(Color color){
        EncenderLuz comandoEncenderLuces = (EncenderLuz) super.comando;
        comandoEncenderLuces.setColor(color);
        return this;
    }
}