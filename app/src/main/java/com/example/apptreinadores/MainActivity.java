package com.example.apptreinadores;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.apptreinadores.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //private static final int REGISTRAR_CAVALO_REQUEST_CODE = 1;

    ActivityMainBinding binding;
    private CavaloAdapter ca;
    private List<Cavalo> cavaloList;
    private CavaloDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper = new CavaloDBHelper(this);
        cavaloList = new ArrayList<>();
        ca = new CavaloAdapter(cavaloList);

        binding.rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        binding.rv.setAdapter(ca);

        carregaDados();

        binding.btnRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrarCavalo.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void carregaDados(){
        cavaloList.clear();
        cavaloList.addAll(dbHelper.getAllCavalos());
        ca.notifyDataSetChanged();
    }




}