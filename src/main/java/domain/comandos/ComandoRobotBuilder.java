package domain.comandos;

import domain.robots.AdapterComunicadorRobot;

public class ComandoRobotBuilder<T extends ComandoRobot>{
    protected T comando;

    public ComandoRobotBuilder(Class<T> clase) {
        try
        {
            this.comando = clase.newInstance();
        }
        catch (Exception e)
        {
            throw new RuntimeException("No se puede instanciar el builder para la clase solicitada");
        }
    }

    public ComandoRobotBuilder<T> conReceptor(AdapterComunicadorRobot receptor){
        ComandoRobot comandoRobot = (ComandoRobot) this.comando;
        comandoRobot.setReceptorAdapter(receptor);
        return this;
    }

    public T construir(){
        return this.comando;
    }
}