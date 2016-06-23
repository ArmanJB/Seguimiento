package com.desarrollofzt.seguimiento.Modelo;

/**
 * Created by Desarrollo on 17/6/2016.
 */
public class Detalle {
    private static final String TAG = Detalle.class.getSimpleName();

    private String id;
    private String id_visita;
    private String id_motivo;

    public Detalle(String id, String id_visita, String id_motivo){
        this.id = id;
        this.id_visita = id_visita;
        this.id_motivo = id_motivo;
    }

    public String getId(){return id;}
    public String getId_visita(){return id_visita;}
    public String getId_motivo(){return id_motivo;}

    public boolean compararCon(Detalle det){
        return this.id.compareTo(det.id) == 0 &&
                this.id_visita.compareTo(det.id_visita) == 0 &&
                this.id_motivo.compareTo(det.id_motivo) == 0;
    }
}
