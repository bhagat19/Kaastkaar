package com.example.amit.kaastkaar.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.amit.kaastkaar.R;

/**
 * Created by amit on 12-10-2016.
 */
public class WelcomeScreen extends AppCompatActivity {

    private Button btn_farmer, btn_retailer;
    private String statusRef;
    private Context mContext;
    public static final String LOG_TAG = WelcomeScreen.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.welcome_screen);
        mContext = this;
        statusRef = getIntent().getStringExtra("refKey");
        Log.v(LOG_TAG, statusRef);



        btn_farmer =(Button) findViewById(R.id.btn_farmer);
        btn_retailer = (Button) findViewById(R.id.btn_retailer);

        btn_farmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusRef.equals("Sign in")) {
                    Intent intent = new Intent(WelcomeScreen.this, LoginActivity.class);
                    Log.v(LOG_TAG, getResources().getString(R.string.user_type));
                    Log.v(LOG_TAG, mContext.getString(R.string.user_farmer));

                    intent.putExtra(getResources().getString(R.string.user_type), mContext.getString(R.string.user_farmer));
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(WelcomeScreen.this, SignUpActivity.class);

                    intent.putExtra(getResources().getString(R.string.user_type),mContext.getString(R.string.user_farmer));
                    startActivity(intent);
                }
            }
        });

        btn_retailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusRef.equals("Sign in")){
                    Intent intent = new Intent(WelcomeScreen.this, LoginActivity.class);
                    intent.putExtra(mContext.getString(R.string.user_type),mContext.getString(R.string.user_retailer));
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(WelcomeScreen.this, SignUpActivity.class);
                    intent.putExtra(mContext.getString(R.string.user_type), mContext.getString(R.string.user_retailer));
                    startActivity(intent);
                }
            }
        });
    }
}
