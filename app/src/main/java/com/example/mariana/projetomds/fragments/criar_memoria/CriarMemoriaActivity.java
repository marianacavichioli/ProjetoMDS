package com.example.mariana.projetomds.fragments.criar_memoria;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mariana.projetomds.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CriarMemoriaActivity extends Fragment implements CriarMemoriaView.View {

    @BindView(R.id.imagem_memoria)
    ImageView imageView;

    @BindView(R.id.titulo)
    EditText titulo;

    @BindView(R.id.descricao)
    EditText descricao;

    Bitmap selectedImagePath;

    CriarMemoriaPresenter criarMemoriaPresenter;

    Location localizacaoAtual;
//    PlaceDetectionClient mPlaceDetectionClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_criar, container, false);

        ButterKnife.bind(this, view);

        criarMemoriaPresenter = new CriarMemoriaPresenter(getActivity(), this);

        // Construct a PlaceDetectionClient.
//        mPlaceDetectionClient = Places.getPlaceDetectionClient(getActivity());

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        criarMemoriaPresenter.verificaResultado(requestCode, resultCode, data);
    }

    public void setLocalizacao(Location localizacao) {
        this.localizacaoAtual = localizacao;
    }

    @OnClick(R.id.btn_galeria)
    public void acessarGaleria() {
        criarMemoriaPresenter.abrirGaleria();
    }

    @OnClick(R.id.btn_camera)
    public void tirarFoto() {
        criarMemoriaPresenter.tiraFoto();
    }

    @OnClick(R.id.botao_salvar)
    public void salvar() {

        String mLocal = getAddress(localizacaoAtual.getLatitude(), localizacaoAtual.getLongitude());
        if(mLocal==null){
            mLocal = "";
        }
        criarMemoriaPresenter.cadastrar(titulo.getText().toString(), descricao.getText().toString(), selectedImagePath, mLocal, localizacaoAtual.getLatitude(), localizacaoAtual.getLongitude());
    }

    @Override
    public void carregaImagem(Bitmap caminhoArquivo) {
        selectedImagePath = caminhoArquivo;
        imageView.setImageBitmap(caminhoArquivo);
    }

    @Override
    public void abreActivity(Intent intent, Integer codigo) {
        startActivityForResult(intent, codigo);
    }

    public String getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();

            Log.d("PLACES", "Address" + add);
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();

            // TennisAppActivity.showDialog(add);
            return obj.getLocality();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
