package com.desarrollofzt.seguimiento.Modelo;

/**
 * Created by Desarrollo on 17/6/2016.
 */
public class Departamento {
    private static final String TAG = Departamento.class.getSimpleName();

    private String id;
    private String nombre;

    public Departamento(String id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public String getId(){return id;}
    public String getNombre(){return nombre;}

    public boolean compararCon(Departamento dep){
        return this.id.compareTo(dep.id) == 0 &&
                this.nombre.compareTo(dep.nombre) == 0;
    }
}
