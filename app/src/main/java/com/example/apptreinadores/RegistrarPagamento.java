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

import com.example.apptreinadores.databinding.ActivityRegistraPagamentoBinding;
import com.example.apptreinadores.databinding.ActivityRegistrarRemediosBinding;

import java.util.ArrayList;
import java.util.List;

public class RegistrarPagamento extends AppCompatActivity {

    ActivityRegistraPagamentoBinding binding;
    private DBHelperCavalo dbHelper;
    private Cavalo cavalo;
    private Integer cavaloId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistraPagamentoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);

        dbHelper = new DBHelperCavalo(this);

        Spinner spinner = binding.spinnerPag;

        ArrayAdapter<String> pagamentoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getOpcoesPagamento());
        pagamentoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(pagamentoAdapter);

        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                ((TextView) view).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

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
                intent.putExtra("cavaloId", cavaloId);
                startActivity(intent);
                finish();
            }
        });

    }

    private List<String> getOpcoesPagamento(){
        List<String> opcoesPagamento = new ArrayList<>();
        opcoesPagamento.add("Selecione o tipo:");
        opcoesPagamento.add("Mensal");
        opcoesPagamento.add("Semanal");

        return opcoesPagamento;
    }

    private void adicionarPagamento(){
        String nome = binding.spinnerPag.getSelectedItem().toString();
        double valor = getValorFromInput();
        String dataPagamento = binding.inputDataPagamento.getText().toString();
        String regex = "\\d{2}/\\d{2}/\\d{4}";

        if(valor == 0.0){
            binding.inputValorPagamento.setError("Informe o valor do remédio.");
            return;
        }
        else if(!dataPagamento.matches(regex)){
            binding.inputDataPagamento.setError("Informe a data: dd/mm/aaaa");
            return;
        }
        else if(nome.equals("Selecione o tipo:")){
            Toast.makeText(this, "Preencha todos os campos, por favor.", Toast.LENGTH_SHORT).show();
            return;
        }

        Pagamento pagamento = new Pagamento(null, nome, valor, dataPagamento);
        dbHelper.addPagamento(pagamento, cavalo);
        Toast.makeText(this, "Pagamento adicionado com sucesso!", Toast.LENGTH_LONG).show();
        limparCampos();
        Intent intent = new Intent(RegistrarPagamento.this, ListarPagamentos.class);
        intent.putExtra("cavalo", cavalo);
        intent.putExtra("cavaloId", cavaloId);
        startActivity(intent);
        finish();

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
        binding.spinnerPag.setSelection(0);
        binding.inputValorPagamento.setText("");
        binding.inputDataPagamento.setText("");

    }

}