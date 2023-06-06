package com.example.apptreinadores;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PrivateKey;

public class CavaloDBHelper extends SQLiteOpenHelper {

    private static final String NOME_BASE = "treinadores";
    private static final int VERSAO_BASE = 1;
    private static final String TABELA_CAVALO = "cavalo";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_RACA = "raca";
    private static final String COLUNA_CHEGADA = "dataChegada";

    public CavaloDBHelper(Context context){
        super(context, NOME_BASE, null, VERSAO_BASE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABELA_CAVALO + " (" +
                COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME + " TEXT, " +
                COLUNA_RACA + " TEXT, " +
                COLUNA_CHEGADA + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addCavalo(Cavalo c){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, c.getNome());
        values.put(COLUNA_RACA, c.getRaca());
        values.put(COLUNA_CHEGADA, c.getDataChegada());

        db.insert(TABELA_CAVALO, null, values);
        db.close();
    }


}
