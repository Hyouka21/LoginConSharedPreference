package com.sosa.loginconsharedpreference.ui;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sosa.loginconsharedpreference.MainActivity;
import com.sosa.loginconsharedpreference.entidades.Usuario;
import com.sosa.loginconsharedpreference.request.ApiClient;

public class RegistroViewModel extends AndroidViewModel {
    private Context context;
 private MutableLiveData<Usuario> usuarioMutable;
    private MutableLiveData<String> avisoMutable;
    private MutableLiveData<Integer> avisoVisibilityMutable;

    public RegistroViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public MutableLiveData<Usuario> getUsuario() {
        if(usuarioMutable == null){
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }

    public LiveData<String> getAvisoMutable() {
        if (avisoMutable == null) {
            avisoMutable = new MutableLiveData<>();
        }
        return avisoMutable;
    }

    public LiveData<Integer> getAvisoVisibilityMutable() {
        if (avisoVisibilityMutable == null) {
            avisoVisibilityMutable = new MutableLiveData<>();
        }
        return avisoVisibilityMutable;
    }
    public void setUsuario(){
        usuarioMutable.setValue(ApiClient.leer(context));
    }
    public void Guardar(Usuario u) {
        Usuario usuario = ApiClient.leer(context);
        if (usuario == null || !(u.getMail().equalsIgnoreCase(usuario.getMail()))) {
            ApiClient.guardar(context, u);

            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show();
            context.startActivity(intent);

        } else {
            avisoMutable.setValue("El usuario ya existe");
            avisoVisibilityMutable.setValue(View.VISIBLE);
        }
    }
    public void Editar(Usuario u) {
        ApiClient.guardar(context, u);

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Toast.makeText(context.getApplicationContext(), "Usuario Editado", Toast.LENGTH_SHORT).show();
        context.startActivity(intent);

        avisoMutable.setValue("Usuario editado");
        avisoVisibilityMutable.setValue(View.VISIBLE);
    }



}
