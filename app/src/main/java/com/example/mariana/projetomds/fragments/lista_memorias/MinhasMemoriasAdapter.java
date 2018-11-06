package com.example.mariana.projetomds.fragments.lista_memorias;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mariana.projetomds.R;
import com.example.mariana.projetomds.persist.model.Memoria;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MinhasMemoriasAdapter extends RecyclerView.Adapter<MinhasMemoriasAdapter.ViewHolder>{

    protected List<Memoria> memoriaList;
    private OnRecyclerViewSelectedMemorias onRecyclerViewSelectedMemorias;
    private Context context;

    MinhasMemoriasAdapter(List<Memoria> memoriaList, Context context){
        this.memoriaList = memoriaList;
        this.context = context;
    }


    //infla o componente view
    @Override
    public MinhasMemoriasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memoria, parent, false);
        return new ViewHolder(v);
    }

    //seta os dados nas views
    @Override
    public void onBindViewHolder(MinhasMemoriasAdapter.ViewHolder holder, int position) {
        Memoria minhasMemorias = memoriaList.get(position);

        System.out.println(minhasMemorias.getNome());

        holder.nome_memoria.setText(minhasMemorias.getNome());
        holder.local.setText(minhasMemorias.getLocal());
        holder.data.setText(minhasMemorias.getData());
    }

    @Override
    public int getItemCount() {
        return memoriaList.size();
    }

    //mapeamento dos componentes da view
    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.nome_memoria)
        TextView nome_memoria;

        @BindView(R.id.local)
        TextView local;

        @BindView(R.id.data)
        TextView data;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        //seta o clique rápido
        @OnClick(R.id.container)
        void onItemClick(View view){
            if(onRecyclerViewSelectedMemorias != null)
                onRecyclerViewSelectedMemorias.onClick(view, getAdapterPosition());

        }

    }
    public void setOnRecyclerViewSelectedMemorias(OnRecyclerViewSelectedMemorias onRecyclerViewSelectedMemorias){
        this.onRecyclerViewSelectedMemorias = onRecyclerViewSelectedMemorias;
    }
}
