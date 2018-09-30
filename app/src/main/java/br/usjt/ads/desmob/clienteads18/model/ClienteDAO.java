package br.usjt.ads.desmob.clienteads18.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClienteDAO {
    private static Cliente[] clientes;

    private ClienteDAO() {
    }

    public static Cliente[] getClientes() {
        if (clientes == null) {
            clientes = new Cliente[21];
            clientes[0] = new Cliente(1, "Carlos Drummond de Andrade", "11 999941342", "cda@usjt.br");
            clientes[1] = new Cliente(2, "Manuel Bandeira", "11 89353987", "mb@usjt.br");
            clientes[2] = new Cliente(3, "Olavo Bilac", "23 987256345", "ob@usjt.br");
            clientes[3] = new Cliente(4, "Vinícius de Moraes", "11 987345616", "vm@usjt.br");
            clientes[4] = new Cliente(5, "Cecília Meireles", "11 923541247", "cm@usjt.br");
            clientes[5] = new Cliente(6, "Castro Alves", "11 981233423", "ca@usjt.br");
            clientes[6] = new Cliente(7, "Gonçalves Dias", "12 987652345", "gd@usjt.br");
            clientes[7] = new Cliente(8, "Ferreira Gullar", "11 987234543", "fg@usjt.br");
            clientes[8] = new Cliente(9, "Machado de Assis", "11 999981234", "ma@usjt.br");
            clientes[9] = new Cliente(10, "Mário de Andrade", "11 989987365", "man@usjt.br");
            clientes[10] = new Cliente(11, "Cora Coralina", "12 989763423", "cc@usjt.br");
            clientes[11] = new Cliente(12, "Manoel de Barros", "14 768365276", "mbar@usjt.br");
            clientes[12] = new Cliente(13, "João Cabral de Melo Neto", "11 987873123", "jcmn@usjt.br");
            clientes[13] = new Cliente(14, "Casimiro de Abreu", "81 987698765", "cab@usjt.br");
            clientes[14] = new Cliente(15, "Paulo Leminski", "62 9762323453", "pl@usjt.br");
            clientes[15] = new Cliente(16, "Álvares de Azevedo", "11 32319878", "aa@usjt.br");
            clientes[16] = new Cliente(17, "Guilherme de Almeida", "11 998787343", "ga@usjt.br");
            clientes[17] = new Cliente(18, "Alphonsus de Guimarães", "11 966565777", "ag@usjt.br");
            clientes[18] = new Cliente(19, "Mário Quintana", "51 999932778", "mq@usjt.br");
            clientes[19] = new Cliente(20, "Gregório de Matos", "12 32138888", "gm@usjt.br");
            clientes[20] = new Cliente(21, "Augusto dos Anjos", "11 994524141", "aanj@usjt.br");
        }
        return clientes;
    }

    public static ArrayList<Cliente> getClientes(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String arquivo = response.body().string();

        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            JSONArray lista = new JSONArray(arquivo);
            for(int i = 0; i < lista.length(); i++){
                JSONObject item = (JSONObject) lista.get(i);
                Cliente cliente = new Cliente();
                cliente.setId(item.getInt("id"));
                cliente.setNome(item.getString("nome"));
                cliente.setFone(item.getString("fone"));
                cliente.setEmail(item.getString("email"));
                clientes.add(cliente);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return clientes;
    }
}
