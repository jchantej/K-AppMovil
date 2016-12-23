package com.store.chante.servicios;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.store.chante.apptarinduccion.R;

/**
 * Created by Chante on 18/12/2016.
 */

public class GestionarMensajesAlertas {

    public static void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        //alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alertDialog.show();
    }

}
