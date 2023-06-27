package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.apptreinadores.databinding.ActivityRegistrarResultadoBinding;

public class RegistrarResultado extends AppCompatActivity {

    ActivityRegistrarResultadoBinding binding;
    private DBHelperCavalo dbHelper;
    Cavalo cavalo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarResultadoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");

        dbHelper = new DBHelperCavalo(this);


        binding.btnRegistraResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarResultado();
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarResultado.this, ListarResultados.class);
                intent.putExtra("cavalo", cavalo);
                startActivity(intent);
                finish();
            }
        });

    }

    private void adicionarResultado(){
        String nomeResultado = binding.inputResultado.getText().toString();
        int distancia = (int) getDistanciaFromInput();
        double tempo = getTempoFromInput();
        String jockey = binding.inputJockey.getText().toString();
        String terreno = binding.inputTerreno.getText().toString();
        String dataResultado = binding.inputData.getText().toString();

        if(distancia == 0) {
            binding.inputDistancia.setError("Informe a distancia.");
            return;
        } else if(tempo == 0.0){
            binding.inputTempo.setError("Informe o tempo.");
            return;
        } else if(nomeResultado.isEmpty() || dataResultado.isEmpty() || jockey.isEmpty() || terreno.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos, por favor.", Toast.LENGTH_SHORT).show();
            return;
        }

        Resultado resultado = new Resultado(null, nomeResultado, tempo, distancia, terreno, dataResultado, jockey);
        dbHelper.addResultado(resultado, cavalo);
        Toast.makeText(this, "Ração adicionado com sucesso!", Toast.LENGTH_LONG).show();
        limparCampos();


    }

    private double getDistanciaFromInput() {
        String distanciaText = binding.inputDistancia.getText().toString().trim();
        if (distanciaText.isEmpty()) {
            return 0; // Valor padrão quando o campo está vazio
        } else {
            try {
                return Integer.parseInt(distanciaText);
            } catch (NumberFormatException e) {
                return 0; // Valor padrão quando o valor não pode ser convertido corretamente
            }
        }
    }

    private double getTempoFromInput() {
        String tempoText = binding.inputTempo.getText().toString().trim();
        if (tempoText.isEmpty()) {
            return 0.0; // Valor padrão quando o campo está vazio
        } else {
            try {
                return Double.parseDouble(tempoText);
            } catch (NumberFormatException e) {
                return 0.0; // Valor padrão quando o valor não pode ser convertido corretamente
            }
        }
    }

    private void limparCampos(){
        binding.inputResultado.setText("");
        binding.inputDistancia.setText("");
        binding.inputData.setText("");
        binding.inputTempo.setText("");
        binding.inputTerreno.setText("");
        binding.inputJockey.setText("");
    }
}