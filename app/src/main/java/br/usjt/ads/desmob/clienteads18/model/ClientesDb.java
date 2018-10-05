package br.usjt.ads.desmob.clienteads18.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ClientesDb {
    ClienteDbHelper dbHelper;

    public ClientesDb(Context context){
        dbHelper = new ClienteDbHelper(context);
    }

    public void insereClientes(ArrayList<Cliente> clientes){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //apaga todos os clientes antes de inserir novamente
        db.delete(ClientesDbContract.Cliente.TABLE_NAME, null, null);

        ContentValues values = new ContentValues();
        for(Cliente cliente:clientes){
            values.put(ClientesDbContract.Cliente.ID, cliente.getId());
            values.put(ClientesDbContract.Cliente.NOME, cliente.getNome());
            values.put(ClientesDbContract.Cliente.FONE, cliente.getFone());
            values.put(ClientesDbContract.Cliente.EMAIL, cliente.getEmail());
            values.put(ClientesDbContract.Cliente.FOTO, cliente.getFigura());
            db.insert(ClientesDbContract.Cliente.TABLE_NAME, null, values);
        }
    }

    public ArrayList<Cliente> listarClientes(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<Cliente> clientes = new ArrayList<>();

        String[] colunas = {
                ClientesDbContract.Cliente.ID,
                ClientesDbContract.Cliente.NOME,
                ClientesDbContract.Cliente.FONE,
                ClientesDbContract.Cliente.EMAIL,
                ClientesDbContract.Cliente.FOTO
        };

        String ordem = ClientesDbContract.Cliente.NOME;

        Cursor c = db.query(ClientesDbContract.Cliente.TABLE_NAME, colunas, null, null, null, null, ordem);

        while(c.moveToNext()){
            Cliente cliente = new Cliente();
            cliente.setId(c.getInt(c.getColumnIndex(ClientesDbContract.Cliente.ID)));
            cliente.setNome(c.getString(c.getColumnIndex(ClientesDbContract.Cliente.NOME)));
            cliente.setFone(c.getString(c.getColumnIndex(ClientesDbContract.Cliente.FONE)));
            cliente.setEmail(c.getString(c.getColumnIndex(ClientesDbContract.Cliente.EMAIL)));
            cliente.setFigura(c.getString(c.getColumnIndex(ClientesDbContract.Cliente.FOTO)));
            clientes.add(cliente);
        }
        c.close();
        return clientes;
    }
}
