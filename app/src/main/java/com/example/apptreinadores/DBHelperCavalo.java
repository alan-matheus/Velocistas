package com.example.apptreinadores;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import androidx.core.view.ViewPropertyAnimatorListener;

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
    private static final String COLUNA_QUANTIDADE_ATUAL_REMEDIO = "qtd_atual";
    private static final String COLUNA_VALOR_REMEDIO = "valor";
    private static final String COLUNA_VALIDADE_REMEDIO = "validade";
    private static final String COLUNA_CHEGADA_REMEDIO = "data_chegada";
    private static final String COLUNA_ID_CAVALO_REMEDIO = "id_cavalo";

    //tabela ração
    private static final String NOME_TABELA_RACAO = "racao";
    private static final String COLUNA_ID_RACAO = "id";
    private static final String COLUNA_NOME_RACAO = "nome";
    private static final String COLUNA_QUANTIDADE_RACAO = "quantidade";
    private static final String COLUNA_QUANTIDADE_ATUAL_RACAO = "qtd_atual";
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
                COLUNA_QUANTIDADE_ATUAL_REMEDIO + " REAL, " +
                COLUNA_VALOR_REMEDIO + " REAL, " +
                COLUNA_VALIDADE_REMEDIO + " TEXT, " +
                COLUNA_CHEGADA_REMEDIO + " TEXT, " +
                COLUNA_ID_CAVALO_REMEDIO + " INTEGER, " +
                "FOREIGN KEY (" + COLUNA_ID_CAVALO_REMEDIO + ") REFERENCES " +
                 NOME_TABELA + "(" + COLUNA_ID + ") ON DELETE CASCADE" +
                ")";

        db.execSQL(createRemedioTable);

        Log.d("racao", "onCreate: tabela racao criada");
        String createRacaoTable = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA_RACAO + " (" +
                COLUNA_ID_RACAO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME_RACAO + " TEXT, " +
                COLUNA_QUANTIDADE_RACAO + " REAL, " +
                COLUNA_QUANTIDADE_ATUAL_RACAO + " REAL, " +
                COLUNA_VALOR_RACAO+ " REAL, " +
                COLUNA_CHEGADA_RACAO+ " TEXT, " +
                COLUNA_ID_CAVALO_RACAO + " INTEGER, " +
                "FOREIGN KEY (" + COLUNA_ID_CAVALO_RACAO+ ") REFERENCES " +
                NOME_TABELA + "(" + COLUNA_ID + ") ON DELETE CASCADE" +
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
                NOME_TABELA + "(" + COLUNA_ID + ") ON DELETE CASCADE" +
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
                NOME_TABELA + "(" + COLUNA_ID + ") ON DELETE CASCADE" +
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
                NOME_TABELA + "(" + COLUNA_ID + ") ON DELETE CASCADE" +
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

    public void excluirCavalo(Cavalo cavalo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOME_TABELA, COLUNA_ID + " = ?", new String[]{String.valueOf(cavalo.getId())});
        db.close();
    }

    public void editarCavalo(Cavalo cavalo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, cavalo.getNome());
        values.put(COLUNA_RACA, cavalo.getRaca());
        values.put(COLUNA_CHEGADA, cavalo.getDataChegada());
        db.update(NOME_TABELA, values, COLUNA_ID + " = ?", new String[]{String.valueOf(cavalo.getId())});
        db.close();
    }

    public void addRemedio(Remedio r, Cavalo c){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, r.getNome());
        values.put(COLUNA_QUANTIDADE_REMEDIO, r.getQuantidade());
        values.put(COLUNA_QUANTIDADE_ATUAL_REMEDIO, r.getQtdAtual());
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

    public void excluirRemedio(Remedio remedio) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOME_TABELA_REMEDIO, COLUNA_ID_REMEDIO + " = ?", new String[]{String.valueOf(remedio.getId())});
        db.close();
    }

    public void editarRemedio(Remedio r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, r.getNome());
        values.put(COLUNA_QUANTIDADE_REMEDIO, r.getQuantidade());
        values.put(COLUNA_VALIDADE_REMEDIO, r.getDataVencimento());
        values.put(COLUNA_CHEGADA_REMEDIO, r.getDataChegada());
        values.put(COLUNA_VALOR_REMEDIO, r.getValor());

        db.update(NOME_TABELA_REMEDIO, values, COLUNA_ID_REMEDIO + " = ?", new String[]{String.valueOf(r.getId())});
        db.close();
    }

    public void atualizarRemedio(Remedio r){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_QUANTIDADE_ATUAL_REMEDIO, r.getQtdAtual());
        db.update(NOME_TABELA_REMEDIO, values, "id = ?", new String[]{String.valueOf(r.getId())});

        db.close();
    }

    public void addRacao(Racao r, Cavalo c){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, r.getNome());
        values.put(COLUNA_QUANTIDADE_RACAO, r.getQuantidade());
        values.put(COLUNA_CHEGADA_RACAO, r.getDataChegada());
        values.put(COLUNA_VALOR_RACAO, r.getValor());
        values.put(COLUNA_QUANTIDADE_ATUAL_RACAO, r.getQtdAtual() );
        values.put(COLUNA_ID_CAVALO_RACAO, c.getId());

        db.insert("racao", null, values);
        db.close();

    }

    @SuppressLint("Range")
    public List<Racao> getRacoesByCavaloId(Integer cavaloId) {
        Log.d("DBHelperRacao", "getRacoesByCavaloId called with cavaloId: " + cavaloId);

        List<Racao> racaoList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + NOME_TABELA_RACAO +
                " WHERE " + COLUNA_ID_CAVALO_RACAO + " = " + cavaloId;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUNA_ID));
                String nome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME_RACAO));
                double quantidade = cursor.getDouble(cursor.getColumnIndex(COLUNA_QUANTIDADE_RACAO));
                //double qtdAtual = cursor.getDouble(cursor.getColumnIndex(COLUNA_QUANTIDADE_ATUAL_RACAO));
                double valor = cursor.getDouble(cursor.getColumnIndex(COLUNA_VALOR_RACAO));
                String dataChegada = cursor.getString(cursor.getColumnIndex(COLUNA_CHEGADA_RACAO));

                Racao racao = new Racao(id, nome, quantidade, valor, dataChegada);
                racaoList.add(racao);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return racaoList;
    }

    public void excluirRacao(Racao racao) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOME_TABELA_RACAO, COLUNA_ID_RACAO + " = ?", new String[]{String.valueOf(racao.getId())});
        db.close();
    }

    public void editarRacao(Racao r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, r.getNome());
        values.put(COLUNA_QUANTIDADE_RACAO, r.getQuantidade());
        values.put(COLUNA_CHEGADA_RACAO, r.getDataChegada());
        values.put(COLUNA_VALOR_RACAO, r.getValor());

        db.update(NOME_TABELA_RACAO, values, COLUNA_ID_RACAO + " = ?", new String[]{String.valueOf(r.getId())});
        db.close();
    }

    public void atualizarRacao(Racao r){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_QUANTIDADE_ATUAL_RACAO, r.getQtdAtual());
        db.update(NOME_TABELA_RACAO, values, "id = ?", new String[]{String.valueOf(r.getId())});

        db.close();
    }

    public void addPagamento(Pagamento p, Cavalo c){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_PAGAMENTO, p.getNome());
        values.put(COLUNA_VALOR_PAGAMENTO, p.getValor());
        values.put(COLUNA_DATA_PAGAMENTO, p.getData());
        values.put(COLUNA_ID_CAVALO_RACAO, c.getId());

        db.insert("pagamento", null, values);
        db.close();

    }

    @SuppressLint("Range")
    public List<Pagamento> getPagamentosByCavaloId(Integer cavaloId) {
        Log.d("DBHelperPagamento", "getPagamentosByCavaloId called with cavaloId: " + cavaloId);

        List<Pagamento> pagamentosList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + NOME_TABELA_PAGAMENTO +
                " WHERE " + COLUNA_ID_CAVALO_PAGAMENTO + " = " + cavaloId;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUNA_ID_PAGAMENTO));
                String nome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME_PAGAMENTO));
                double valor = cursor.getDouble(cursor.getColumnIndex(COLUNA_VALOR_PAGAMENTO));
                String dataPagamento = cursor.getString(cursor.getColumnIndex(COLUNA_DATA_PAGAMENTO));

                Pagamento pagamento = new Pagamento(id, nome, valor, dataPagamento);
                pagamentosList.add(pagamento);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return pagamentosList;
    }

    public void excluirPagamento(Pagamento pagamento) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOME_TABELA_PAGAMENTO, COLUNA_ID_PAGAMENTO + " = ?", new String[]{String.valueOf(pagamento.getId())});
        db.close();
    }

    public void editarPagamento(Pagamento p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_PAGAMENTO, p.getNome());
        values.put(COLUNA_VALOR_PAGAMENTO, p.getValor());
        values.put(COLUNA_DATA_PAGAMENTO, p.getData());

        db.update(NOME_TABELA_PAGAMENTO, values, COLUNA_ID_PAGAMENTO + " = ?", new String[]{String.valueOf(p.getId())});
        db.close();
    }


    public void addResultado(Resultado r, Cavalo c){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_RESULTADO, r.getNome());
        values.put(COLUNA_TERRENO_RESULTADO, r.getTerreno());
        values.put(COLUNA_TEMPO_RESULTADO, r.getTempo());
        values.put(COLUNA_DISTANCIA_RESULTADO, r.getDistancia());
        values.put(COLUNA_DATA_RESULTADO, r.getData());
        values.put(COLUNA_JOCKEY_RESULTADO, r.getJockey());
        values.put(COLUNA_ID_CAVALO_RESULTADO, c.getId());

        db.insert("resultado", null, values);
        db.close();

    }

    @SuppressLint("Range")
    public List<Resultado> getResultadosByCavaloId (Integer cavaloId) {
        Log.d("DBHelperResultados", "getRemediosByCavaloId called with cavaloId: " + cavaloId);

        List<Resultado> resultadosList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + NOME_TABELA_RESULTADO +
                " WHERE " + COLUNA_ID_CAVALO_RESULTADO + " = " + cavaloId;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUNA_ID_RESULTADO));
                String nome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME_RESULTADO));
                String terreno = cursor.getString(cursor.getColumnIndex(COLUNA_TERRENO_RESULTADO));
                double tempo = cursor.getDouble(cursor.getColumnIndex(COLUNA_TEMPO_RESULTADO));
                Integer distancia = cursor.getInt(cursor.getColumnIndex(COLUNA_DISTANCIA_RESULTADO));
                String dataResultado = cursor.getString(cursor.getColumnIndex(COLUNA_DATA_RESULTADO));
                String jockeyResultado = cursor.getString(cursor.getColumnIndex(COLUNA_JOCKEY_RESULTADO));

                Resultado resultado = new Resultado(id, nome, tempo, distancia, terreno, dataResultado, jockeyResultado);
                resultadosList.add(resultado);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return resultadosList;
    }

    public void excluirResultado(Resultado resultado) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOME_TABELA_RESULTADO, COLUNA_ID_RESULTADO + " = ?", new String[]{String.valueOf(resultado.getId())});
        db.close();
    }

    public void editarResultado(Resultado r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_RESULTADO, r.getNome());
        values.put(COLUNA_TERRENO_RESULTADO, r.getTerreno());
        values.put(COLUNA_TEMPO_RESULTADO, r.getTempo());
        values.put(COLUNA_DISTANCIA_RESULTADO, r.getDistancia());
        values.put(COLUNA_DATA_RESULTADO, r.getData());
        values.put(COLUNA_JOCKEY_RESULTADO, r.getJockey());

        db.update(NOME_TABELA_RESULTADO, values, COLUNA_ID_RESULTADO + " = ?", new String[]{String.valueOf(r.getId())});
        db.close();
    }

    public void addTempoTreino(TempoTreino t, Cavalo c){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_TEMPO_TEMPO, t.getTempo());
        values.put(COLUNA_DISTANCIA_TEMPO, t.getDistancia());
        values.put(COLUNA_TERRENO_TEMPO, t.getTerreno());
        values.put(COLUNA_DATA_TEMPO, t.getData());
        values.put(COLUNA_JOCKEY_TEMPO, t.getJockey());
        values.put(COLUNA_ID_CAVALO_TEMPO, c.getId());

        db.insert("tempo_treino", null, values);
        db.close();

    }

    @SuppressLint("Range")
    public List<TempoTreino> getTemposByCavaloId (Integer cavaloId) {
        Log.d("DBHelperTempos", "getTemposByCavaloId called with cavaloId: " + cavaloId);

        List<TempoTreino> temposList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + NOME_TABELA_TEMPO +
                " WHERE " + COLUNA_ID_CAVALO_TEMPO + " = " + cavaloId;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUNA_ID_TEMPO));
                String terreno = cursor.getString(cursor.getColumnIndex(COLUNA_TERRENO_TEMPO));
                double tempo = cursor.getDouble(cursor.getColumnIndex(COLUNA_TEMPO_TEMPO));
                Integer distancia = cursor.getInt(cursor.getColumnIndex(COLUNA_DISTANCIA_TEMPO));
                String dataTempo = cursor.getString(cursor.getColumnIndex(COLUNA_DATA_TEMPO));
                String jockeyTempo = cursor.getString(cursor.getColumnIndex(COLUNA_JOCKEY_TEMPO));

                TempoTreino tempoTreino = new TempoTreino(id, tempo, distancia, terreno, dataTempo, jockeyTempo);
                temposList.add(tempoTreino);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return temposList;
    }

    public void excluirTempo(TempoTreino tempoTreino) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOME_TABELA_TEMPO, COLUNA_ID_TEMPO + " = ?", new String[]{String.valueOf(tempoTreino.getId())});
        db.close();
    }

    public void editarTempo(TempoTreino t) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_TEMPO_TEMPO, t.getTempo());
        values.put(COLUNA_DISTANCIA_TEMPO, t.getDistancia());
        values.put(COLUNA_TERRENO_TEMPO, t.getTerreno());
        values.put(COLUNA_DATA_TEMPO, t.getData());
        values.put(COLUNA_JOCKEY_TEMPO, t.getJockey());

        db.update(NOME_TABELA_TEMPO, values, COLUNA_ID_TEMPO + " = ?", new String[]{String.valueOf(t.getId())});
        db.close();
    }


}


