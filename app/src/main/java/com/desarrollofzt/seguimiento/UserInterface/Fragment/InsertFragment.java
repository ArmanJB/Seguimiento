package com.desarrollofzt.seguimiento.UserInterface.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.desarrollofzt.seguimiento.Modelo.Area;
import com.desarrollofzt.seguimiento.Modelo.Departamento;
import com.desarrollofzt.seguimiento.Modelo.Escuela;
import com.desarrollofzt.seguimiento.Modelo.Motivo;
import com.desarrollofzt.seguimiento.Modelo.Oficial;
import com.desarrollofzt.seguimiento.R;
import com.desarrollofzt.seguimiento.Util.Constantes;
import com.desarrollofzt.seguimiento.Web.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Desarrollo on 17/6/2016.
 */
public class InsertFragment extends Fragment {
    private static final String TAG = InsertFragment.class.getSimpleName();
    private Gson gson = new Gson();

    private Area[] areas;
    private Departamento[] departamentos;
    private Oficial[] oficiales;
    private Motivo[] motivos;
    private Escuela[] escuelas;

    private List<String> areasS = new ArrayList<String>();
    private List<String> departamentosS = new ArrayList<String>();
    private List<String> oficialesS = new ArrayList<String>();
    private List<String> escuelasS = new ArrayList<String>();
    private List<String> motivosS = new ArrayList<String>();
    private List<String> otrosMotivosS = new ArrayList<String>();

    TextView fechaF;
    Spinner idAreaF;
    Spinner idDepartamentoF;
    Spinner idEscuelaF;
    Spinner idOficialF;
    LinearLayout mot;
    LinearLayout otrosMot;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_form, container, false);

        idAreaF = (Spinner) v.findViewById(R.id.idArea);
        idDepartamentoF = (Spinner) v.findViewById(R.id.idDepartamento);
        idEscuelaF = (Spinner) v.findViewById(R.id.idEscuela);
        idOficialF = (Spinner) v.findViewById(R.id.idOficial);
        fechaF = (TextView) v.findViewById(R.id.fecha);
        mot = (LinearLayout) v.findViewById(R.id.idMotivos);
        otrosMot = (LinearLayout) v.findViewById(R.id.idOtrosMotivos);

        cargarDatos();

        fechaF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment picker = new DatePickerFragment();
                picker.show(getFragmentManager(), "datePicker");
            }
        });
        idAreaF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(Area a : areas){
                    if(a.getNombre() == parent.getItemAtPosition(position).toString()){
                        cargarOficiales(a.getId() );
                        cargarMotivos(a.getId(), mot);
                        cargarOtrosMotivos("4", otrosMot);
                        if (a.getId().equals("4")){
                            otrosMot.removeAllViews();
                        }
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        idDepartamentoF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(Departamento d : departamentos){
                    if(d.getNombre() == parent.getItemAtPosition(position).toString()){
                        cargarEscuelas(d.getId() );
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                if (!camposVacios())
                    guardarVisita();
                else
                    Toast.makeText(getActivity(), "Completa los campos", Toast.LENGTH_LONG).show();
                return true;

            case R.id.descartar:
                if (!camposVacios())
                    mostrarDialogo();
                else
                    getActivity().finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void guardarVisita(){
        final String fecha = fechaF.getText().toString();
        String id_escuela = "";
        String id_oficial = "";
        JSONArray id_motivo = new JSONArray();

        for(Escuela e : escuelas){
            if(e.getNombre() == idEscuelaF.getSelectedItem().toString()){
                id_escuela = e.getId();
                break;
            }
        }

        for(Oficial o : oficiales){
            String nombre = o.getNombres()+" "+o.getApellidos();
            if(nombre.equals(idOficialF.getSelectedItem().toString())){
                id_oficial = o.getId();
                break;
            }
        }

        int id = 100;
        for(String m : motivosS){
            CheckBox c = (CheckBox) getView().findViewById(id+1);
            if(c.isChecked() ) {
                for(Motivo mot : motivos){
                    if (c.getText() == mot.getNombre()){
                        id_motivo.put(mot.getId() );
                        break;
                    }
                }
            }
            id++;
        }

        int id2 = 200;
        for(String m : motivosS){
            CheckBox c = (CheckBox) getView().findViewById(id2+1);
            if(c.isChecked() ) {
                for(Motivo mot : motivos){
                    if (c.getText() == mot.getNombre()){
                        id_motivo.put(mot.getId() );
                        break;
                    }
                }
            }
            id2++;
        }

        HashMap<String, String> map = new HashMap<>();

        map.put("fecha", fecha);
        map.put("id_escuela", id_escuela);
        map.put("id_oficial", id_oficial);

        JSONObject jobject = new JSONObject(map);
        try {
            jobject.put("motivos", id_motivo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, jobject.toString());

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(Request.Method.POST, Constantes.INSERT, jobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                procesarRespuesta(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "Error Volley: " + error.getMessage());
                            }
                        }
                ) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("Accept", "application/json");
                        return headers;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8" + getParamsEncoding();
                    }
                }
        );

    }

    private void procesarRespuesta(JSONObject response) {

        try {
            String estado = response.getString("estado");
            String mensaje = response.getString("mensaje");

            switch (estado) {
                case "1":
                    Toast.makeText(getActivity(), mensaje, Toast.LENGTH_LONG).show();
                    getActivity().setResult(Activity.RESULT_OK);
                    getActivity().finish();
                    break;

                case "2":
                    Toast.makeText(getActivity(), mensaje, Toast.LENGTH_LONG).show();
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    getActivity().finish();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public boolean camposVacios(){
        String fecha = fechaF.getText().toString();
        String id_escuela = ".";
        String id_oficial = ".";

        return (fecha.isEmpty() || id_escuela.isEmpty() || id_oficial.isEmpty());
    }

    public void actualizarFecha(int ano, int mes, int dia) {
        fechaF.setText(ano + "/" + (mes + 1) + "/" + dia);
    }

    public void mostrarDialogo() {
        DialogFragment dialogo = ConfirmDialogFragment.createInstance(getResources().getString(R.string.dialog_discard));
        dialogo.show(getFragmentManager(), "ConfirmDialog");
    }

    // *************************************

    public void cargarDatos(){
        HashMap<String, String> map = new HashMap<>();
        map.put("user", Constantes.USER);
        map.put("token", Constantes.TOKEN);

        JSONObject jobject = new JSONObject(map);

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(Request.Method.POST, Constantes.GET, jobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                extraerDatos(response);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "Error Volley: " + error.toString());
                            }
                        }
                ){
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("Accept", "application/json");
                        return headers;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8" + getParamsEncoding();
                    }
                }
        );
    }

    private void extraerDatos(JSONObject response) {
        try {
            String estado = response.getString("estado");
            switch (estado) {
                case "1":
                    JSONArray msgA = response.getJSONArray("areas");
                    JSONArray msgD = response.getJSONArray("departamentos");
                    JSONArray msgO = response.getJSONArray("oficiales");
                    JSONArray msgM = response.getJSONArray("motivos");
                    JSONArray msgE = response.getJSONArray("escuelas");
                    //
                    areas = gson.fromJson(msgA.toString(), Area[].class);
                    departamentos = gson.fromJson(msgD.toString(), Departamento[].class);
                    oficiales = gson.fromJson(msgO.toString(), Oficial[].class);
                    motivos = gson.fromJson(msgM.toString(), Motivo[].class);
                    escuelas = gson.fromJson(msgE.toString(), Escuela[].class);
                    //
                    for(Area a : areas){
                        areasS.add(a.getNombre());
                    }
                    ArrayAdapter<String> dataA =
                            new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, areasS);
                    dataA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    idAreaF.setAdapter(dataA);

                    for(Departamento d : departamentos){
                        departamentosS.add(d.getNombre());
                    }
                    ArrayAdapter<String> dataD =
                            new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, departamentosS);
                    dataD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    idDepartamentoF.setAdapter(dataD);

                    break;
                case "2":
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(getActivity(), mensaje2, Toast.LENGTH_LONG).show();
                    break;
            }

        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
        }

    }

    private void cargarOficiales(String id){
        oficialesS.clear();
        for(Oficial o : oficiales){
            if (o.getId_area().equals(id) ){
                oficialesS.add(o.getNombres()+" "+o.getApellidos());
            }
        }
        ArrayAdapter<String> dataO =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, oficialesS);
        dataO.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idOficialF.setAdapter(dataO);
    }
    private void cargarEscuelas(String id){
        escuelasS.clear();
        for(Escuela e : escuelas){
            if (e.getId_departamento().equals(id) ){
                escuelasS.add(e.getNombre());
            }
        }
        ArrayAdapter<String> dataE =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, escuelasS);
        dataE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idEscuelaF.setAdapter(dataE);
    }
    private void cargarMotivos(String id, LinearLayout v){
        motivosS.clear();
        for(Motivo m : motivos){
            if (m.getId_area().equals(id) ){
                motivosS.add(m.getNombre());
            }
        }
        v.removeAllViews();
        int ids = 100;
        for(String m : motivosS){
            CheckBox opcion = new CheckBox(getContext());
            opcion.setText(m);
            opcion.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            opcion.setId(ids+1);
            v.addView(opcion);
            ids++;
        }
    }
    private void cargarOtrosMotivos(String id, LinearLayout v){
        otrosMotivosS.clear();
        for(Motivo m : motivos){
            if (m.getId_area().equals(id) ){
                otrosMotivosS.add(m.getNombre());
            }
        }
        v.removeAllViews();
        int ids = 200;
        for(String m : otrosMotivosS){
            CheckBox opcion = new CheckBox(getContext());
            opcion.setText(m);
            opcion.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            opcion.setId(ids+1);
            v.addView(opcion);
            ids++;
        }
    }
}
