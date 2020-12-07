package com.islasoft.responsivedesign;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.islasoft.responsivedesign.db.entity.NotaEntity;

import java.util.List;


//Implementar los metodos de comunicacion entre fragmentos
public class NuevaNotaDialogViewModel extends AndroidViewModel {
    //Datos que queremos transferir
    private LiveData<List<NotaEntity>> allNotas;
    private NotaRepository notaRepository; //Clase que facilita los datos (local/API)

    //Se genera el contructor
    public  NuevaNotaDialogViewModel(Application application){
        super(application);

        //Instanciar
        notaRepository = new NotaRepository(application);
        allNotas = notaRepository.getAll(); //Devuelve todas las notas
    }

    //Devuelve los valores de la lista
    //El fragment que necesita recibir la nueva lista de datos
    public LiveData<List<NotaEntity>> getAllNotas(){ return allNotas;}

    //El fragment que inserte una nueva nota debera comunicarlo al ViewModel
    public void insertNota(NotaEntity new_nota_entity){ notaRepository.insert(new_nota_entity);}
}