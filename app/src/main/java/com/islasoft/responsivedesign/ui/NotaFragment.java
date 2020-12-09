package com.islasoft.responsivedesign.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.islasoft.responsivedesign.NuevaNotaDialogFragment;
import com.islasoft.responsivedesign.NuevaNotaDialogViewModel;
import com.islasoft.responsivedesign.R;
import com.islasoft.responsivedesign.db.entity.NotaEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class NotaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2; //Numero de Columnas que se muestran en la lista

    //VARIABLES AGREGADAS
    private List<NotaEntity> notaEntityList;
    private MyNotaRecyclerViewAdapter adapterNotas;

    private NuevaNotaDialogViewModel notaViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotaFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotaFragment newInstance(int columnCount) {
        NotaFragment fragment = new NotaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        //Se crea un metodo para forzar el menu de opciones que se creo en OnCreateOptionMenu
        setHasOptionsMenu(true); //Se indica que el fragment tiene un menu de opciones
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nota_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                //Se hace responsivo
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(mColumnCount, StaggeredGridLayoutManager.VERTICAL));
            }

            notaEntityList = new ArrayList<>();
            /*notaEntityList.add(new NotaEntity("Tarea de Ejemplo", "Descripcion de Tarea de Ejemplo", true, android.R.color.holo_blue_light));
            notaEntityList.add(new NotaEntity("Tarea de Ejemplo 1", "Descripcion de Tarea de Ejemplo Descripcion de Tarea de Ejemplo Descripcion de Tarea de Ejemplo Descripcion de Tarea de Ejemplo Descripcion de Tarea de Ejemplo ", false, android.R.color.holo_blue_light));
            notaEntityList.add(new NotaEntity("Tarea de Ejemplo 2", "Descripcion de Tarea de Ejemplo", true, android.R.color.holo_blue_light));*/


            adapterNotas = new MyNotaRecyclerViewAdapter(notaEntityList, getActivity());
            recyclerView.setAdapter(adapterNotas);

            //Metodo que se encarga de consultar la existencia de nuevos datos
            executeViewModel();
        }
        return view;
    }

    private void executeViewModel() {
        //Se llama el ViewModel Fragment
        notaViewModel = ViewModelProviders.of(getActivity())
                .get(NuevaNotaDialogViewModel.class);

        notaViewModel.getAllNotas().observe(getActivity(), new Observer<List<NotaEntity>>() {
            @Override
            public void onChanged(List<NotaEntity> notaEntities) {
                //Actualizar el Adapter
                adapterNotas.setNewNotas(notaEntities);
            }
        });//Observa si hay nuevos datos
    }

    //Agregando menú añadir
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_nota_fragment, menu);
    }

    //On click en el item de agregar nota
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itm_add_nota:
                mostrarDialogoNuevaNota();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //Abre el fragment en forma de dialog para insertar nota
    private void mostrarDialogoNuevaNota() {
        FragmentManager fm = getActivity().getSupportFragmentManager();

        //Se instancia la clase fragment
        NuevaNotaDialogFragment dialogNuevaNota = new NuevaNotaDialogFragment();

        //Mostrar el dialogo
        dialogNuevaNota.show(fm,"NuevaNotaDialogFragment");
    }
}