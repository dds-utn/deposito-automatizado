package domain.robots;

import domain.comandos.notemporizados.Color;

public interface AdapterComunicadorRobot {
    public void apagarLuz(Color color);
    public void encenderLuz(Color color);
    public void adelante(Integer segundos);
    public void atras(Integer segundos);
    public void izquierda(Integer segundos);
    public void derecha(Integer segundos);
    public void tocarBocina(Integer segundos);
}
