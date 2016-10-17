package com.example.amit.kaastkaar.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by amit on 14-10-2016.
 */
public class FarmerViewFragment extends Fragment {

    public static final String LOG_TAG = FarmerViewFragment.class.getSimpleName();

    public static FarmerViewFragment newInstance() {
        FarmerViewFragment farmerViewFragment = new FarmerViewFragment();
        Bundle bundle = new Bundle();
        farmerViewFragment.setArguments(bundle);
        return farmerViewFragment;
    }



    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        Log.v(LOG_TAG,"Hi from FarmerFragment");

    }

    }

