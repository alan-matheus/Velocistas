package com.example.apptreinadores;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import java.util.ArrayList;

import java.util.List;

public class CavaloDBHelper extends SQLiteOpenHelper {

    private static final String NOME_BASE = "treinadores";
    private static final int VERSAO_BASE = 1;
    private static final String NOME_TABELA = "cavalo";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_RACA = "raca";
    private static final String COLUNA_CHEGADA = "dataChegada";

    public CavaloDBHelper(Context context){
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
        db.close();
    }

    public List<Cavalo> getAllCavalos() {

        String sql = "SELECT nome, raca, dataChegada FROM cavalo;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        List<Cavalo> cavaloList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {

                @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME));
                @SuppressLint("Range") String raca = cursor.getString(cursor.getColumnIndex(COLUNA_RACA));
                @SuppressLint("Range") String dataChegada = cursor.getString(cursor.getColumnIndex(COLUNA_CHEGADA));

                Cavalo cavalo = new Cavalo(nome, raca, dataChegada);
                cavaloList.add(cavalo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return cavaloList;

    }



}


