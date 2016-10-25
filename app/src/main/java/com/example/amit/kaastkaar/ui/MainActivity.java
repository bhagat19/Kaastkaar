package com.example.amit.kaastkaar.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.amit.kaastkaar.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        userType = getIntent().getStringExtra(getResources().getString(R.string.user_type));
        Log.v(LOG_TAG,userType);

        mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(LOG_TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    if(userType.equals(getResources().getString(R.string.user_farmer)))
                    {
                        FarmerViewFragment farmerViewFragment = FarmerViewFragment.newInstance();
                       getFragmentManager().beginTransaction().
                               replace(R.id.fragment_placeholder,farmerViewFragment,"dialog").commit();


                    }
                    else
                    {
                     RetailViewFragment retailViewFragment = RetailViewFragment.newInstance();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragment_placeholder,retailViewFragment).commit();

                    }
                } else {
                    // User is signed out
                    Log.d(LOG_TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(MainActivity.this,"Please login to continue", Toast.LENGTH_SHORT).show();
                }

            }
        }





