package com.store.chante.apptarinduccion;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.SimpleLocationOverlay;


public class OsmActivity extends Activity {
    LocationManager locationManager;
    private MapController mapController;
    private MapView openMapView;
    double latitudActual = -4.0159712; //Corrdenadas Iniciales
    double longitudActual = -79.2034732; //Corrdenadas Iniciales

    //temppral verificar en toast cuantas veces actualiza
    Integer numero = 0;
    ArrayList<OverlayItem> overlayItemArray;
    //sirve para obtener la  posicion actual
    SimpleLocationOverlay myLocationOverlay;

    //evita  actualizar cuando la app no esta en primer plano
    Boolean continueGps = true;

    private LocationListener myLocationListener = new LocationListener() {
        public void onLocationChanged(Location paramAnonymousLocation) {
            OsmActivity.this.updateLoc(paramAnonymousLocation);
        }

        public void onProviderDisabled(String paramAnonymousString) {
        }

        public void onProviderEnabled(String paramAnonymousString) {
        }

        public void onStatusChanged(String paramAnonymousString, int paramAnonymousInt, Bundle paramAnonymousBundle) {
        }
    };

    private void setOverlayLoc(Location paramLocation) {
        GeoPoint localGeoPoint = new GeoPoint(paramLocation);
        this.overlayItemArray.clear();
        OverlayItem localOverlayItem = new OverlayItem("Mi localización es: ", "Mi localización es Lon:" + localGeoPoint.getLongitude() +":"+  localGeoPoint.getLongitude()  , localGeoPoint);
        this.overlayItemArray.add(localOverlayItem);
    }

    private void updateLoc(Location location) {
        //Obtener la dirección de la calle a partir de la latitud y la longitud
        if(latitudActual!=location.getLatitude() && longitudActual!=location.getLongitude() && continueGps){
            numero ++;
            latitudActual=location.getLatitude();
            longitudActual=location.getLongitude();
            GeoPoint myLocation = new GeoPoint(latitudActual,longitudActual);
            myLocationOverlay.setLocation(myLocation);

            openMapView.getController().animateTo(myLocation);
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Actualiza "+numero, Toast.LENGTH_SHORT);

            toast1.show();
       }
      //  setOverlayLoc(location);
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_osm);
        openMapView = (MapView) findViewById(R.id.osmMApID);
        openMapView.setBuiltInZoomControls(true);
        openMapView.setMultiTouchControls(true);
        openMapView.setUseDataConnection(true);
       // openMapView.setTileSource(TileSourceFactory.MAPNIK);
        mapController = (MapController) this.openMapView.getController();
        mapController.setZoom(14);
        overlayItemArray = new ArrayList();
       // DefaultResourceProxyImpl localDefaultResourceProxyImpl = new DefaultResourceProxyImpl(this);
        overlayItemArray.add(new OverlayItem("Datos de Ubicacion", "lat:-4.0159712 - log:-79.2034732 ", new GeoPoint(-4.0159712 ,-79.2034732))); // Lat/Lon decimal degrees
        overlayItemArray.add(new OverlayItem("Datos de Ubicacion", "lat:-4.0106224  - log: -79.1983312", new GeoPoint(-4.0106224 ,-79.1983312))); // Lat/Lon decimal degrees
       // MyItemizedIconOverlay localMyItemizedIconOverlay = new MyItemizedIconOverlay(this.overlayItemArray, null, localDefaultResourceProxyImpl);
        OnItemGestureListener<OverlayItem> myOnItemGestureListener = new OnItemGestureListener<OverlayItem>() {

            @Override
            public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                Toast.makeText(
                        OsmActivity.this,
                        item.getTitle() + "n" + item.getTitle() + "n"
                                + item.getPoint().getLatitudeE6() + " : "
                                + item.getPoint().getLongitudeE6(),
                        Toast.LENGTH_LONG).show();

                return true;
            }

        };

        //Esto es tu posicion, aun faltaria setear la posicion de tu marcador
        myLocationOverlay = new SimpleLocationOverlay(this);
        openMapView.getOverlays().add(myLocationOverlay);

        ItemizedOverlayWithFocus<OverlayItem> anotherItemizedIconOverlay = new ItemizedOverlayWithFocus<OverlayItem>(this, overlayItemArray, myOnItemGestureListener);
        anotherItemizedIconOverlay.setFocusItemsOnTap(true);
        openMapView.getOverlays().add(anotherItemizedIconOverlay);

        locationManager = ((LocationManager) getSystemService(Context.LOCATION_SERVICE));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
       locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                myLocationListener);
        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastLocation != null) {
            updateLoc(lastLocation);
        }


       ScaleBarOverlay localScaleBarOverlay = new ScaleBarOverlay(openMapView);
        this.openMapView.getOverlays().add(localScaleBarOverlay);
    }

    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        this.locationManager.removeUpdates(this.myLocationListener);
        continueGps=true;
    }

    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        if (locationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER))
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, myLocationListener);

        if (locationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER))
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocationListener);

        continueGps=true;
    }

    //TODO permite personalizar los markadores , iconos -- Iplementar en una clase separada

   /* private class MyItemizedIconOverlay extends ItemizedIconOverlay<OverlayItem>{

        public MyItemizedIconOverlay(
                List<OverlayItem> pList,
                org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener<OverlayItem> pOnItemGestureListener,
                ResourceProxy pResourceProxy) {
            super(pList, pOnItemGestureListener, pResourceProxy);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void draw(Canvas canvas, MapView mapview, boolean arg2) {
            // TODO Auto-generated method stub
            super.draw(canvas, mapview, arg2);

            if(!overlayItemArray.isEmpty()){

                //overlayItemArray have only ONE element only, so I hard code to get(0)
                GeoPoint in = (GeoPoint) overlayItemArray.get(0).getPoint();

                Point out = new Point();
                mapview.getProjection().toPixels(in, out);

                Bitmap bm = BitmapFactory.decodeResource(getResources(),
                        R.drawable.default_marker);
                canvas.drawBitmap(bm,
                        out.x - bm.getWidth()/2, 	//shift the bitmap center
                        out.y - bm.getHeight()/2, 	//shift the bitmap center
                        null);
            }
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event, MapView mapView) {
            // TODO Auto-generated method stub
            //return super.onSingleTapUp(event, mapView);
            return true;
        }
    }*/
}