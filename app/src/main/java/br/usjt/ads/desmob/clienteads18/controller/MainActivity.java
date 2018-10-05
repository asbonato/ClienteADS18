package br.usjt.ads.desmob.clienteads18.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.ads.desmob.clienteads18.R;
import br.usjt.ads.desmob.clienteads18.model.Cliente;
import br.usjt.ads.desmob.clienteads18.model.ClienteDAO;
import br.usjt.ads.desmob.clienteads18.model.ClientesDb;

public class MainActivity extends Activity {
    public static final String CHAVE = "br.usjt.ads.desmob.clienteads18.controller.chave";
    private EditText txtBusca;
    public static final String URL = "/rest/clientes";
    public static final String HOST = "http://10.70.8.236:8080/app_poetas";
    public static final String CLIENTES = "br.usjt.ads.desmob.clienteads18.controller.clientes";
    Intent intent;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBusca = findViewById(R.id.txt_busca);
        context = this;
    }

    public void buscarClientes(View view) {
        intent = new Intent(this, ListaClientesActivity.class);
        String texto = txtBusca.getText().toString();
        intent.putExtra(CHAVE, texto);
        if(ClienteDAO.isConnected(this)) {
            new DownloadClientes().execute(HOST + URL);
        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível! Usado cache.", Toast.LENGTH_LONG);
            toast.show();
            new CarregaClientes().execute("clientes");
        }

    }

    private class DownloadClientes extends AsyncTask<String, Void, ArrayList<Cliente>>{

        @Override
        protected ArrayList<Cliente> doInBackground(String... strings) {
            try {
                ArrayList<Cliente> clientes =  ClienteDAO.getClientes(strings[0]);
                ClientesDb db = new ClientesDb(context);
                db.insereClientes(clientes);
                return clientes;
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

    private class CarregaClientes extends AsyncTask<String, Void, ArrayList<Cliente>>{

        @Override
        protected ArrayList<Cliente> doInBackground(String... strings) {
            ClientesDb db = new ClientesDb(context);
            ArrayList<Cliente> clientes = db.listarClientes();
            return clientes;
        }

        protected void onPostExecute(ArrayList<Cliente> clientes){
            intent.putExtra(CLIENTES, clientes);
            startActivity(intent);
        }
    }
}
