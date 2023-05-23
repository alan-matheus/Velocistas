package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apptreinadores.databinding.ActivityRegistrarCavaloBinding;

public class RegistrarCavalo extends AppCompatActivity {

    ActivityRegistrarCavaloBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarCavaloBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarCavalo.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}