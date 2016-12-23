package com.store.chante.apptarinduccion;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.store.chante.servicios.PeliculaDbAdapter;

public class PeliculaFormularioActivity extends AppCompatActivity {

    private PeliculaDbAdapter dbAdapter;
    private Cursor cursor;

    //
    // Modo del formulario
    //
    private int modo ;

    //
    // Identificador del registro que se edita cuando la opción es MODIFICAR
    //
    private long id ;

    //
    // Elementos de la vista
    //
    private EditText nombre;
    private EditText genero;
    private EditText descripcion;


    private Button boton_guardar;
    private Button boton_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula_formulario);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        if (extra == null) return;

        //
        // Obtenemos los elementos de la vista
        //
        nombre = (EditText) findViewById(R.id.txtNombre);
        genero = (EditText) findViewById(R.id.txtGenero);
        descripcion = (EditText) findViewById(R.id.txtDescripcion);


        boton_guardar = (Button) findViewById(R.id.boton_guardar);
        boton_cancelar = (Button) findViewById(R.id.boton_cancelar);

        //
        // Creamos el adaptador
        //
        dbAdapter = new PeliculaDbAdapter(this);
        dbAdapter.abrir();

        //
        // Obtenemos el identificador del registro si viene indicado
        //
        if (extra.containsKey(PeliculaDbAdapter.C_COLUMNA_ID))
        {
            id = extra.getLong(PeliculaDbAdapter.C_COLUMNA_ID);
            consultar(id);
        }

        //
        // Establecemos el modo del formulario
        //
        establecerModo(extra.getInt(PeliculaActivity.C_MODO));

        //
        // Definimos las acciones para los dos botones
        //
        boton_guardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                guardar();
            }
        });
        boton_cancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                cancelar();
            }
        });

    }


    private void establecerModo(int m)
    {
        this.modo = m ;

        if (modo == PeliculaActivity.C_VISUALIZAR)
        {
            this.setTitle(nombre.getText().toString());
            this.setEdicion(false);
        }else if (modo == PeliculaActivity.C_CREAR)
        {
            this.setTitle(R.string.hipoteca_crear_pelicula);
            this.setEdicion(true);
        }else if (modo == PeliculaActivity.C_EDITAR)
        {
            this.setTitle(R.string.hipoteca_editar_titulo);
            this.setEdicion(true);
        }

    }

    private void consultar(long id)
    {
        //
        // Consultamos el centro por el identificador
        //
        cursor = dbAdapter.getRegistro(id);

        nombre.setText(cursor.getString(cursor.getColumnIndex(PeliculaDbAdapter.C_COLUMNA_NOMBRE)));
        genero.setText(cursor.getString(cursor.getColumnIndex(PeliculaDbAdapter.C_COLUMNA_GENERO)));
        descripcion.setText(cursor.getString(cursor.getColumnIndex(PeliculaDbAdapter.C_COLUMNA_DESCRIPCION)));

    }

    private void setEdicion(boolean opcion)
    {
        nombre.setEnabled(opcion);
        genero.setEnabled(opcion);
        descripcion.setEnabled(opcion);

    }

    private void guardar()
    {
        //
        // Obtenemos los datos del formulario
        //
        ContentValues reg = new ContentValues();

        if (modo == PeliculaActivity.C_EDITAR)
            reg.put(PeliculaDbAdapter.C_COLUMNA_ID, id);

            reg.put(PeliculaDbAdapter.C_COLUMNA_NOMBRE, nombre.getText().toString());
            reg.put(PeliculaDbAdapter.C_COLUMNA_GENERO, genero.getText().toString());
            reg.put(PeliculaDbAdapter.C_COLUMNA_DESCRIPCION, descripcion.getText().toString());

            if (modo == PeliculaActivity.C_CREAR) {
                dbAdapter.insert(reg);
                Toast.makeText(PeliculaFormularioActivity.this, R.string.hipoteca_crear_confirmacion, Toast.LENGTH_SHORT).show();
            } else if (modo == PeliculaActivity.C_EDITAR) {
                Toast.makeText(PeliculaFormularioActivity.this, R.string.hipoteca_editar_confirmacion, Toast.LENGTH_SHORT).show();
                dbAdapter.update(reg);
            }


        //
        // Devolvemos el control
        //
        setResult(RESULT_OK);
        finish();
    }

    private void cancelar()
    {
        setResult(RESULT_CANCELED, null);
        finish();
    }

}