package com.example.mariana.projetomds.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mariana.projetomds.R;
import com.example.mariana.projetomds.adapters.AbasAdapter;
import com.example.mariana.projetomds.fragments.MapaActivity;
import com.example.mariana.projetomds.fragments.CriarMemoriaActivity;
import com.example.mariana.projetomds.fragments.lista_memorias.MinhasMemoriasActivity;

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

        //Colocar ícone
        //tabLayout.getTabAt(0).setIcon(R.drawable.icone);
    }
}
