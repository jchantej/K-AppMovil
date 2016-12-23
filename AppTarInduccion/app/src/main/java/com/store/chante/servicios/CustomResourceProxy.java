package com.store.chante.servicios;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.store.chante.apptarinduccion.R;

import org.osmdroid.DefaultResourceProxyImpl;

/**
 * Created by Chante on 21/12/2016.
 */

public class CustomResourceProxy extends DefaultResourceProxyImpl {

    private final Context mContext;
    public CustomResourceProxy(Context pContext) {
        super(pContext);
        mContext = pContext;
    }

    @Override
    public Bitmap getBitmap(final bitmap pResId) {
        switch (pResId){
            case person:
                //your image goes here!!!
                return BitmapFactory.decodeResource(mContext.getResources(),R.drawable.marker_default);
        }
        return super.getBitmap(pResId);
    }

    @Override
    public Drawable getDrawable(final bitmap pResId) {
        switch (pResId){
            case person:
                return mContext.getResources().getDrawable(R.drawable.default_marker);
        }
        return super.getDrawable(pResId);
    }
}
