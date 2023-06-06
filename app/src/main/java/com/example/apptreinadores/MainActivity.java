package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.apptreinadores.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

        binding.rv.setLayoutManager(new ConstraintLayout(this));

        binding.recyclerView.setAdapter(horseAdapter);

        loadHorseData();
    }

    private void loadHorseData() {
        horseList.clear();
        horseList.addAll(databaseHelper.getAllHorses());
        horseAdapter.notifyDataSetChanged();
    }

        binding.btnRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrarRacoes.class);
                startActivity(intent);
                finish();
            }
        });

    }
}