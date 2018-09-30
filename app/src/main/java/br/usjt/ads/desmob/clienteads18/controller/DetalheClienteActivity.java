package br.usjt.ads.desmob.clienteads18.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.ads.desmob.clienteads18.R;
import br.usjt.ads.desmob.clienteads18.model.Cliente;
import br.usjt.ads.desmob.clienteads18.model.ClienteDAO;
import br.usjt.ads.desmob.clienteads18.model.Util;

public class DetalheClienteActivity extends Activity {
    public static final String IMG = MainActivity.HOST+"/img/";
    private TextView nome, fone, email;
    private ImageView foto;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_cliente);
        nome = findViewById(R.id.detalhe_txt_nome);
        fone = findViewById(R.id.detalhe_txt_fone);
        email = findViewById(R.id.detalhe_txt_email);
        foto = findViewById(R.id.detalhe_foto_cliente);
        context = this;

        Intent intent = getIntent();
        Cliente cliente = (Cliente)intent.getSerializableExtra(ListaClientesActivity.CLIENTE);
        nome.setText(cliente.getNome().toString());
        fone.setText(cliente.getFone().toString());
        email.setText(cliente.getEmail().toString());
        new DownloadImagem().execute(IMG+cliente.getFigura()+".jpg");

    }

    private class DownloadImagem extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap imagem = null;
            try {
                imagem = ClienteDAO.getImage(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(imagem == null){
                imagem = ((BitmapDrawable)context.getDrawable(R.drawable.face)).getBitmap();
            }
            return imagem;
        }

        protected void onPostExecute(Bitmap imagem){
            foto.setImageBitmap(imagem);
        }
    }
}