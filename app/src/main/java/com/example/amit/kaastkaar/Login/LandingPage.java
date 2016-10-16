package com.example.amit.kaastkaar.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.amit.kaastkaar.R;

/**
 * Created by amit on 11-10-2016.
 */
public class LandingPage extends AppCompatActivity {
    private Button btnSignUp, btnSignIn;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.landing_page);

        btnSignIn = (Button) findViewById(R.id.btn_login);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(LandingPage.this, WelcomeScreen.class);
                intent.putExtra("refKey", "Sign in");
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(LandingPage.this,WelcomeScreen.class);
                intent.putExtra("refKey", "Sign up");
                startActivity(intent);
            }
        });
    }
}
