package com.islasoft.responsivedesign.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.islasoft.responsivedesign.db.entity.NotaEntity;

import java.util.List;

//Permite las acciones CRUD de una Base de Datos
@Dao
public interface NotaDao {
    //Implementando metodos CRUD

    @Insert //Crear
    void insert(NotaEntity nota);

    @Update //Actualizar
    void update(NotaEntity nota);

    @Query("DELETE FROM notas") //Eliminar Todos los registros
    void deleteAll();

    @Query("DELETE FROM notas WHERE id = :idNota") //Eliminar un registro via id
    void deleteById(int idNota);

    @Query("SELECT * FROM notas ORDER BY titulo ASC") //Consultar todos los registros
    //Se adapta LiveData para trabajar con datos en tiempo real
    LiveData<List<NotaEntity>> getAll(); //Se crea una lista y como parametro se anexan las entidades de la tabla

    @Query("SELECT * FROM notas WHERE favorita LIKE 'true'")
    //Se agregan para datos dinamicos
    LiveData<List<NotaEntity>> getAllFavoritas();
}
