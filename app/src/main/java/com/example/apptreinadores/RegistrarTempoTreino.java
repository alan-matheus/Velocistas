package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apptreinadores.databinding.ActivityRegistrarResultadoBinding;
import com.example.apptreinadores.databinding.ActivityRegistrarTempoBinding;

public class RegistrarTempoTreino extends AppCompatActivity {

    ActivityRegistrarTempoBinding binding;
    private DBHelperCavalo dbHelper;
    Cavalo cavalo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarTempoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");

        dbHelper = new DBHelperCavalo(this);


        binding.btnRegistraTempoTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarTempo();
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarTempoTreino.this, ListarTempos.class);
                intent.putExtra("cavalo", cavalo);
                startActivity(intent);
                finish();
            }
        });

    }

    private void adicionarTempo(){

        int distancia = (int) getDistanciaFromInput();
        double tempo = getTempoFromInput();
        String jockey = binding.inputJockeyTempo.getText().toString();
        String terreno = binding.inputTerrenoTempo.getText().toString();
        String dataResultado = binding.inputDataTempo.getText().toString();

        if(distancia == 0) {
            binding.inputDistanciaTempo.setError("Informe a distancia.");
            return;
        } else if(tempo == 0.0){
            binding.inputTempoTreino.setError("Informe o tempo.");
            return;
        } else if(dataResultado.isEmpty() || jockey.isEmpty() || terreno.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos, por favor.", Toast.LENGTH_SHORT).show();
            return;
        }

        TempoTreino tempoTreino = new TempoTreino(null, tempo, distancia, terreno, dataResultado, jockey);
        dbHelper.addTempoTreino(tempoTreino, cavalo);
        Toast.makeText(this, "Ração adicionado com sucesso!", Toast.LENGTH_LONG).show();
        limparCampos();


    }

    private double getDistanciaFromInput() {
        String distanciaText = binding.inputDistanciaTempo.getText().toString().trim();
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
        String tempoText = binding.inputTempoTreino.getText().toString().trim();
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
        binding.inputDistanciaTempo.setText("");
        binding.inputDataTempo.setText("");
        binding.inputTempoTreino.setText("");
        binding.inputTerrenoTempo.setText("");
        binding.inputJockeyTempo.setText("");
    }
}