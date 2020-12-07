package com.islasoft.responsivedesign.db;

//Añadiendo una Base de Datos
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.islasoft.responsivedesign.db.dao.NotaDao;
import com.islasoft.responsivedesign.db.entity.NotaEntity;

//Se añade la version y las entidades que existen en la BD
@Database(entities = {NotaEntity.class}, version = 1)
public abstract class NotaRoomDatabase extends RoomDatabase {

    //Declarar la interface DAO
    public abstract NotaDao notaDao();

    //Crear la variable que guarda la instancia de la bd
    private static volatile NotaRoomDatabase INSTANCE;

    //Metodo para permitir la instancia de la BD
    public static NotaRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){//Decide si se invoca por primera vez la BD
            synchronized (NotaRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotaRoomDatabase.class, "notas_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
