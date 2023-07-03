package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apptreinadores.databinding.ActivityEditarRemedioBinding;


public class EditarRemedio extends AppCompatActivity {

    ActivityEditarRemedioBinding binding;
    private DBHelperCavalo dbHelper;
    private Cavalo cavalo;
    private Integer cavaloId;
    private Remedio remedio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditarRemedioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);
        dbHelper = new DBHelperCavalo(this);

        // Obter o objeto Remedio passado pela MainActivity
        Intent intent = getIntent();
        if (intent.hasExtra("remedio")) {
            remedio = (Remedio) intent.getSerializableExtra("remedio");
            preencherCampos();
        } else {
            // Se não houver objeto Cavalo, encerrar a atividade
            finish();
        }

        binding.btnRegistraRemedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizarRemedio();
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarRemedio.this, ListarRemedios.class);
                intent.putExtra("cavalo", cavalo);
                intent.putExtra("cavaloId", cavaloId);
                startActivity(intent);
                finish();
            }
        });

    }

    private void preencherCampos() {
        binding.inputNomeRemedio.setText(remedio.getNome());
        binding.inputChegadaRemedio.setText(remedio.getDataChegada());
        binding.inputQuantidadeRemedio.setText(Double.toString(remedio.getQuantidade()));
        binding.inputValorRemedio.setText(Double.toString(remedio.getValor()));
        binding.inputVencimentoRemedio.setText(remedio.getDataVencimento());

    }

    private void atualizarRemedio(){
        String nome = binding.inputNomeRemedio.getText().toString();
        double quantidade = getQuantidadeFromInput();
        double valor = getValorFromInput();
        String dataVencimento = binding.inputVencimentoRemedio.getText().toString();
        String dataChegada = binding.inputChegadaRemedio.getText().toString();
        String regex = "\\d{2}/\\d{2}/\\d{4}";

        if(quantidade == 0.0) {
            binding.inputQuantidadeRemedio.setError("Informe a quantidade.");
            return;
        } else if(valor == 0.0){
            binding.inputValorRemedio.setError("Informe o valor do remédio.");
            return;
        } else if (!dataChegada.matches(regex)){
            binding.inputChegadaRemedio.setError("Informe a data: dd/mm/aaaa");
            return;
        } else if(!dataVencimento.matches(regex)){
            binding.inputVencimentoRemedio.setError("Informe a data: dd/mm/aaaa");
            return;
        }

        else if(nome.isEmpty() || dataVencimento.isEmpty() || dataChegada.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos, por favor.", Toast.LENGTH_SHORT).show();
            return;
        }

        remedio.setNome(nome);
        remedio.setDataChegada(dataChegada);
        remedio.setDataVencimento(dataVencimento);
        remedio.setQuantidade(quantidade);
        remedio.setValor(valor);

        dbHelper.editarRemedio(remedio);
        Toast.makeText(this, "Remédio editado com sucesso!", Toast.LENGTH_LONG).show();
        limparCampos();
        Intent intent = new Intent(EditarRemedio.this, ListarRemedios.class);
        intent.putExtra("cavalo", cavalo);
        intent.putExtra("cavaloId", cavaloId);
        startActivity(intent);
        finish();
    }

    private double getQuantidadeFromInput() {
        String quantidadeText = binding.inputQuantidadeRemedio.getText().toString().trim();
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
        String quantidadeText = binding.inputValorRemedio.getText().toString().trim();
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
        binding.inputNomeRemedio.setText("");
        binding.inputValorRemedio.setText("");
        binding.inputQuantidadeRemedio.setText("");
        binding.inputVencimentoRemedio.setText("");
        binding.inputChegadaRemedio.setText("");
    }
}