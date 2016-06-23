package com.desarrollofzt.seguimiento.Modelo;

/**
 * Created by Desarrollo on 17/6/2016.
 */
public class Visita {
    private static final String TAG = Visita.class.getSimpleName();

    private String id;
    private String fecha;
    private String id_escuela;
    private String id_oficial;

    public Visita(String id, String fecha, String id_escuela, String id_oficial){
        this.id = id;
        this.fecha = fecha;
        this.id_escuela = id_escuela;
        this.id_oficial = id_oficial;
    }

    public String getId(){return id;}
    public String getFecha(){return fecha;}
    public String getId_escuela(){return id_escuela;}
    public String getId_oficial(){return id_oficial;}

    public boolean compararCon(Visita vis){
        return this.id.compareTo(vis.id) == 0 &&
                this.fecha.compareTo(vis.fecha) == 0 &&
                this.id_escuela.compareTo(vis.id_escuela) == 0 &&
                this.id_oficial.compareTo(vis.id_oficial) == 0;
    }
}
