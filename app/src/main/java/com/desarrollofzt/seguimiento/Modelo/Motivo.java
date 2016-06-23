package com.desarrollofzt.seguimiento.Modelo;

/**
 * Created by Desarrollo on 17/6/2016.
 */
public class Motivo {
    private static final String TAG = Motivo.class.getSimpleName();

    private String id;
    private String nombre;
    private String id_area;

    public Motivo(String id, String nombre, String id_area){
        this.id = id;
        this.nombre = nombre;
        this.id_area = id_area;
    }

    public String getId(){return id;}
    public String getNombre(){return nombre;}
    public String getId_area(){return id_area;}

    public boolean compararCon(Motivo mot){
        return this.id.compareTo(mot.id) == 0 &&
                this.nombre.compareTo(mot.nombre) == 0 &&
                this.id_area.compareTo(mot.id_area) == 0;
    }
}
