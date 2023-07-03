package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.apptreinadores.databinding.ActivityEditarCavaloBinding;
import com.google.android.material.internal.EdgeToEdgeUtils;

import java.util.ArrayList;
import java.util.List;

public class EditarCavalo extends AppCompatActivity {

    ActivityEditarCavaloBinding binding;
    private DBHelperCavalo dbHelper;
    private Cavalo cavalo;
    private List<String> racaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditarCavaloBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperCavalo(this);
        racaList = getOpcoesRaca();

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarCavalo.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Obter o objeto Cavalo passado pela MainActivity
        Intent intent = getIntent();
        if (intent.hasExtra("cavalo")) {
            cavalo = (Cavalo) intent.getSerializableExtra("cavalo");
            preencherCampos();
        } else {
            // Se não houver objeto Cavalo, encerrar a atividade
            finish();
        }

        // Configurar o Spinner de raça
        ArrayAdapter<String> racaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, racaList);
        racaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(racaAdapter);

        binding.btnAtualizarCavalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizarCavalo();
            }
        });
    }

    private void preencherCampos() {
        binding.inputNomeCavalo.setText(cavalo.getNome());
        binding.inputChegada.setText(cavalo.getDataChegada());

        // Selecionar a raça correta no Spinner
        String raca = cavalo.getRaca();
        int racaIndex = racaList.indexOf(raca);
        if (racaIndex != -1) {
            binding.spinner.setSelection(racaIndex);
        }
    }

    private void atualizarCavalo() {
        String nome = binding.inputNomeCavalo.getText().toString();
        String raca = binding.spinner.getSelectedItem().toString();
        String chegada = binding.inputChegada.getText().toString();
        String regex = "\\d{2}/\\d{2}/\\d{4}";

        if (nome.isEmpty() || raca.equals("Selecione a raça:") || chegada.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos, por favor.", Toast.LENGTH_SHORT).show();
            return;
        } else if (!chegada.matches(regex)) {
            binding.inputChegada.setError("Informe a data no formato: dd/mm/aaaa");
            return;
        }

        // Atualizar os dados do cavalo
        cavalo.setNome(nome);
        cavalo.setRaca(raca);
        cavalo.setDataChegada(chegada);

        // Atualizar o cavalo na base de dados
        dbHelper.editarCavalo(cavalo);

        Toast.makeText(this, "Cavalo atualizado com sucesso!", Toast.LENGTH_SHORT).show();

        // Encerrar a atividade e retornar para a MainActivity
        finish();
    }

    private List<String> getOpcoesRaca(){
        List<String> opcoesRaca = new ArrayList<>();
        opcoesRaca.add("Selecione a raça:");
        opcoesRaca.add("Puro Sangue");
        opcoesRaca.add("Quarto de Milha");
        opcoesRaca.add("Mestiço");

        return opcoesRaca;
    }
}