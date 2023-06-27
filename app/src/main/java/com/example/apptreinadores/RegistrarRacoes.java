package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apptreinadores.databinding.ActivityRegistrarRacoesBinding;
import com.example.apptreinadores.databinding.ActivityRegistrarRemediosBinding;

public class RegistrarRacoes extends AppCompatActivity {

    ActivityRegistrarRacoesBinding binding;
    private DBHelperCavalo dbHelper;
    Cavalo cavalo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarRacoesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");

        dbHelper = new DBHelperCavalo(this);


        binding.btnRegistraRacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarRacao();
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarRacoes.this, ListarRacoes.class);
                intent.putExtra("cavalo", cavalo);
                startActivity(intent);
                finish();
            }
        });

    }

    private void adicionarRacao(){
        String nome = binding.inputNomeRacao.getText().toString();
        double quantidade = getQuantidadeFromInput();
        double valor = getValorFromInput();
        String dataChegada = binding.inputChegadaRacao.getText().toString();
        String regex = "\\d{2}/\\d{2}/\\d{4}";

        if(quantidade == 0.0) {
            binding.inputQuantidadeRacao.setError("Informe a quantidade.");
            return;

        }
        else if(!dataChegada.matches(regex)){
            binding.inputChegadaRacao.setError("Informe a data: dd/mm/aaaa");
        }
        else if(valor == 0.0){
            binding.inputValorRacao.setError("Informe o valor do remédio.");
            return;
        } else if(nome.isEmpty() || dataChegada.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos, por favor.", Toast.LENGTH_SHORT).show();
            return;
        }

        Racao racao = new Racao(null, nome, quantidade, valor, dataChegada);
        dbHelper.addRacao(racao, cavalo);
        Toast.makeText(this, "Ração adicionado com sucesso!", Toast.LENGTH_LONG).show();
        limparCampos();


    }

    private double getQuantidadeFromInput() {
        String quantidadeText = binding.inputQuantidadeRacao.getText().toString().trim();
        if (quantidadeText.isEmpty()) {
            return 0.0; // Valor padrão quando o campo está vazio
        } else {
            try {
                return Double.parseDouble(quantidadeText);
            } catch (NumberFormatException e) {
                return 0.0; // Valor padrão quando o valor não pode ser convertido corretamente
            }
        }
    }

    private double getValorFromInput() {
        String quantidadeText = binding.inputValorRacao.getText().toString().trim();
        if (quantidadeText.isEmpty()) {
            return 0.0; // Valor padrão quando o campo está vazio
        } else {
            try {
                return Double.parseDouble(quantidadeText);
            } catch (NumberFormatException e) {
                return 0.0; // Valor padrão quando o valor não pode ser convertido corretamente
            }
        }
    }

    private void limparCampos(){
        binding.inputNomeRacao.setText("");
        binding.inputValorRacao.setText("");
        binding.inputQuantidadeRacao.setText("");
        binding.inputChegadaRacao.setText("");
    }
}