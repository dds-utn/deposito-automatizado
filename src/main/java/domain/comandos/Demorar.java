package domain.comandos;

import java.util.concurrent.TimeUnit;

public class Demorar implements Comando{
    private Double segundos;

    public Demorar(Double segundos) {
        this.segundos = segundos;
    }

    @Override
    public void ejecutar() {
        try {
            TimeUnit.SECONDS.sleep(this.segundos.longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
