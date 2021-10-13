package com.sosa.loginconsharedpreference.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.sosa.loginconsharedpreference.entidades.Usuario;

public class ApiClient {
    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context){
        if(sp == null){
            sp = context.getSharedPreferences("datos",0);
        }
        return sp;
    }

    public static void guardar(Context context, Usuario usuario){
        SharedPreferences sp = conectar(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putLong("dni", usuario.getDni());
        editor.putString("apellido", usuario.getApellido());
        editor.putString("nombre", usuario.getNombre());
        editor.putString("mail", usuario.getMail());
        editor.putString("pass", usuario.getPassword());
        editor.commit();
    }
    public static Usuario leer(Context context){
        SharedPreferences sp = conectar(context);

        Long dni = sp.getLong("dni",-1);
        String apellido = sp.getString("apellido","-1");
        String nombre = sp.getString("nombre","-1");
        String mail = sp.getString("mail","-1");
        String pass = sp.getString("pass","-1");

        Usuario usuario = new Usuario(dni, nombre, apellido, mail, pass);
        return usuario;
    }

    public static Usuario login(Context context, String mail, String pass){
        Usuario usuario = null;
        SharedPreferences sp = conectar(context);

        Long dni = sp.getLong("dni",-1);
        String apellido = sp.getString("apellido","-1");
        String nombre = sp.getString("nombre","-1");
        String email = sp.getString("mail","-1");
        String passw = sp.getString("pass","-1");

        if(mail.equals(email) && pass.equals(passw)){
            usuario = new Usuario(dni, nombre, apellido, email, passw);
        } else {
            Toast.makeText(context.getApplicationContext(), "Datos Invalidos", Toast.LENGTH_LONG).show();
        }
        return usuario;
    }
}
