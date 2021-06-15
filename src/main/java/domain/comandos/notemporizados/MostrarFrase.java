package domain.comandos.notemporizados;

import domain.comandos.ComandoRobot;

public class MostrarFrase extends ComandoRobot {
    private String frase;

    public void setFrase(String frase) {
        this.frase = frase;
    }

    @Override
    public void ejecutar() {
        super.receptorAdapter.mostrarFrase(this.frase);
    }
}
