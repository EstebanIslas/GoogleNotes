package com.islasoft.responsivedesign;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.islasoft.responsivedesign.db.NotaRoomDatabase;
import com.islasoft.responsivedesign.db.dao.NotaDao;
import com.islasoft.responsivedesign.db.entity.NotaEntity;

import java.util.List;

public class NotaRepository {

    private NotaDao notaDao; //Se importa la clase interface

    //Se crea una lista para obtener valores
    private LiveData<List<NotaEntity>> allNotas;
    private LiveData<List<NotaEntity>> allFavNotas;


    //Instanciar a partir del objeto Application una instancia de la BD
    public NotaRepository(Application application){
        //Accede a la base de datos local
        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);

        //Acceder a las operaciones del notadao
        notaDao = db.notaDao();

        //Se requiere de la accion getall en la interface NotaDao
        allNotas = notaDao.getAll();
        allFavNotas = notaDao.getAllFavoritas();
    }

    //Declarar operaciones al repositorio solo de manera local
    public LiveData<List<NotaEntity>> getAll() { return allNotas; }

    public LiveData<List<NotaEntity>> getAllFavs() { return allFavNotas; }

    public void insert (NotaEntity nota){
        //Se ejecutara una AsyncTask en segundo plano
        new insertAsyncTask(notaDao).execute(nota);
    }


    private static class insertAsyncTask extends AsyncTask<NotaEntity, Void, Void>{
        private NotaDao notaDaoAsyncTask;

        insertAsyncTask(NotaDao dao){
            notaDaoAsyncTask = dao;
        }
        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDaoAsyncTask.insert(notaEntities[0]); //Se inserta la nota nueva
            return null;
        }
    }


}
