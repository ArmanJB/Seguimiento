package com.desarrollofzt.seguimiento.Modelo;

/**
 * Created by Desarrollo on 17/6/2016.
 */
public class Escuela {
    private static final String TAG = Escuela.class.getSimpleName();

    private String id;
    private String nombre;
    private String id_departamento;

    public Escuela(String id, String nombre, String id_departamento){
        this.id = id;
        this.nombre = nombre;
        this.id_departamento = id_departamento;
    }

    public String getId(){return id;}
    public String getNombre(){return nombre;}
    public String getId_departamento(){return id_departamento;}

    public boolean compararCon(Escuela esc){
        return this.id.compareTo(esc.id) == 0 &&
                this.nombre.compareTo(esc.nombre) == 0 &&
                this.id_departamento.compareTo(esc.id_departamento) == 0;
    }
}
