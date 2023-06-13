package com.example.apptreinadores;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.apptreinadores.DBHelperCavalo;

public class DBHelperRemedio extends SQLiteOpenHelper {
    private DBHelperCavalo dbHelperCavalo;
    private static final String NOME_BASE = "treinadores";
    private static final int VERSAO_BASE = 1;
    private static final String NOME_TABELA = "remedio";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_QUANTIDADE = "quantidade";
    private static final String COLUNA_VALOR = "valor";
    private static final String COLUNA_VALIDADE = "validade";
    private static final String COLUNA_CHEGADA = "data_chegada";
    private static final String COLUNA_ID_CAVALO = "id_cavalo";

    public DBHelperRemedio(Context context, DBHelperCavalo dbHelperCavalo){
        super(context, NOME_BASE, null, VERSAO_BASE);
        this.dbHelperCavalo = dbHelperCavalo;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTableQuery = "CREATE TABLE " + NOME_TABELA + " (" +
                COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME + " TEXT, " +
                COLUNA_QUANTIDADE + " INTEGER, " +
                COLUNA_VALOR + " REAL, " +
                COLUNA_VALIDADE + " TEXT, " +
                COLUNA_CHEGADA + " TEXT, " +
                COLUNA_ID_CAVALO + " INTEGER, " +
                "FOREIGN KEY (" + COLUNA_ID_CAVALO + ") REFERENCES " +
                DBHelperCavalo.NOME_TABELA + "(" + dbHelperCavalo.COLUNA_ID + ")" +
                ")";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + NOME_TABELA;
        sqLiteDatabase.execSQL(dropTableQuery);
        onCreate(sqLiteDatabase);
    }




}
