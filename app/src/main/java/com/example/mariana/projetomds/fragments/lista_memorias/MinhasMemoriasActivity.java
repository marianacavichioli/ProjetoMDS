package com.example.mariana.projetomds.fragments.lista_memorias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mariana.projetomds.activities.memorias_detail.MinhasMemoriasDetailActivity;
import com.example.mariana.projetomds.R;
import com.example.mariana.projetomds.persist.dao.MemoriaDAO;
import com.example.mariana.projetomds.persist.model.Memoria;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MinhasMemoriasActivity extends Fragment implements MinhasMemoriasView {

    @BindView(R.id.rvMemorias)
    RecyclerView rvMemorias;

    MinhasMemoriasPresenter minhasMemoriasPresenter;

    Context context;
    Memoria memoria = new Memoria();
    MemoriaDAO memoriaDAO = new MemoriaDAO(context);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_memorias, container, false);

        ButterKnife.bind(this, view);

        minhasMemoriasPresenter = new MinhasMemoriasPresenter(this);

        context = getActivity();

        minhasMemoriasPresenter.memoriasList(context);

        return view;
    }

    public void updateList(final List<Memoria> memoriasList) {

        //seta o adapter
        MinhasMemoriasAdapter memoriasAdapter = new MinhasMemoriasAdapter(memoriasList, context);

        memoriasAdapter.setOnRecyclerViewSelectedMemorias(new OnRecyclerViewSelectedMemorias() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent
                        (getActivity(), MinhasMemoriasDetailActivity.class);
                intent.putExtra("memoria_id", memoriasList.get(position).getId());
                startActivity(intent);
            }

        });

        rvMemorias.setAdapter(memoriasAdapter);

        // criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(context, layoutManager.getOrientation());
        rvMemorias.setLayoutManager(layoutManager);
        rvMemorias.addItemDecoration(dividerItemDecoration);
    }


}
