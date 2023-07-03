package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptreinadores.databinding.ActivityRegistrarTempoBinding;

import java.util.ArrayList;
import java.util.List;

public class EditarTempo extends AppCompatActivity {

    ActivityRegistrarTempoBinding binding;
    private DBHelperCavalo dbHelper;
    private Cavalo cavalo;
    private Integer cavaloId;
    private TempoTreino tempoTreino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarTempoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);
        dbHelper = new DBHelperCavalo(this);

        Intent intent = getIntent();
        if (intent.hasExtra("tempo")) {
            tempoTreino = (TempoTreino) intent.getSerializableExtra("tempo");
            preencherCampos();
        } else {
            // Se não houver objeto Tempo, encerrar a atividade
            finish();
        }
        Spinner spinner2 = binding.spinnerTerrenoTreino;

        ArrayAdapter<String> terrenoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getOpcoesTerreno());
        terrenoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(terrenoAdapter);

        spinner2.setSelection(0);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                ((TextView) view).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        binding.btnRegistraTempoTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizarTempo();
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarTempo.this, ListarTempos.class);
                intent.putExtra("cavalo", cavalo);
                intent.putExtra("cavaloId", cavaloId);
                startActivity(intent);
                finish();
            }
        });

    }

    private void preencherCampos(){
        binding.inputDataTempo.setText(tempoTreino.getData());
        binding.inputJockeyTempo.setText(tempoTreino.getJockey());
        binding.inputDistanciaTempo.setText(Integer.toString(tempoTreino.getDistancia()));
        binding.inputTempoTreino.setText(Double.toString(tempoTreino.getTempo()));
    }

    private List<String> getOpcoesTerreno(){
        List<String> opcoesTerreno = new ArrayList<>();
        opcoesTerreno.add("Selecione o terreno:");
        opcoesTerreno.add("Areia");
        opcoesTerreno.add("Grama");

        return opcoesTerreno;
    }

    private void atualizarTempo(){

        Integer distancia =  getDistanciaFromInput();
        double tempo = getTempoFromInput();
        String jockey = binding.inputJockeyTempo.getText().toString();
        String terreno = binding.spinnerTerrenoTreino.getSelectedItem().toString();
        String dataResultado = binding.inputDataTempo.getText().toString();
        String regex = "\\d{2}/\\d{2}/\\d{4}";

        if(distancia == 0) {
            binding.inputDistanciaTempo.setError("Informe a distancia.");
            return;
        } else if(tempo == 0.0){
            binding.inputTempoTreino.setError("Informe o tempo.");
            return;
        } else if(!dataResultado.matches(regex)){
            binding.inputDataTempo.setError("Informe a data: dd/mm/aaaa");
            return;
        } else if(jockey.isEmpty() || terreno.equals("Selecione o terreno:")){
            Toast.makeText(this, "Preencha todos os campos, por favor.", Toast.LENGTH_SHORT).show();
            return;
        }

        tempoTreino.setTempo(tempo);
        tempoTreino.setJockey(jockey);
        tempoTreino.setDistancia(distancia);
        tempoTreino.setTerreno(terreno);
        tempoTreino.setData(dataResultado);

        dbHelper.editarTempo(tempoTreino);

        Toast.makeText(this, "Tempo editado com sucesso!", Toast.LENGTH_LONG).show();
        limparCampos();
        Intent intent = new Intent(EditarTempo.this, ListarTempos.class);
        intent.putExtra("cavalo", cavalo);
        intent.putExtra("cavaloId", cavaloId);
        startActivity(intent);
        finish();

    }

    private Integer getDistanciaFromInput() {
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
        binding.spinnerTerrenoTreino.setSelection(0);
        binding.inputJockeyTempo.setText("");
    }
}