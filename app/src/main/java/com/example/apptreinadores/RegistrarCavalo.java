package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptreinadores.databinding.ActivityRegistrarCavaloBinding;

import java.util.ArrayList;
import java.util.List;

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

        Spinner spinner = binding.spinner;

        ArrayAdapter<String> racaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getOpcoesRaca());
        racaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(racaAdapter);

        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                ((TextView) view).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


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

    private List<String> getOpcoesRaca(){
        List<String> opcoesRaca = new ArrayList<>();
        opcoesRaca.add("Selecione a raça:");
        opcoesRaca.add("Puro Sangue");
        opcoesRaca.add("Quarto de Milha");
        opcoesRaca.add("Mestiço");

        return opcoesRaca;
    }

    private void salvarCavalo(){


        String nome = binding.inputNomeCavalo.getText().toString();
        String raca = binding.spinner.getSelectedItem().toString();
        String chegada = binding.inputChegada.getText().toString();
        String regex = "\\d{2}/\\d{2}/\\d{4}";

        if(nome.isEmpty() || raca.isEmpty() || raca.equals("Selecione a raça:")){
            Toast.makeText(this, "Preencha todos os campos, por favor.", Toast.LENGTH_SHORT).show();
            return;
        } else if(!chegada.matches(regex)){
            binding.inputChegada.setError("Informe a data: dd/mm/aaaa");
            return;
        }

        Cavalo c = new Cavalo(null, nome, raca, chegada);
        dbHelper.addCavalo(c);
        Toast.makeText(this, "Cavalo Adicionado com sucesso!", Toast.LENGTH_LONG).show();

        limparCampos();

        Intent intent = new Intent(RegistrarCavalo.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void limparCampos(){
        binding.inputNomeCavalo.setText("");
        binding.inputChegada.setText("");
    }
}