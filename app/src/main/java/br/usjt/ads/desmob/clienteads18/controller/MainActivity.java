package br.usjt.ads.desmob.clienteads18.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.ads.desmob.clienteads18.R;
import br.usjt.ads.desmob.clienteads18.model.Cliente;
import br.usjt.ads.desmob.clienteads18.model.ClienteDAO;

public class MainActivity extends Activity {
    public static final String CHAVE = "br.usjt.ads.desmob.clienteads18.controller.chave";
    private EditText txtBusca;
    public static final String URL = "http://192.168.0.15:8080/app_poetas/rest/clientes";
    public static final String CLIENTES = "br.usjt.ads.desmob.clienteads18.controller.clientes";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBusca = findViewById(R.id.txt_busca);
    }

    public void buscarClientes(View view) {
        intent = new Intent(this, ListaClientesActivity.class);
        String texto = txtBusca.getText().toString();
        intent.putExtra(CHAVE, texto);

        new DownloadClientes().execute(URL);

    }

    private class DownloadClientes extends AsyncTask<String, Void, ArrayList<Cliente>>{

        @Override
        protected ArrayList<Cliente> doInBackground(String... strings) {
            try {
                return ClienteDAO.getClientes(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<Cliente>();
        }

        protected void onPostExecute(ArrayList<Cliente> clientes){
            intent.putExtra(CLIENTES, clientes);
            startActivity(intent);
        }
    }
}
