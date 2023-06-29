package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apptreinadores.databinding.ActivityListarTemposBinding;

import java.util.ArrayList;
import java.util.List;

public class ListarTempos extends AppCompatActivity implements AdapterTempo.OnItemClickListener{
    ActivityListarTemposBinding binding;
    private Cavalo cavalo;
    private Integer cavaloId;
    private AdapterTempo adapterTempo;
    private List<TempoTreino> tempoList;
    private DBHelperCavalo dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListarTemposBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperCavalo(this);
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);

        binding.txtNome.setText(cavalo.getNome());

        tempoList = new ArrayList<>();
        adapterTempo = new AdapterTempo(tempoList, this);
        binding.rvTempos.setLayoutManager(new LinearLayoutManager(this));
        binding.rvTempos.setAdapter(adapterTempo);

        carregaDados();

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListarTempos.this, Menu.class);
                i.putExtra("cavalo", cavalo);
                i.putExtra("cavaloId", cavaloId);
                startActivity(i);
                finish();
            }
        });

        binding.btnAddTempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarTempos.this, RegistrarTempoTreino.class);
                intent.putExtra("cavalo", cavalo);
                intent.putExtra("cavaloId", cavaloId);
                startActivity(intent);
                finish();
            }
        });

    }

    private void carregaDados(){
        tempoList.clear();
        tempoList.addAll(dbHelper.getTemposByCavaloId(cavaloId));
        adapterTempo.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(TempoTreino tempoTreino) {

    }
}