package com.store.chante.apptarinduccion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.store.chante.constantes.Constantes;

import java.net.URISyntaxException;

public class ChatActivity extends AppCompatActivity {

    private Socket mSocket;
    private Button enviarMensaje;
    private EditText msgEmitido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        {
            try {
                mSocket = IO.socket(Constantes.URL_SOCKET);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        //  mSocket.on("new message", onNewMessage);

        mSocket.connect();

        enviarMensaje = (Button) findViewById(R.id.btnEnviar);
        msgEmitido = (EditText) findViewById(R.id.msgEmitido);
        enviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSend(msgEmitido.getText().toString());
            }
        });


    }

    private void enviarMensaje() {
        String message = msgEmitido.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            return;
        }

        msgEmitido.setText("");
        mSocket.emit("enviar mensaje", message);
    }

    private void attemptSend(String msgEmit) {
        // if (null == mUsername) return;
        //if (!mSocket.connected()) return;

        //  mTyping = false;

        String message = msgEmit.toString().trim();
        msgEmitido.setText("");
        // perform the sending message attempt.
        mSocket.emit("nuevo mensaje", message);
    }



}