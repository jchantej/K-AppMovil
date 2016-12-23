package com.store.chante.apptarinduccion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.store.chante.serviciosandroid.HoraService;
public class HoraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora);

        findViewById(R.id.buttonOn).setOnClickListener(mClickListener);
        findViewById(R.id.buttonOff).setOnClickListener(mClickListener);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.buttonOn:
                    // Start Service
                    startService(new Intent(HoraActivity.this, HoraService.class));
                    break;
                case R.id.buttonOff:
                    // Stop Service
                    stopService(new Intent(HoraActivity.this, HoraService.class));
                    break;
            }
        }
    };
}
