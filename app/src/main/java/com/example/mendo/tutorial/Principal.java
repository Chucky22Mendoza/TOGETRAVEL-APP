package com.example.mendo.tutorial;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity implements View.OnClickListener{

    private TextView _titleText;
    EditText _txtUser, _txtPass;
    Button _btnLogIn, _btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Get the key for escape and back

        //Declare the Title text
        _titleText = (TextView) findViewById(R.id.id_txtTitle);

        //Call the function for change the title
        cronometro();

        //declare and get the user text and password text
        _txtUser = (EditText) findViewById(R.id.id_txtUser);
        _txtPass = (EditText) findViewById(R.id.id_txtPass);

        //declare buttons
        _btnLogIn = (Button) findViewById(R.id.id_btnLogIn);
        _btnSignUp = (Button) findViewById(R.id.id_btnSignIn);

        //Send the buttons to method listener
        _btnLogIn.setOnClickListener(this);
        _btnSignUp.setOnClickListener(this);

    }

    //Code for change the title in the first screen, "TogeTravel" to "Welcome"
    //Change the title like 6 seconds once to once
    public void cronometro(){

            new CountDownTimer(6000,1000){

                @Override
                public void onTick(long millisUntilfinished){

                    _titleText.setText("TogeTravel");
                    _titleText.setTextColor(Color.rgb(0,0,255));

                }

                @Override
                public void onFinish() {

                    new CountDownTimer(6000,1000){

                        @Override
                        public void onTick(long millisUntilfinished){

                            _titleText.setText("¡Welcome!");
                            _titleText.setTextColor(Color.rgb(255,0,0));

                        }

                        @Override
                        public void onFinish() {

                            cronometro();

                        }

                    }.start();
                }
            }.start();

    }

    //All the events when a button is clicked is right in this part
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            //Click Log In
            case R.id.id_btnLogIn:

                //We need enter with a user and password and check into the database
                String _user = _txtUser.getText().toString();
                String _pass = _txtPass.getText().toString();

                if(_user.equals("Chucky22Mendoza")){

                    if (_pass.equals("milito22")) {

                        Intent openChat = new Intent(Principal.this,Chat.class);
                        startActivity(openChat);
                        finish();

                    }else{

                        Toast.makeText(getApplicationContext(),"Password Incorrect",Toast.LENGTH_LONG).show();

                    }

                }else{

                    Toast.makeText(getApplicationContext(),"User Incorrect",Toast.LENGTH_LONG).show();

                }

                break;

            //Click Sign up
            case R.id.id_btnSignIn:

                Intent Regis = new Intent(Principal.this,Register.class);
                startActivity(Regis);
                finish();

                break;
        }
    }
}
