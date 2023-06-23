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
    private static final String NOME_TABELA_REMEDIO = "remedio";
    private static final String COLUNA_ID_REMEDIO = "id";
    private static final String COLUNA_NOME_REMEDIO = "nome";
    private static final String COLUNA_QUANTIDADE_REMEDIO = "quantidade";
    private static final String COLUNA_VALOR_REMEDIO = "valor";
    private static final String COLUNA_VALIDADE_REMEDIO = "validade";
    private static final String COLUNA_CHEGADA_REMEDIO = "data_chegada";
    private static final String COLUNA_ID_CAVALO_REMEDIO = "id_cavalo";

    //tabela ração
    private static final String NOME_TABELA_RACAO = "racao";
    private static final String COLUNA_ID_RACAO = "id";
    private static final String COLUNA_NOME_RACAO = "nome";
    private static final String COLUNA_QUANTIDADE_RACAO = "quantidade";
    private static final String COLUNA_VALOR_RACAO = "valor";
    private static final String COLUNA_CHEGADA_RACAO = "data_chegada";
    private static final String COLUNA_ID_CAVALO_RACAO = "id_cavalo";

    //tabela pagamento
    private static final String NOME_TABELA_PAGAMENTO = "pagamento";
    private static final String COLUNA_ID_PAGAMENTO = "id";
    private static final String COLUNA_NOME_PAGAMENTO = "nome";
    private static final String COLUNA_VALOR_PAGAMENTO = "valor";
    private static final String COLUNA_DATA_PAGAMENTO = "data_chegada";
    private static final String COLUNA_ID_CAVALO_PAGAMENTO = "id_cavalo";

    //tabela resultado
    private static final String NOME_TABELA_RESULTADO = "resultado";
    private static final String COLUNA_ID_RESULTADO = "id";
    private static final String COLUNA_NOME_RESULTADO = "nome";
    private static final String COLUNA_TERRENO_RESULTADO = "terreno";
    private static final String COLUNA_TEMPO_RESULTADO = "tempo";
    private static final String COLUNA_DISTANCIA_RESULTADO = "distancia";
    private static final String COLUNA_DATA_RESULTADO = "data_resultado";
    private static final String COLUNA_JOCKEY_RESULTADO = "jockey_resultado";
    private static final String COLUNA_ID_CAVALO_RESULTADO = "id_cavalo";

    //tabela tempo treino
    private static final String NOME_TABELA_TEMPO = "tempo_treino";
    private static final String COLUNA_ID_TEMPO = "id";
    private static final String COLUNA_TERRENO_TEMPO = "terreno";
    private static final String COLUNA_TEMPO_TEMPO = "tempo";
    private static final String COLUNA_DISTANCIA_TEMPO = "distancia";
    private static final String COLUNA_DATA_TEMPO = "data_tempo";
    private static final String COLUNA_JOCKEY_TEMPO = "jockey_tempo";
    private static final String COLUNA_ID_CAVALO_TEMPO = "id_cavalo";


    public DBHelperCavalo(Context context){
        super(context, NOME_BASE, null, VERSAO_BASE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("cavalo", "onCreate: tabela cavalo criada");
        String createCavaloTable = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA + " (" +
                COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME + " TEXT, " +
                COLUNA_RACA + " TEXT, " +
                COLUNA_CHEGADA + " TEXT)";

        db.execSQL(createCavaloTable);

        Log.d("remedio", "onCreate: tabela remedio criada ");
        String createRemedioTable = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA_REMEDIO + " (" +
                COLUNA_ID_REMEDIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME_REMEDIO + " TEXT, " +
                COLUNA_QUANTIDADE_REMEDIO + " REAL, " +
                COLUNA_VALOR_REMEDIO + " REAL, " +
                COLUNA_VALIDADE_REMEDIO + " TEXT, " +
                COLUNA_CHEGADA_REMEDIO + " TEXT, " +
                COLUNA_ID_CAVALO_REMEDIO + " INTEGER, " +
                "FOREIGN KEY (" + COLUNA_ID_CAVALO_REMEDIO + ") REFERENCES " +
                 NOME_TABELA + "(" + COLUNA_ID + ")" +
                ")";

        db.execSQL(createRemedioTable);

        Log.d("racao", "onCreate: tabela racao criada");
        String createRacaoTable = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA_RACAO + " (" +
                COLUNA_ID_RACAO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME_RACAO + " TEXT, " +
                COLUNA_QUANTIDADE_RACAO + " REAL, " +
                COLUNA_VALOR_RACAO+ " REAL, " +
                COLUNA_CHEGADA_RACAO+ " TEXT, " +
                COLUNA_ID_CAVALO_RACAO + " INTEGER, " +
                "FOREIGN KEY (" + COLUNA_ID_CAVALO_RACAO+ ") REFERENCES " +
                NOME_TABELA + "(" + COLUNA_ID + ")" +
                ")";

        db.execSQL(createRacaoTable);

        Log.d("pagamento", "onCreate: tabela pagamento criada ");
        String createPagamentoTable = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA_PAGAMENTO + " (" +
                COLUNA_ID_PAGAMENTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME_PAGAMENTO + " TEXT, " +
                COLUNA_VALOR_PAGAMENTO+ " REAL, " +
                COLUNA_DATA_PAGAMENTO+ " TEXT, " +
                COLUNA_ID_CAVALO_PAGAMENTO + " INTEGER, " +
                "FOREIGN KEY (" + COLUNA_ID_CAVALO_PAGAMENTO+ ") REFERENCES " +
                NOME_TABELA + "(" + COLUNA_ID + ")" +
                ")";

        db.execSQL(createPagamentoTable);

        Log.d("resultado", "onCreate: tabela resultado criada ");
        String createResultadoTable = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA_RESULTADO + " (" +
                COLUNA_ID_RESULTADO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME_RESULTADO + " TEXT, " +
                COLUNA_TEMPO_RESULTADO+ " REAL, " +
                COLUNA_DISTANCIA_RESULTADO+ " INTEGER, " +
                COLUNA_TERRENO_RESULTADO+ " TEXT, " +
                COLUNA_DATA_RESULTADO+ " TEXT, " +
                COLUNA_JOCKEY_RESULTADO+ " TEXT, " +
                COLUNA_ID_CAVALO_RESULTADO + " INTEGER, " +
                "FOREIGN KEY (" + COLUNA_ID_CAVALO_RESULTADO+ ") REFERENCES " +
                NOME_TABELA + "(" + COLUNA_ID + ")" +
                ")";

        db.execSQL(createResultadoTable);

        Log.d("tempo_treino", "onCreate: tabela tempo treino criada");
        String createTempoTable = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA_TEMPO + " (" +
                COLUNA_ID_TEMPO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_TEMPO_TEMPO+ " REAL, " +
                COLUNA_DISTANCIA_TEMPO+ " INTEGER, " +
                COLUNA_TERRENO_TEMPO+ " TEXT, " +
                COLUNA_DATA_TEMPO+ " TEXT, " +
                COLUNA_JOCKEY_TEMPO+ " TEXT, " +
                COLUNA_ID_CAVALO_TEMPO + " INTEGER, " +
                "FOREIGN KEY (" + COLUNA_ID_CAVALO_TEMPO+ ") REFERENCES " +
                NOME_TABELA + "(" + COLUNA_ID + ")" +
                ")";

        db.execSQL(createTempoTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void addCavalo(Cavalo c){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, c.getNome());
        values.put(COLUNA_RACA, c.getRaca());
        values.put(COLUNA_CHEGADA, c.getDataChegada());

        db.insert("cavalo", null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<Cavalo> getAllCavalos() {

        String sql = "SELECT id, nome, raca, dataChegada FROM cavalo;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        List<Cavalo> cavaloList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                 Integer id = cursor.getInt(cursor.getColumnIndex(COLUNA_ID));
                 String nome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME));
                 String raca = cursor.getString(cursor.getColumnIndex(COLUNA_RACA));
                 String dataChegada = cursor.getString(cursor.getColumnIndex(COLUNA_CHEGADA));

                Cavalo cavalo = new Cavalo(id, nome, raca, dataChegada);
                cavaloList.add(cavalo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return cavaloList;

    }

    public void addRemedio(Remedio r, Cavalo c){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, r.getNome());
        values.put(COLUNA_QUANTIDADE_REMEDIO, r.getQuantidade());
        values.put(COLUNA_VALIDADE_REMEDIO, r.getDataVencimento());
        values.put(COLUNA_CHEGADA_REMEDIO, r.getDataChegada());
        values.put(COLUNA_VALOR_REMEDIO, r.getValor());
        values.put(COLUNA_ID_CAVALO_REMEDIO, c.getId());

        db.insert("remedio", null, values);
        db.close();

    }

    @SuppressLint("Range")
    public List<Remedio> getRemediosByCavaloId(Integer cavaloId) {
        Log.d("DBHelperRemedio", "getRemediosByCavaloId called with cavaloId: " + cavaloId);

        List<Remedio> remedioList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + NOME_TABELA_REMEDIO +
                " WHERE " + COLUNA_ID_CAVALO_REMEDIO + " = " + cavaloId;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                 int id = cursor.getInt(cursor.getColumnIndex(COLUNA_ID));
                 String nome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME_REMEDIO));
                 double quantidade = cursor.getDouble(cursor.getColumnIndex(COLUNA_QUANTIDADE_REMEDIO));
                 double valor = cursor.getDouble(cursor.getColumnIndex(COLUNA_VALOR_REMEDIO));
                 String dataVencimento = cursor.getString(cursor.getColumnIndex(COLUNA_VALIDADE_REMEDIO));
                 String dataChegada = cursor.getString(cursor.getColumnIndex(COLUNA_CHEGADA_REMEDIO));

                Remedio remedio = new Remedio(id, nome, quantidade, valor, dataVencimento, dataChegada);
                remedioList.add(remedio);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return remedioList;
    }



}


