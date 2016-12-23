package com.store.chante.servicios;

/**
 * Created by Chante on 18/12/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PeliculaDbHelper extends SQLiteOpenHelper {


        private static int version = 1;
        private static String name = "PeliculaDb" ;
        private static CursorFactory factory = null;

        public PeliculaDbHelper(Context context)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
                Log.i(this.getClass().toString(), "Creando base de datos");

                db.execSQL( "CREATE TABLE PELICULA(" +
                        " _id INTEGER PRIMARY KEY," +
                        " hip_nombre TEXT NOT NULL, " +
                        " hip_genero TEXT, " +
                        " hip_descripcion TEXT)");

                db.execSQL( "CREATE UNIQUE INDEX hip_nombre ON PELICULA (hip_nombre ASC)" );

                Log.i(this.getClass().toString(), "Tabla PELICULA creada");

   /*
    * Insertamos datos iniciales
    */
                db.execSQL("INSERT INTO PELICULA(_id, hip_nombre,hip_genero,hip_descripcion) VALUES(1,'Los Hombres de Negro','Accion', 'Los Hombres de Negro')");
                db.execSQL("INSERT INTO PELICULA(_id, hip_nombre,hip_genero,hip_descripcion) VALUES(2,'Dragon Ball Z','Animada', 'DB Descripcion')");
                Log.i(this.getClass().toString(), "Datos iniciales PELICULA insertados");
                Log.i(this.getClass().toString(), "Base de datos creada");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {

        }

}

