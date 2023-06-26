package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apptreinadores.databinding.ActivityListarRacoesBinding;

import java.util.ArrayList;
import java.util.List;

public class ListarRacoes extends AppCompatActivity implements AdapterRacao.OnItemClickListener {
    ActivityListarRacoesBinding binding;
    Cavalo cavalo;
    private Integer cavaloId;
    private AdapterRacao adapterRacao;
    private List<Racao> racaoList;
    private DBHelperCavalo dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListarRacoesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperCavalo(this);
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);

        binding.txtNome.setText(cavalo.getNome());

        racaoList = new ArrayList<>();
        adapterRacao = new AdapterRacao(racaoList, this);
        binding.rvRacoes.setLayoutManager(new LinearLayoutManager(this));
        binding.rvRacoes.setAdapter(adapterRacao);

        carregaDados();

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListarRacoes.this, Menu.class);
                i.putExtra("cavalo", cavalo);
                i.putExtra("cavaloId", cavaloId);
                startActivity(i);
                finish();
            }
        });

        binding.btnAddRacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarRacoes.this, RegistrarRacoes.class);
                intent.putExtra("cavalo", cavalo);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onItemClick(Racao racao) {

    }

    private void carregaDados(){
        racaoList.clear();
        racaoList.addAll(dbHelper.getRacoesByCavaloId(cavaloId));
        adapterRacao.notifyDataSetChanged();
    }
}