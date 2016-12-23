package com.store.chante.apptarinduccion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Chante on 16/12/2016.
 */

public class MainActivity  extends AppCompatActivity{

    Button irMapas;
    Button irConsumoRest;
    Button irChat;
    Button irGestionPeliculas;
    Button irServicioAndriod;
    Button irGoogelMaps;
    Button irOpenStreetMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        irMapas = (Button) findViewById(R.id.btnMapa);
        irGoogelMaps  = (Button) findViewById(R.id.btnGoogleMap);
        irOpenStreetMap=(Button) findViewById((R.id.btnOpstmap));
        irConsumoRest = (Button) findViewById(R.id.btnRest);
        irChat = (Button) findViewById(R.id.btnChat);
        irGestionPeliculas = (Button) findViewById(R.id.btnSqlite);
        irServicioAndriod=(Button) findViewById((R.id.btnServicioAndriod));

        irMapas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, MapBoxActivity.class);
                startActivity(it);
            }
        });
        irGoogelMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, GoogleMapsActivity.class);
                startActivity(it);

            }
        });
        irOpenStreetMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, OsmActivity.class);
                startActivity(it);

            }
        });

        irConsumoRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, ConsumoRestActivity.class);
                startActivity(it);

            }
        });

        irChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(it);

            }
        });

        irGestionPeliculas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, PeliculaActivity.class);
                startActivity(it);

            }
        });

        irServicioAndriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, HoraActivity.class);
                startActivity(it);

            }
        });
    }

}
