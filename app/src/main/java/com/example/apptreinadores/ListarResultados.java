package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apptreinadores.databinding.ActivityListarRacoesBinding;
import com.example.apptreinadores.databinding.ActivityListarResultadosBinding;

import java.util.ArrayList;
import java.util.List;

public class ListarResultados extends AppCompatActivity implements AdapterResultado.OnItemClickListener{
    ActivityListarResultadosBinding binding;
    private Cavalo cavalo;
    private Integer cavaloId;
    private AdapterResultado adapterResultado;
    private List<Resultado> resultadosList;
    private DBHelperCavalo dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListarResultadosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperCavalo(this);
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);

        binding.txtNome.setText(cavalo.getNome());

        resultadosList = new ArrayList<>();
        adapterResultado = new AdapterResultado(resultadosList, this);
        binding.rvResultados.setLayoutManager(new LinearLayoutManager(this));
        binding.rvResultados.setAdapter(adapterResultado);

        carregaDados();

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListarResultados.this, Menu.class);
                i.putExtra("cavalo", cavalo);
                i.putExtra("cavaloId", cavaloId);
                startActivity(i);
                finish();
            }
        });

        binding.btnAddResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarResultados.this, RegistrarResultado.class);
                intent.putExtra("cavalo", cavalo);
                intent.putExtra("cavaloId", cavaloId);
                startActivity(intent);
                finish();
            }
        });

    }

    private void carregaDados(){
        resultadosList.clear();
        resultadosList.addAll(dbHelper.getResultadosByCavaloId(cavaloId));
        adapterResultado.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Resultado resultado) {

    }
}