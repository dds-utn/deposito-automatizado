package domain.secuencias;

import domain.comandos.ComandoRobot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Secuencia {
    private String nombre;
    private List<ComandoRobot> comandos;

    public Secuencia(String nombre){
        this.comandos = new ArrayList<>();
        this.nombre   = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarComandos(ComandoRobot ... comandos){
        Collections.addAll(this.comandos, comandos);
    }

    public List<ComandoRobot> getComandos() {
        return comandos;
    }
}