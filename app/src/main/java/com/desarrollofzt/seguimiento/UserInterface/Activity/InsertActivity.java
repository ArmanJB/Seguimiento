package com.desarrollofzt.seguimiento.UserInterface.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.desarrollofzt.seguimiento.R;
import com.desarrollofzt.seguimiento.UserInterface.Fragment.ConfirmDialogFragment;
import com.desarrollofzt.seguimiento.UserInterface.Fragment.DatePickerFragment;
import com.desarrollofzt.seguimiento.UserInterface.Fragment.InsertFragment;
import com.desarrollofzt.seguimiento.Util.Constantes;

/**
 * Created by Desarrollo on 17/6/2016.
 */
public class InsertActivity extends AppCompatActivity implements DatePickerFragment.OnDateSelectedListener, ConfirmDialogFragment.ConfirmDialogListener {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);

        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_done);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new InsertFragment(), "InsertFragment").commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        InsertFragment insertFragment = (InsertFragment) getSupportFragmentManager().findFragmentByTag("InsertFragment");
        if (insertFragment != null) {
            finish();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        InsertFragment insertFragment = (InsertFragment) getSupportFragmentManager().findFragmentByTag("InsertFragment");
        if (insertFragment != null) {
            //
        }
    }

    @Override
    public void onDateSelected(int year, int month, int day) {
        InsertFragment insertFragment = (InsertFragment) getSupportFragmentManager().findFragmentByTag("InsertFragment");
        if (insertFragment != null) {
            insertFragment.actualizarFecha(year, month, day);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == Constantes.CODIGO_DETALLE || requestCode == 3){
            if (resultCode == RESULT_OK || resultCode == 203){
                InsertFragment fragment = (InsertFragment) getSupportFragmentManager().findFragmentByTag("InsertFragment");
                fragment.cargarDatos();
            }
        }
    }

}
