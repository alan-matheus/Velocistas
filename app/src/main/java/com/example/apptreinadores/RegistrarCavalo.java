package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apptreinadores.databinding.ActivityRegistrarCavaloBinding;

public class RegistrarCavalo extends AppCompatActivity {

    ActivityRegistrarCavaloBinding binding;
    private CavaloDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarCavaloBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new CavaloDBHelper(this);

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarCavalo.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnRegistraCavalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarCavalo();
            }
        });
    }

    private void salvarCavalo(){
        String nome = binding.inputNomeCavalo.getText().toString();
        String raca = binding.inputRaca.getText().toString();
        String chegada = binding.inputChegada.getText().toString();

        Cavalo c = new Cavalo(nome, raca, chegada);
        dbHelper.addCavalo(c);

        limparCampos();
    }

    private void limparCampos(){
        binding.inputNomeCavalo.setText("");
        binding.inputRaca.setText("");
        binding.inputChegada.setText("");
    }
}