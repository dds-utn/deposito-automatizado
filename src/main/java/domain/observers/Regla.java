package domain.observers;

import domain.comandos.Comando;
import domain.observers.condiciones.Condicion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Regla implements IObserverMedible{
    private Condicion condicion;
    private Double umbral;
    private List<Comando> acciones;

    public Regla(Condicion condicion, Double umbral){
        this.umbral     = umbral;
        this.condicion  = condicion;
        this.acciones   = new ArrayList<>();
    }

    public void agregarAccion(Comando ... comandos){
        Collections.addAll(this.acciones, comandos);
    }

    private Boolean seCumpleCondicionPara(Double unValor){
        return this.condicion.teCumplisCon(unValor, this.umbral);
    }

    private void ejecutarAcciones(){
        this.acciones.forEach(Comando::ejecutar);
    }

    @Override
    public void serNotificadoPor(Medible medible) {
        if(this.seCumpleCondicionPara(medible.medicion())){
            this.ejecutarAcciones();
        }
    }
}