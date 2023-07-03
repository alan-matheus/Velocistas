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

import com.example.apptreinadores.databinding.ActivityRegistrarResultadoBinding;

import java.util.ArrayList;
import java.util.List;

public class EditarResultado extends AppCompatActivity {

    ActivityRegistrarResultadoBinding binding;
    private DBHelperCavalo dbHelper;
    private Cavalo cavalo;
    private Integer cavaloId;
    private Resultado resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarResultadoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);

        dbHelper = new DBHelperCavalo(this);

        Intent intent = getIntent();
        if (intent.hasExtra("resultado")) {
            resultado = (Resultado) intent.getSerializableExtra("resultado");
            preencherCampos();
        } else {
            // Se não houver objeto Cavalo, encerrar a atividade
            finish();
        }

        Spinner spinner1 = binding.spinnerResultado;

        ArrayAdapter<String> resultadoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getOpcoesResultado());
        resultadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(resultadoAdapter);

        spinner1.setSelection(0);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                ((TextView) view).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Spinner spinner2 = binding.spinnerTerreno;

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

        binding.btnRegistraResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizarResultado();
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarResultado.this, ListarResultados.class);
                intent.putExtra("cavalo", cavalo);
                intent.putExtra("cavaloId", cavaloId);
                startActivity(intent);
                finish();
            }
        });

    }

    private void preencherCampos(){
        binding.inputData.setText(resultado.getData());
        binding.inputJockey.setText(resultado.getJockey());
        binding.inputDistancia.setText(Integer.toString(resultado.getDistancia()));
        binding.inputTempo.setText(Double.toString(resultado.getTempo()));
    }

    private List<String> getOpcoesResultado(){
        List<String> opcoesResultado = new ArrayList<>();
        opcoesResultado.add("Selecione o resultado:");
        opcoesResultado.add("Vitória");
        opcoesResultado.add("Empate");
        opcoesResultado.add("Derrota");

        return opcoesResultado;
    }

    private List<String> getOpcoesTerreno(){
        List<String> opcoesTerreno = new ArrayList<>();
        opcoesTerreno.add("Selecione o terreno:");
        opcoesTerreno.add("Areia");
        opcoesTerreno.add("Grama");

        return opcoesTerreno;
    }

    private void atualizarResultado(){
        String nomeResultado = binding.spinnerResultado.getSelectedItem().toString();
        Integer distancia = getDistanciaFromInput();
        double tempo = getTempoFromInput();
        String jockey = binding.inputJockey.getText().toString();
        String terreno = binding.spinnerTerreno.getSelectedItem().toString();
        String dataResultado = binding.inputData.getText().toString();
        String regex = "\\d{2}/\\d{2}/\\d{4}";

        if(distancia == 0) {
            binding.inputDistancia.setError("Informe a distancia.");
            return;
        } else if(tempo == 0.0){
            binding.inputTempo.setError("Informe o tempo.");
            return;
        } else if(!dataResultado.matches(regex)){
            binding.inputData.setError("Informe a data: dd/mm/aaaa");
            return;
        } else if(nomeResultado.equals("Selecione o resultado:") || jockey.isEmpty() || terreno.equals("Selecione o terreno:")){
            Toast.makeText(this, "Preencha todos os campos, por favor.", Toast.LENGTH_SHORT).show();
            return;
        }

        resultado.setNome(nomeResultado);
        resultado.setTempo(tempo);
        resultado.setDistancia(distancia);
        resultado.setTerreno(terreno);
        resultado.setData(dataResultado);
        resultado.setJockey(jockey);

        dbHelper.editarResultado(resultado);

        Toast.makeText(this, "Resultado editado com sucesso!", Toast.LENGTH_LONG).show();
        limparCampos();
        Intent intent = new Intent(EditarResultado.this, ListarResultados.class);
        intent.putExtra("cavalo", cavalo);
        intent.putExtra("cavaloId", cavaloId);
        startActivity(intent);
        finish();

    }

    private Integer getDistanciaFromInput() {
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
        binding.spinnerResultado.setSelection(0);
        binding.inputDistancia.setText("");
        binding.inputData.setText("");
        binding.inputTempo.setText("");
        binding.spinnerTerreno.setSelection(0);
        binding.inputJockey.setText("");
    }
}