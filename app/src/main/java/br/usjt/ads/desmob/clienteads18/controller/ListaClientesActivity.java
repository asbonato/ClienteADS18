package br.usjt.ads.desmob.clienteads18.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.usjt.ads.desmob.clienteads18.R;

public class ListaClientesActivity extends Activity {
    public static final String NOME = "br.usjt.ads.desmob.clienteads18.controllernomedocliente";
    private ArrayList<String> clientes;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_lista_clientes);
        Intent intent = getIntent();
        String chave = intent.getStringExtra(MainActivity.CHAVE);
        clientes = buscaClientes(chave);
        ListView listView = findViewById(R.id.lista_clientes);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, clientes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nome = clientes.get(i);
                Intent intent = new Intent(activity, DetalheClienteActivity.class);
                intent.putExtra(NOME, nome);
                startActivity(intent);
            }
        });

    }



    private ArrayList<String> buscaClientes(String chave){
        ArrayList<String> resultado;

        if(chave != null && chave.length() > 0){
            resultado = new ArrayList<>();
            ArrayList<String> lista = listaClientes();
            for(String cliente:lista){
                if(cliente.toUpperCase().contains(chave.toUpperCase())){
                    resultado.add(cliente);
                }
            }
            return resultado;
        } else {
            return listaClientes();
        }
    }


    private ArrayList<String> listaClientes(){
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Carlos Drummond de Andrade");
        lista.add("Manuel Bandeira");
        lista.add("Olavo Bilac");
        lista.add("Vinícius de Moraes");
        lista.add("Cecília Meireles");
        lista.add("Castro Alves");
        lista.add("Gonçalves Dias");
        lista.add("Ferreira Gullar");
        lista.add("Machado de Assis");
        lista.add("Mário de Andrade");
        lista.add("Cora Coralina");
        lista.add("Manoel de Barros");
        lista.add("João Cabral de Melo Neto");
        lista.add("Casimiro de Abreu");
        lista.add("Paulo Leminski");
        lista.add("Álvares de Azevedo");
        lista.add("Guilherme de Almeida");
        lista.add("Alphonsus de Guimarães");
        lista.add("Mário Quintana");
        lista.add("Gregório de Matos");
        lista.add("Augusto dos Anjos");

        return lista;
    }
}
