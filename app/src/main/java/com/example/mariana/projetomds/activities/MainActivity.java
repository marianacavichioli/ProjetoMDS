package com.example.mariana.projetomds.activities;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mariana.projetomds.R;
import com.example.mariana.projetomds.adapters.AbasAdapter;
import com.example.mariana.projetomds.fragments.mapa.MapaActivity;
import com.example.mariana.projetomds.fragments.criar_memoria.CriarMemoriaActivity;
import com.example.mariana.projetomds.fragments.lista_memorias.MinhasMemoriasActivity;
import com.example.mariana.projetomds.persist.dao.MemoriaDAO;
import com.example.mariana.projetomds.persist.model.Memoria;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AbasAdapter adapter = new AbasAdapter( getSupportFragmentManager() );
        adapter.adicionar( new MapaActivity() , "Mapa");
        adapter.adicionar( new CriarMemoriaActivity(), "Criar Memória");
        adapter.adicionar( new MinhasMemoriasActivity(), "Minhas Memórias");

        ViewPager viewPager = (ViewPager) findViewById(R.id.abas_view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.abas);
        tabLayout.setupWithViewPager(viewPager);

        popularMemorias(this);

        //Colocar ícone
        //tabLayout.getTabAt(0).setIcon(R.drawable.icone);
    }

    public void popularMemorias(Context contexto){
        MemoriaDAO memoriaDAO = new MemoriaDAO(this);

        ArrayList<Memoria> listMemorias = memoriaDAO.getMemorias();

        //if(listMemorias.size() <= 0)
        memoriaDAO.popularMemorias();
    }

//Colocar diferente para cada Fragment

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_cadastro_template1, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

}
