package br.usjt.ads.desmob.clienteads18.model;

import android.provider.BaseColumns;

public class ClientesDbContract {
    public ClientesDbContract(){}

    public static abstract class Cliente implements BaseColumns{
        public static final String TABLE_NAME = "cliente";
        public static final String ID = "id";
        public static final String NOME = "nome";
        public static final String FONE = "fone";
        public static final String EMAIL = "email";
        public static final String FOTO = "foto";

    }
}
