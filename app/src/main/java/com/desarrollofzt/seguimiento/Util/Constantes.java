package com.desarrollofzt.seguimiento.Util;

/**
 * Created by Desarrollo on 17/6/2016.
 */
public class Constantes {
    public static final int CODIGO_DETALLE = 100;
    public static final int CODIGO_ACTUALIZACION = 101;
    public static final String USER = "fztmobile@admin.com";
    public static final String TOKEN = "2";
    private static final String PUERTO_HOST = "";
    private static final String IP = "107.170.49.251";//"192.168.56.1";

    //URLs del Web Service
    public static final String GET = "http://" + IP + PUERTO_HOST + "/obtener_datos/"+USER+"/"+TOKEN;
    public static final String INSERT = "http://" + IP + PUERTO_HOST + "/insertar_visita/";
    //public static final String UPDATE = "http://" + IP + PUERTO_HOST + "/visitas-webservice/actualizar_meta.php";
    //public static final String DELETE = "http://" + IP + PUERTO_HOST + "/visitas-webservice/borrar_meta.php";

    public static final String EXTRA_ID = "IDEXTRA";
}
