package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.apptreinadores.databinding.ActivityRegistrarCavaloBinding;

public class RegistrarCavalo extends AppCompatActivity {

    ActivityRegistrarCavaloBinding binding;
    private DBHelperCavalo dbHelper;

    private TextWatcher capitalizeTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            if (!text.isEmpty()) {
                String capitalizedText = text.substring(0, 1).toUpperCase() + text.substring(1);
                if (!capitalizedText.equals(text)) {
                    binding.inputNomeCavalo.setText(capitalizedText);
                    binding.inputNomeCavalo.setSelection(capitalizedText.length());
                 }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarCavaloBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperCavalo(this);
        binding.inputNomeCavalo.addTextChangedListener(capitalizeTextWatcher);


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

        if(nome.isEmpty() || raca.isEmpty() || chegada.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos, por favor.", Toast.LENGTH_SHORT).show();
            return;
        }

        Cavalo c = new Cavalo(nome, raca, chegada);
        dbHelper.addCavalo(c);
        Toast.makeText(this, "Cavalo Adicionado com sucesso!", Toast.LENGTH_LONG).show();

        limparCampos();
    }

    private void limparCampos(){
        binding.inputNomeCavalo.setText("");
        binding.inputRaca.setText("");
        binding.inputChegada.setText("");
    }
}