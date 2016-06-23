package com.desarrollofzt.seguimiento.UserInterface.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.desarrollofzt.seguimiento.R;

public class MainActivity extends AppCompatActivity {

    private Button visita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visita = (Button) findViewById(R.id.nuevaVisita);
        visita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), InsertActivity.class);
                startActivity(i);
            }
        });
    }
}
