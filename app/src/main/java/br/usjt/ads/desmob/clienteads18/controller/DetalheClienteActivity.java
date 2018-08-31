package br.usjt.ads.desmob.clienteads18.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import br.usjt.ads.desmob.clienteads18.R;

public class DetalheClienteActivity extends Activity {
    private TextView nomeCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_cliente);
        nomeCliente = findViewById(R.id.txt_nome);
        Intent intent = getIntent();
        String nome = intent.getStringExtra(ListaClientesActivity.NOME);
        nomeCliente.setText(nome);
    }
}
