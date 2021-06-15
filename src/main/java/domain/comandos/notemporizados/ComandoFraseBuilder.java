package domain.comandos.notemporizados;

import domain.comandos.ComandoRobotBuilder;
import domain.robots.AdapterComunicadorRobot;

public class ComandoFraseBuilder extends ComandoRobotBuilder<MostrarFrase> {

    public ComandoFraseBuilder(Class<MostrarFrase> clase) {
        super(clase);
    }

    @Override
    public ComandoFraseBuilder conReceptor(AdapterComunicadorRobot receptor) {
        return (ComandoFraseBuilder) super.conReceptor(receptor);
    }

    public ComandoFraseBuilder conFrase(String frase) {
        super.comando.setFrase(frase);
        return this;
    }
}
