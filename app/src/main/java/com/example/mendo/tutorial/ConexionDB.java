package com.example.mendo.tutorial;

import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by mendo on 23/12/2017.
 */

public class ConexionDB {
    Connection _con; //Variable para conectar tipo connection
    public String _driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // driver de Sql Server
    public String _url = "jdbc:sqlserver://localhost:14330;databaseName=RegisterTogeTravel";    //url para conectar a la DB local

    //Método para conectar con 2 variables de entrada que son usuario y contraseña
    public Connection getConexion(String _usuario, String _contraseña){
        try{
            System.out.println("Conexión realizada"); //debug 1
            Class.forName(_driver); //Mandar a llamar el driver
            System.out.println("Conexión realizada2"); //debug 2
            _con=DriverManager.getConnection(_url,_usuario,_contraseña); //Hacer la conexión dependiendo del usuario ingresado en la interfz
            System.out.println("Conexión realizada3");//debug 3
        }catch (Exception e){
            System.out.println("Error de conexión");//Error de conexión
        }
        return _con ;
    }
}
