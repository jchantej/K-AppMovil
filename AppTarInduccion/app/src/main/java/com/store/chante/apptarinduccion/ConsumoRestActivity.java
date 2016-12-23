package com.store.chante.apptarinduccion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.store.chante.clienterest.ContactoRestCliente;
import com.store.chante.modelos.Contactos;
import com.store.chante.servicios.ContactoAdapter;
import com.store.chante.servicios.GestionarConexiones;
import com.store.chante.servicios.GestionarMensajesAlertas;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class ConsumoRestActivity extends AppCompatActivity {

    private ListView contactosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumo_rest);
        if(GestionarConexiones.verificarTodaConexion(this)){
            getContactos();
        }
        else{

            GestionarMensajesAlertas.showAlertDialog(this, "Conexion",
                    "Tu Dispositivo tiene problemas de conexion", true);
        }

    }

    private void getContactos() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "application/json"));

        ContactoRestCliente.get(ConsumoRestActivity.this, "api/contactos/", headers.toArray(new Header[headers.size()]),
                null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        ArrayList<Contactos> contactoArray = new ArrayList<>();
                        ContactoAdapter contactoAdapter = new ContactoAdapter(ConsumoRestActivity.this, contactoArray);

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                contactoAdapter.add(new Contactos(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        contactosList = (ListView) findViewById(R.id.list_contactos);
                        contactosList.setAdapter(contactoAdapter);
                    }
                });
    }
}
