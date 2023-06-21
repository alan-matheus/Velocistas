package com.example.apptreinadores;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;

import java.util.List;

public class DBHelperCavalo extends SQLiteOpenHelper {

    private static final String NOME_BASE = "treinadores";
    private static final int VERSAO_BASE = 1;


    //tabela cavalo
    public static final String NOME_TABELA = "cavalo";
    public static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_RACA = "raca";
    private static final String COLUNA_CHEGADA = "dataChegada";


    //tabela remedios

    private static final String COLUNA_ID_REMEDIO = "id";
    private static final String COLUNA_NOME_REMEDIO = "nome";
    private static final String COLUNA_QUANTIDADE = "quantidade";
    private static final String COLUNA_VALOR = "valor";
    private static final String COLUNA_VALIDADE = "validade";
    private static final String COLUNA_CHEGADA_REMEDIO = "data_chegada";
    private static final String COLUNA_ID_CAVALO = "id_cavalo";

    public DBHelperCavalo(Context context){
        super(context, NOME_BASE, null, VERSAO_BASE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + NOME_TABELA + " (" +
                COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME + " TEXT, " +
                COLUNA_RACA + " TEXT, " +
                COLUNA_CHEGADA + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + NOME_TABELA;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public void addCavalo(Cavalo c){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, c.getNome());
        values.put(COLUNA_RACA, c.getRaca());
        values.put(COLUNA_CHEGADA, c.getDataChegada());

        db.insert(NOME_TABELA, null, values);
        //db.close();
    }

    public void addRemedio(Remedio r, Cavalo c){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, r.getNome());
        values.put(COLUNA_QUANTIDADE, r.getQuantidade());
        values.put(COLUNA_VALIDADE, r.getDataVencimento());
        values.put(COLUNA_CHEGADA, r.getDataChegada());
        values.put(COLUNA_VALOR, r.getValor());
        values.put(COLUNA_ID_CAVALO, c.getId());

        db.insert("remedio", null, values);
        //db.close();

    }

    public List<Cavalo> getAllCavalos() {

        String sql = "SELECT id, nome, raca, dataChegada FROM cavalo;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        List<Cavalo> cavaloList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex(COLUNA_ID));
                @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME));
                @SuppressLint("Range") String raca = cursor.getString(cursor.getColumnIndex(COLUNA_RACA));
                @SuppressLint("Range") String dataChegada = cursor.getString(cursor.getColumnIndex(COLUNA_CHEGADA));

                Cavalo cavalo = new Cavalo(id, nome, raca, dataChegada);
                cavaloList.add(cavalo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return cavaloList;

    }

    public List<Remedio> getRemediosByCavaloId(Integer cavaloId) {

        Log.d("DBHelperRemedio", "getRemediosByCavaloId called with cavaloId: " + cavaloId);

        List<Remedio> remedioList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] colunas = {COLUNA_ID, COLUNA_NOME, COLUNA_QUANTIDADE, COLUNA_VALOR, COLUNA_VALIDADE, COLUNA_CHEGADA};

        String selecao = COLUNA_ID_CAVALO + " = ?";
        String[] selecaoArgs = {String.valueOf(cavaloId)};

        Cursor cursor = db.query(NOME_TABELA, colunas, selecao, selecaoArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUNA_ID));
                @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME));
                @SuppressLint("Range") double quantidade = cursor.getDouble(cursor.getColumnIndex(COLUNA_QUANTIDADE));
                @SuppressLint("Range") double valor = cursor.getDouble(cursor.getColumnIndex(COLUNA_VALOR));
                @SuppressLint("Range") String dataVencimento = cursor.getString(cursor.getColumnIndex(COLUNA_VALIDADE));
                @SuppressLint("Range") String dataChegada = cursor.getString(cursor.getColumnIndex(COLUNA_CHEGADA));

                Remedio remedio = new Remedio(id, nome, quantidade, valor, dataVencimento, dataChegada);

                remedioList.add(remedio);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return remedioList;
    }



}


