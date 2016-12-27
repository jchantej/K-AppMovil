package com.store.chante.apptarinduccion;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class GoogleMapsActivity extends AppCompatActivity implements OnMapReadyCallback  , GoogleMap.OnMarkerClickListener{
    //  private MyFragmentMaps mMapFragment;
    private GoogleMap mMap;
    private static final int LOCATION_REQUEST_CODE = 1;
    private Double latitud;
    private Double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.idMapaGoogle);
        mapFragment.getMapAsync(this);

        //Permite agregar el fragmento dinamicamente
      /*  mMapFragment = MyFragmentMaps.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.idMapaGoogle, mMapFragment)
                .commit();*/

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        // posiciones para los marcodores
        LatLng llmarcador1 = new LatLng(-4.0159712 ,-79.2034732);
        LatLng llmarcador2 = new LatLng(-4.0106224 ,-79.1983312);

        MarkerOptions markerOptions1 =
                new MarkerOptions()
                        .position(llmarcador1)
                        .title("Datos Posicion")
                        .snippet("lat:-4.0159712 - log:-79.2034732");
        MarkerOptions markerOptions2 =
                new MarkerOptions()
                        .position(llmarcador2)
                        .title("Datos Posicion")
                        .snippet("lat:-4.0106224  - log: -79.1983312");

        mMap.addMarker(markerOptions1);
        mMap.addMarker(markerOptions2);


        // Controles UI
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Mostrar diálogo explicativo
            } else {
                // Solicitar permiso
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_REQUEST_CODE);
            }
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMarkerClickListener(GoogleMapsActivity.this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            // ¿Permisos asignados?
            if (permissions.length > 0 &&
                    permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
            } else {
                Toast.makeText(this, "Error de permisos", Toast.LENGTH_LONG).show();
            }

        }
    }

    //metodo que escucha el Evento del Click en el marcador.
    @Override
    public boolean onMarkerClick(Marker marker) {


        latitud =  marker.getPosition().latitude;
        longitud =  marker.getPosition().longitude;
        //Cadena con la latitud y longitud que seran leidas por la App de Google Maps
        String uriParse = "google.navigation:q="+latitud.toString()+","+longitud.toString();
        Uri gmmIntentUri = Uri.parse(uriParse);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
        return true;
    }
}
