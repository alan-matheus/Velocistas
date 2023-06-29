package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apptreinadores.databinding.ActivityListarPagamentosBinding;
import com.example.apptreinadores.databinding.ActivityListarRacoesBinding;

import java.util.ArrayList;
import java.util.List;

public class ListarPagamentos extends AppCompatActivity implements AdapterPagamento.OnItemClickListener {

    ActivityListarPagamentosBinding binding;
    private Cavalo cavalo;
    private Integer cavaloId;
    private AdapterPagamento adapterPagamento;
    private List<Pagamento> pagamentosList;
    private DBHelperCavalo dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListarPagamentosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperCavalo(this);
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);

        binding.txtNome.setText(cavalo.getNome());

        pagamentosList = new ArrayList<>();
        adapterPagamento = new AdapterPagamento(pagamentosList, this);
        binding.rvPagamentos.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPagamentos.setAdapter(adapterPagamento);

        carregaDados();

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListarPagamentos.this, Menu.class);
                i.putExtra("cavalo", cavalo);
                i.putExtra("cavaloId", cavaloId);
                startActivity(i);
                finish();
            }
        });

        binding.btnAddPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarPagamentos.this, RegistrarPagamento.class);
                intent.putExtra("cavalo", cavalo);
                intent.putExtra("cavaloId", cavaloId);
                startActivity(intent);
                finish();
            }
        });


    }

    private void carregaDados(){
        pagamentosList.clear();
        pagamentosList.addAll(dbHelper.getPagamentosByCavaloId(cavaloId));
        adapterPagamento.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Pagamento pagamento) {

    }



}