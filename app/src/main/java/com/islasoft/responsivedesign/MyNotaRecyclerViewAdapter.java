package com.islasoft.responsivedesign;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class MyNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder> {

    private final List<NotaEntity> mValues;
    private Context ctx;

    public MyNotaRecyclerViewAdapter(List<NotaEntity> items, Context ctx)
    {
        mValues = items;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_nota, parent, false);
        return new ViewHolder(view);
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
                Snackbar.make(v, "Doing something", Snackbar.LENGTH_SHORT).show();
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