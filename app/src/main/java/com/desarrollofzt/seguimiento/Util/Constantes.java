package com.desarrollofzt.seguimiento.Util;

/**
 * Created by Desarrollo on 17/6/2016.
 */
public class Constantes {
    public static final int CODIGO_DETALLE = 100;
    public static final int CODIGO_ACTUALIZACION = 101;
    public static final String USER = "fztmobile@admin.com";
    public static final String TOKEN = "$2y$10$YKPJOauMci4/PwWITARbXezDNhNWtLmbN5/Tsi2cjZEa8Xw.SMjIW";
    private static final String PUERTO_HOST = ":8080";
    private static final String IP = "192.168.56.1";//"192.168.56.1";

    //URLs del Web Service
    public static final String GET = "http://" + IP + PUERTO_HOST + "/visitas-webservice/obtener_datos.php";
    public static final String INSERT = "http://" + IP + PUERTO_HOST + "/visitas-webservice/insertar_visita.php";
    //public static final String UPDATE = "http://" + IP + PUERTO_HOST + "/visitas-webservice/actualizar_meta.php";
    //public static final String DELETE = "http://" + IP + PUERTO_HOST + "/visitas-webservice/borrar_meta.php";

    public static final String EXTRA_ID = "IDEXTRA";
}
