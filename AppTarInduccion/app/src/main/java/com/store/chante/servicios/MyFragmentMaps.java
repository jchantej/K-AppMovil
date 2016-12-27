package com.store.chante.servicios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by Chante on 23/12/2016.
 */

public class MyFragmentMaps extends SupportMapFragment {

    public MyFragmentMaps() {
    }

    public static MyFragmentMaps newInstance() {
        return new MyFragmentMaps();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        return root;
    }
}
