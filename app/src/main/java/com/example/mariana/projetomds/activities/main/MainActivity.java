package com.example.mariana.projetomds.activities.main;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mariana.projetomds.R;
import com.example.mariana.projetomds.adapters.AbasAdapter;
import com.example.mariana.projetomds.fragments.mapa.MapaActivity;
import com.example.mariana.projetomds.fragments.criar_memoria.CriarMemoriaActivity;
import com.example.mariana.projetomds.fragments.lista_memorias.MinhasMemoriasActivity;
import com.example.mariana.projetomds.persist.dao.MemoriaDAO;
import com.example.mariana.projetomds.persist.model.Memoria;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView{

    MainPresenter mainPresenter;
    MemoriaDAO memoriaDAO;
    Memoria memoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);

        AbasAdapter adapter = new AbasAdapter( getSupportFragmentManager() );
        adapter.adicionar( new MapaActivity() , "");
        adapter.adicionar( new CriarMemoriaActivity(), "");
        adapter.adicionar( new MinhasMemoriasActivity(), "");


//        MapaActivity mapaFragment = (MapaActivity) getFragmentManager().findFragmentById(R.id);

        ViewPager viewPager = (ViewPager) findViewById(R.id.abas_view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.abas);
        tabLayout.setupWithViewPager(viewPager);

        mainPresenter.popularMemorias(this);

        //Colocar ícones melhores
        tabLayout.getTabAt(0).setIcon(R.drawable.icons8marcadordemapafilled50).setTag(0);
        tabLayout.getTabAt(1).setIcon(R.drawable.icons8maisfilled50).setTag(1);
        tabLayout.getTabAt(2).setIcon(R.drawable.icons8listafilled50).setTag(2);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                FragmentManager fm = getSupportFragmentManager();

                MapaActivity fragment = (MapaActivity)fm.findFragmentByTag("android:switcher:" + R.id.abas_view_pager + ":0");

                CriarMemoriaActivity fragment2 = (CriarMemoriaActivity)fm.findFragmentByTag("android:switcher:" + R.id.abas_view_pager + ":1");


                if (fragment.getLocalizacaoAtual() != null){
                    fragment2.setLocalizacao(fragment.getLocalizacaoAtual());
                    //Log.d("TO LOC", "to aqui 1 " + fragment.getLocalizacaoAtual());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                FragmentManager fm = getSupportFragmentManager();

                MapaActivity fragment = (MapaActivity)fm.findFragmentByTag("android:switcher:" + R.id.abas_view_pager + ":0");

                CriarMemoriaActivity fragment2 = (CriarMemoriaActivity)fm.findFragmentByTag("android:switcher:" + R.id.abas_view_pager + ":1");


                if (fragment.getLocalizacaoAtual() != null){
                    fragment2.setLocalizacao(fragment.getLocalizacaoAtual());
                    //Log.d("TO LOC", "to aqui 1 " + fragment.getLocalizacaoAtual());
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
              //  Log.d("TO LOC", "to aqui 3");
            }
        });

    }

    //Colocar diferente para cada Fragment

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_salvar_memoria, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_salvar:
//                mainPresenter.cadastrarMemoria();
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

//    @Override
//    public void abrirMainActivity() {
//        memoria.setAtiva(Memoria.SITUACAO_ATIVA);
//        memoriaDAO.update(memoria);
//        Toast.makeText(MainActivity.this, "Memoria cadastrada com sucesso", Toast.LENGTH_LONG).show();
//        Intent abrirDetalhes = new Intent(MainActivity.this, AtividadesDetailActivity.class);
//        abrirDetalhes.putExtra("memoria_id", memoria.getId());
//        startActivity(abrirDetalhes);
//        finish();
//    }

}
