package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apptreinadores.databinding.ActivityRegistrarRemediosBinding;

public class RegistrarRemedios extends AppCompatActivity {

    ActivityRegistrarRemediosBinding binding;
    private DBHelperCavalo dbHelper;
    private Cavalo cavalo;
    private Integer cavaloId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarRemediosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);
        dbHelper = new DBHelperCavalo(this);


        binding.btnRegistraRemedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarRemedio();
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarRemedios.this, ListarRemedios.class);
                intent.putExtra("cavalo", cavalo);
                intent.putExtra("cavaloId", cavaloId);
                startActivity(intent);
                finish();
            }
        });

    }

    private void adicionarRemedio(){
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

        Remedio remedio = new Remedio(null, nome, quantidade, valor, dataVencimento, dataChegada);
        dbHelper.addRemedio(remedio, cavalo);
        Toast.makeText(this, "Remédio adicionado com sucesso!", Toast.LENGTH_LONG).show();
        limparCampos();
        Intent intent = new Intent(RegistrarRemedios.this, ListarRemedios.class);
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