package com.islasoft.responsivedesign.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.snackbar.Snackbar;
import com.islasoft.responsivedesign.NuevaNotaDialogViewModel;
import com.islasoft.responsivedesign.db.entity.NotaEntity;
import com.islasoft.responsivedesign.R;

import java.util.List;


public class MyNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder> {

    private List<NotaEntity> mValues; //Se elimina el valor final
    private Context ctx;

    private NuevaNotaDialogViewModel viewModel;

    public MyNotaRecyclerViewAdapter(List<NotaEntity> items, Context ctx)
    {
        mValues = items;
        this.ctx = ctx;
        //Conecta al Fragment con el viewmodel para actualizar
        viewModel = ViewModelProviders.of((AppCompatActivity)ctx).get(NuevaNotaDialogViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_nota, parent, false);
        return new ViewHolder(view);
    }

    //Metodo que Settea/Actualiza la nueva lista de notas
    public void setNewNotas(List<NotaEntity> nuevaNotas){
        this.mValues = nuevaNotas; //Se le envia la nueva lista para actualizar
        notifyDataSetChanged(); //Actualizar el adapter
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tv_titulo.setText(holder.mItem.getTitulo());
        holder.tv_contenido.setText(holder.mItem.getContenido());

        if (holder.mItem.isFavorita()){
            holder.imv_favorito.setImageResource(R.drawable.ic_baseline_star_24);
        }

        holder.imv_favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mItem.isFavorita()){ //Si la nota no es favorita
                    holder.mItem.setFavorita(false);
                    holder.imv_favorito.setImageResource(R.drawable.ic_baseline_star_border_24);
                    Snackbar.make(v, "Nota Actualizada", Snackbar.LENGTH_SHORT).show();
                }else {
                    holder.mItem.setFavorita(true);
                    holder.imv_favorito.setImageResource(R.drawable.ic_baseline_star_24);
                    Snackbar.make(v, "Nota Actualizada", Snackbar.LENGTH_SHORT).show();
                }

                viewModel.updateNota(holder.mItem); //Se envia al ViewModel
            }
        });
    }

    /*private void toast(String txt){
        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tv_titulo;
        public final TextView tv_contenido;
        public final ImageView imv_favorito;

        public NotaEntity mItem;

        public ViewHolder(View view){
            super(view);
            mView = view;
            tv_titulo = view.findViewById(R.id.tv_titulo);
            tv_contenido = view.findViewById(R.id.tv_contenido);
            imv_favorito = view.findViewById(R.id.imv_favorito);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tv_titulo.getText() + "'";
        }
    }
}