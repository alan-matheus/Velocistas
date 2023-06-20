package com.example.apptreinadores;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.apptreinadores.DBHelperCavalo;

import java.util.ArrayList;
import java.util.List;

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
                COLUNA_QUANTIDADE + " REAL, " +
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
        db.close();

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
                int id = cursor.getInt(cursor.getColumnIndex(COLUNA_ID));
                String nome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME));
                double quantidade = cursor.getDouble(cursor.getColumnIndex(COLUNA_QUANTIDADE));
                double valor = cursor.getDouble(cursor.getColumnIndex(COLUNA_VALOR));
                String dataVencimento = cursor.getString(cursor.getColumnIndex(COLUNA_VALIDADE));
                String dataChegada = cursor.getString(cursor.getColumnIndex(COLUNA_CHEGADA));

                Remedio remedio = new Remedio(nome, quantidade, valor, dataVencimento, dataChegada);

                remedioList.add(remedio);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return remedioList;
    }









}
