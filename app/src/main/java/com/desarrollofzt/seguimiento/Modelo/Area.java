package com.desarrollofzt.seguimiento.Modelo;

/**
 * Created by Desarrollo on 17/6/2016.
 */
public class Area {
    private static final String TAG = Area.class.getSimpleName();

    private String id;
    private String nombre;

    public Area(String id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public String getId(){return id;}
    public String getNombre(){return nombre;}

    public boolean compararCon(Area dep){
        return this.id.compareTo(dep.id) == 0 &&
                this.nombre.compareTo(dep.nombre) == 0;
    }
}
