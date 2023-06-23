package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.apptreinadores.databinding.ActivityListarRemediosBinding;

import java.util.ArrayList;
import java.util.List;

public class ListarRemedios extends AppCompatActivity implements AdapterRemedio.OnItemClickListener{
    ActivityListarRemediosBinding binding;
    private Cavalo cavalo;
    private Integer cavaloId;
    private AdapterRemedio adapterRemedio;
    private List<Remedio> remedioList;
    private DBHelperCavalo dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListarRemediosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperCavalo(this);

        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);

        binding.txtNome.setText(cavalo.getNome());

        remedioList = new ArrayList<>();
        adapterRemedio = new AdapterRemedio(remedioList, this);
        binding.rvRemedios.setLayoutManager(new LinearLayoutManager(this));
        binding.rvRemedios.setAdapter(adapterRemedio);

        carregaDados();

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListarRemedios.this, Menu.class);
                i.putExtra("cavalo", cavalo);
                i.putExtra("cavaloId", cavaloId);
                startActivity(i);
                finish();
            }
        });

        binding.btnAddRemedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarRemedios.this, RegistrarRemedios.class);
                intent.putExtra("cavalo", cavalo);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onItemClick(Remedio remedio) {

    }

    private void carregaDados(){
        remedioList.clear();
        remedioList.addAll(dbHelper.getRemediosByCavaloId(cavaloId));
        adapterRemedio.notifyDataSetChanged();
    }
}