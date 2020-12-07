package com.islasoft.responsivedesign;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import org.jetbrains.annotations.NotNull;

public class NuevaNotaDialogFragment extends DialogFragment {

    private NuevaNotaDialogViewModel mViewModel;

    public static NuevaNotaDialogFragment newInstance() {
        return new NuevaNotaDialogFragment();
    }

    private View view;
    //Declarar variables View Components
    private EditText edt_titulo;
    private EditText edt_contenido;
    private RadioGroup rdg_color;
    private Switch swt_favorita;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nueva_nota_dialog_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NuevaNotaDialogViewModel.class);
        // TODO: Use the ViewModel
    }

    //Creando un progressDialog para creaciones de notas
    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nueva Nota");
        builder.setMessage("Introduzca los datos de la nueva nota")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Rescate de variables
                        String titulo = edt_titulo.getText().toString();
                        String contenido = edt_contenido.getText().toString();
                        String color = "azul"; //Se envia como predefinido
                        switch (rdg_color.getCheckedRadioButtonId()){
                            case R.id.rbn_color_rojo:
                                color = "rojo";
                                break;
                            case R.id.rbn_color_verde:
                                color = "verde";
                                break;
                        }

                        boolean favorita = swt_favorita.isChecked();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        //Introducir en el codigo un layout
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.nueva_nota_dialog_fragment, null);

        //Instanciar
        edt_titulo = view.findViewById(R.id.edt_titulo);
        edt_contenido = view.findViewById(R.id.edt_contenido);
        rdg_color = view.findViewById(R.id.rdg_color);
        swt_favorita = view.findViewById(R.id.swt_favorita);

        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create();
    }

}