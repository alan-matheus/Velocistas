package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apptreinadores.databinding.ActivityRegistraPagamentoBinding;
import com.example.apptreinadores.databinding.ActivityRegistrarRemediosBinding;

public class RegistrarPagamento extends AppCompatActivity {

    ActivityRegistraPagamentoBinding binding;
    private DBHelperCavalo dbHelper;
    Cavalo cavalo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistraPagamentoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");

        dbHelper = new DBHelperCavalo(this);


        binding.btnRegistraPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarPagamento();
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarPagamento.this, ListarPagamentos.class);
                intent.putExtra("cavalo", cavalo);
                startActivity(intent);
                finish();
            }
        });

    }

    private void adicionarPagamento(){
        String nome = binding.inputTipoPagamento.getText().toString();
        double valor = getValorFromInput();
        String dataPagamento = binding.inputDataPagamento.getText().toString();

        if(valor == 0.0){
            binding.inputValorPagamento.setError("Informe o valor do remédio.");
            return;
        } else if(nome.isEmpty() || dataPagamento.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos, por favor.", Toast.LENGTH_SHORT).show();
            return;
        }

        Pagamento pagamento = new Pagamento(null, nome, valor, dataPagamento);
        dbHelper.addPagamento(pagamento, cavalo);
        Toast.makeText(this, "Remédio adicionado com sucesso!", Toast.LENGTH_LONG).show();
        limparCampos();

    }

    private double getValorFromInput() {
        String valorText = binding.inputValorPagamento.getText().toString().trim();
        if (valorText.isEmpty()) {
            return 0.0; // Valor padrão quando o campo está vazio
        } else {
            try {
                return Double.parseDouble(valorText);
            } catch (NumberFormatException e) {
                return 0.0; // Valor padrão quando o valor não pode ser convertido corretamente
            }
        }
    }

    private void limparCampos(){
        binding.inputTipoPagamento.setText("");
        binding.inputValorPagamento.setText("");
        binding.inputDataPagamento.setText("");

    }

}