package com.store.chante.modelos;

/**
 * Created by Chante on 18/12/2016.
 */
import org.json.JSONException;
import org.json.JSONObject;
public class Contactos {


    private String nombre;
    private Integer telefono;
    private String correo;
    private String genero;

    public Contactos(JSONObject object) {
        try {
        this.nombre = object.getString("nombre");
        this.telefono =  object.getInt("telefono");
        this.correo = object.getString("correo");
        this.genero = object.getString("genero");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Contactos(String nombre, Integer telefono, String correo, String genero) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


}
