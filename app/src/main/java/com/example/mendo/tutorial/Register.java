package com.example.mendo.tutorial;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button _btnAcept;
    EditText _txtName, _txtUsers, _txtPW, _txtPWC, _txtGender, _txtCelPhone, _txtCountry;
    CheckBox _ckTerCon;
    TextView _txtCk;
    String TAG = "Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Id for EditText
        _txtName = (EditText) findViewById(R.id.id_formName);
        _txtUsers = (EditText) findViewById(R.id.id_formUser);
        _txtPW = (EditText) findViewById(R.id.id_formPass);
        _txtPWC = (EditText) findViewById(R.id.id_formCP);
        _txtGender = (EditText) findViewById(R.id.id_formGender);
        _txtCelPhone = (EditText) findViewById(R.id.id_formCelPhone);
        _txtCountry = (EditText) findViewById(R.id.id_formCountry);

        //Id for CheckBox
        _ckTerCon = (CheckBox) findViewById(R.id.id_ckTerCon);
        _txtCk = (TextView) findViewById(R.id.id_txtForCk);

        //Id for buttons
        _btnAcept = (Button) findViewById(R.id.id_btnFormAcept);

        //Go for onClick Method
        _ckTerCon.setOnClickListener(this);
        _btnAcept.setOnClickListener(this);
        _txtCk.setOnClickListener(this);

        //Set enabled false or true
        _btnAcept.setEnabled(false);

    }

    //This method is the connection with the database on sql server in a local server
    //Connection with sql server
    public Connection conexionDB(){

        Connection _conexion=null;

        try{

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String _idComputer = "190.168.1.70";
            String _DBName= "RegisterTogeTravel";
            String _userDB="DBMendoza";
            String _password= "123";
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

            //String _server = "jdbc:jtds:sqlserver://" + _idComputer + "/" + _DBName;
            //_cn = DriverManager.getConnection(_server,_user,_password);

            //Error encontrado
            _conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://"
                    + _idComputer + ";databaseName="
                    + _DBName + ";user="
                    + _userDB + ";password="
                    + _password + ";");
            //Error encontrado

            Log.d("Connection: ","Done");

        }catch(Exception e){

            Log.d("Connection: ","Catch");
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

        }

        Log.d("Connection: ","Return: "+_conexion);
        return _conexion;

    }

    //This method is for register or sign up the users

    public void User(){

        try{

            Log.d("User: ","Inicio del Try");
            //String _gender;
            //_gender = _txtGender.getText().toString();
            //_gender = _gender.toUpperCase();
            PreparedStatement _pst = conexionDB2().prepareStatement("insert into usuario" +
                    "(nombre, usuario, contraseña, confirmContraseña, sexo, celular, pais) " +
                    "values (?,?,?,?,?,?,?)");
            _pst.setString(1,_txtName.getText().toString()); //Name
            _pst.setString(2,_txtUsers.getText().toString()); //user
            _pst.setString(3,_txtPW.getText().toString()); //Password
            _pst.setString(4,_txtPWC.getText().toString()); //Confirm Password
            _pst.setString(5,_txtGender.getText().toString()); //Gender
            _pst.setString(6,_txtCelPhone.getText().toString()); //Cellphone number
            _pst.setString(7,_txtCountry.getText().toString()); //Country
            _pst.executeUpdate();

            Toast.makeText(getApplicationContext(),"COMPLETADO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
            Log.d("User: ","Final del Try");

        }catch (SQLException e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            Log.d("User: ","Catch");

        }
    }

    //Connection with Mysql
    private Connection _cn = null;
    Statement _st;
    private String _user = "root";
    private String _pass = "";
    private String _host = "localhost";
    private String _nombreDB = "registertogetravel";

    public Connection conexionDB2(){

        try{
            Log.d("phpMyAdmin "," Server");
            Class.forName("com.mysql.jdbc.Driver").newInstance( );
            String _server = "jdbc:mysql://" + _host + "/" + _nombreDB;
            _cn = DriverManager.getConnection(_server,_user,_pass);
            Log.d("phpMyAdmin "," Server conectado" + _cn);
            return _cn;

        }catch(Exception e){

            e.printStackTrace();
            Log.d("phpMyAdmin "," Server: " + _cn);
            return _cn;


        }
    }

    Statement createStatement(){
        throw new UnsupportedOperationException("No soportado");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            //Button for acept the form
            case R.id.id_btnFormAcept:

                //We need the button enabled
                if (_btnAcept.isEnabled()) {

                    //We check if the fields are Empty or not, for try send the values
                    if(_txtName.getText().toString().isEmpty() || _txtUsers.getText().toString().isEmpty()
                            || _txtName.getText().toString().isEmpty() || _txtPW.getText().toString().isEmpty()
                            || _txtPWC.getText().toString().isEmpty() || _txtGender.getText().toString().isEmpty()
                            || _txtCelPhone.getText().toString().isEmpty() || _txtCountry.getText().toString().isEmpty()){

                        Toast.makeText(getApplicationContext(), "Es necesario llenar todos los campos", Toast.LENGTH_LONG).show();

                    }else{

                        //Confirm the passwords are the same
                        if (_txtPW.getText().toString().equals(_txtPWC.getText().toString())) {

                            //Look the termins and conditions is checked
                            if (_ckTerCon.isChecked()){

                                //Send all parameters to the DataBase and go back to starActivity
                                User();
                                Log.d("btnAcept: ","Sí jala");
                                Intent main = new Intent(Register.this, Principal.class);
                                startActivity(main);
                                finish();

                            }else {

                                Toast.makeText(getApplicationContext(), "Acepte terminos y condiciones de uso", Toast.LENGTH_LONG).show();

                            }
                        } else {

                            Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();

                        }
                    }
                }

                break;

            //Button for convert enabled the button acept after all is done, the most important is the check
            case R.id.id_ckTerCon:

                if (_ckTerCon.isChecked()) {

                    _btnAcept.setEnabled(true);

                }else {

                    _btnAcept.setEnabled(false);

                }

                break;

            //Label or text for the check (same logic)
            case R.id.id_txtForCk:

                if (_ckTerCon.isChecked()){

                    _ckTerCon.setChecked(false);
                    _btnAcept.setEnabled(false);

                }else{

                    _ckTerCon.setChecked(true);
                    _btnAcept.setEnabled(true);

                }

                break;
        }
    }
}
