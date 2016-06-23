package com.desarrollofzt.seguimiento.Modelo;

/**
 * Created by Desarrollo on 17/6/2016.
 */
public class Oficial {
    private static final String TAG = Oficial.class.getSimpleName();

    private String id;
    private String nombres;
    private String apellidos;
    private String id_area;

    public Oficial(String id, String nombres, String apellidos, String id_area){
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.id_area = id_area;
    }

    public String getId(){return id;}
    public String getNombres(){return nombres;}
    public String getApellidos(){return apellidos;}
    public String getId_area(){return id_area;}

    public boolean compararCon(Oficial ofi){
        return this.id.compareTo(ofi.id) == 0 &&
                this.nombres.compareTo(ofi.nombres) == 0 &&
                this.apellidos.compareTo(ofi.apellidos) == 0 &&
                this.id_area.compareTo(ofi.id_area) == 0;
    }
}
