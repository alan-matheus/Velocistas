package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.apptreinadores.databinding.ActivityMenuBinding;

public class Menu extends AppCompatActivity {
    private Cavalo cavalo;
    ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");




    }
}