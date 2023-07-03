package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apptreinadores.databinding.ActivityRegistrarRacoesBinding;

public class EditarRacao extends AppCompatActivity {

    ActivityRegistrarRacoesBinding binding;
    private DBHelperCavalo dbHelper;
    private Cavalo cavalo;
    private Integer cavaloId;
    private Racao racao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarRacoesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);
        dbHelper = new DBHelperCavalo(this);

        // Obter o objeto Remedio passado pela MainActivity
        Intent intent = getIntent();
        if (intent.hasExtra("racao")) {
            racao = (Racao) intent.getSerializableExtra("racao");
            preencherCampos();
        } else {
            // Se não houver objeto Cavalo, encerrar a atividade
            finish();
        }



        binding.btnRegistraRacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizarRacao();
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarRacao.this, ListarRacoes.class);
                intent.putExtra("cavalo", cavalo);
                intent.putExtra("cavaloId", cavaloId);
                startActivity(intent);
                finish();
            }
        });

    }

    private void preencherCampos(){
        binding.inputNomeRacao.setText(racao.getNome());
        binding.inputValorRacao.setText(Double.toString(racao.getValor()));
        binding.inputChegadaRacao.setText(racao.getDataChegada());
        binding.inputQuantidadeRacao.setText(Double.toString(racao.getQuantidade()));
    }

    private void atualizarRacao(){
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
            return;
        }
        else if(valor == 0.0){
            binding.inputValorRacao.setError("Informe o valor do remédio.");
            return;
        } else if(nome.isEmpty() || dataChegada.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos, por favor.", Toast.LENGTH_SHORT).show();
            return;
        }

        racao.setNome(nome);
        racao.setValor(valor);
        racao.setDataChegada(dataChegada);
        racao.setQuantidade(quantidade);

        dbHelper.editarRacao(racao);

        Toast.makeText(this, "Ração editada com sucesso!", Toast.LENGTH_LONG).show();
        limparCampos();
        Intent intent = new Intent(EditarRacao.this, ListarRacoes.class);
        intent.putExtra("cavalo", cavalo);
        intent.putExtra("cavaloId", cavaloId);
        startActivity(intent);
        finish();

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