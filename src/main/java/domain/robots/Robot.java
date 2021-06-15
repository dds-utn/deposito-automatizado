package domain.robots;

import domain.comandos.Comando;
import domain.observers.IObserver;
import domain.observers.Medible;
import domain.secuencias.Secuencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Robot implements Medible {
    private String nombre;
    private List<Comando> comandos;
    private List<IObserver> observers;
    private Double proximidadAObjetoEnCM;
    private Sensor sensorDeProximidad;

    public Robot(String nombre, Sensor sensor){
        this.nombre     = nombre;
        this.comandos   = new ArrayList<>();
        this.observers  = new ArrayList<>();
        this.proximidadAObjetoEnCM = 0.0;
        this.sensorDeProximidad = sensor;
        sensor.setRobot(this);
    }

    public void encolaComando(Comando ... comandos){
        Collections.addAll(this.comandos, comandos);
    }

    public void limpiaComandos(){
        this.comandos.clear();
    }

    public void ejecutaComandos(){
        this.comandos.forEach(Comando::ejecutar);
    }

    public void ejecutaSecuencia(Secuencia secuencia){
        secuencia.getComandos().forEach(Comando::ejecutar);
    }

    public void setProximidadAObjetoEnCM(Double proximidadAObjetoEnCM) {
        this.proximidadAObjetoEnCM = proximidadAObjetoEnCM;
        this.notificar();
    }

    @Override
    public void agregarObservers(IObserver... observers) {
        Collections.addAll(this.observers, observers);
    }

    @Override
    public void eliminarObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notificar() {
        this.observers.forEach(o -> o.serNotificadoPor(this));
    }

    @Override
    public Double medicion() {
        return this.proximidadAObjetoEnCM;
    }
}