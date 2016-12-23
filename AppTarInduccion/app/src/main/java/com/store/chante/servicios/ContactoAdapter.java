package com.store.chante.servicios;

/**
 * Created by Chante on 18/12/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.store.chante.apptarinduccion.R;
import com.store.chante.modelos.Contactos;

import java.util.ArrayList;
public class ContactoAdapter extends ArrayAdapter<Contactos>{
    private static class ViewHolder {
        TextView ctcNombfe;
        TextView ctcTelefono;
    }

    public ContactoAdapter(Context context, ArrayList<Contactos> contactos) {
        super(context, R.layout.item_contacto, contactos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contactos contacto = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_contacto, parent, false);

            viewHolder.ctcNombfe= (TextView) convertView.findViewById(R.id.value_note_id);
            viewHolder.ctcTelefono = (TextView) convertView.findViewById(R.id.value_note_title);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ctcNombfe.setText(contacto.getNombre());
        viewHolder.ctcTelefono.setText(contacto.getTelefono().toString());

        return convertView;
    }
}
