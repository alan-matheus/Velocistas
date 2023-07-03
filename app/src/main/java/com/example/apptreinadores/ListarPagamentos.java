package com.example.apptreinadores;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.apptreinadores.databinding.ActivityListarPagamentosBinding;
import com.example.apptreinadores.databinding.ActivityListarRacoesBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListarPagamentos extends AppCompatActivity implements AdapterPagamento.OnItemClickListener {

    ActivityListarPagamentosBinding binding;
    private Cavalo cavalo;
    private Integer cavaloId;
    private AdapterPagamento adapterPagamento;
    private List<Pagamento> pagamentosList;
    private DBHelperCavalo dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListarPagamentosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperCavalo(this);
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);

        binding.txtNome.setText(cavalo.getNome());

        pagamentosList = new ArrayList<>();
        adapterPagamento = new AdapterPagamento(pagamentosList, this);
        binding.rvPagamentos.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPagamentos.setAdapter(adapterPagamento);

        carregaDados();
        configurarRecyclerView();

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListarPagamentos.this, Menu.class);
                i.putExtra("cavalo", cavalo);
                i.putExtra("cavaloId", cavaloId);
                startActivity(i);
                finish();
            }
        });

        binding.btnAddPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarPagamentos.this, RegistrarPagamento.class);
                intent.putExtra("cavalo", cavalo);
                intent.putExtra("cavaloId", cavaloId);
                startActivity(intent);
                finish();
            }
        });


    }

    private void configurarRecyclerView() {

        // Configurar o PopupMenu para os botões de opções nos CardViews
        adapterPagamento.setOnButtonOptionsClickListener(new AdapterPagamento.OnButtonOptionsClickListener() {
            @Override
            public void onOptionsClick(View view, Pagamento pagamento) {
                PopupMenu popupMenu = new PopupMenu(ListarPagamentos.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                // Configurar ações para os itens do menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_editar:
                                editarPagamento(pagamento);
                                return true;
                            case R.id.menu_excluir:
                                excluirPagamento(pagamento);
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popupMenu.show();
            }
        });

    }

    private void excluirPagamento(Pagamento pagamento) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertaExcluirStyle);
        builder.setTitle("Confirmação");
        builder.setMessage("Deseja excluir o pagamento " + pagamento.getNome() + "?");
        builder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lógica para excluir o pagamento
                dbHelper.excluirPagamento(pagamento);
                carregaDados();
                Toast.makeText(ListarPagamentos.this, "Pagamento excluído com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", null);

        AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                positiveButton.setTextColor(getResources().getColor(R.color.white));
                Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                negativeButton.setTextColor(getResources().getColor(R.color.white));
            }
        });

        dialog.show();
    }

    private void editarPagamento(Pagamento pagamento) {
        // Iniciar a activity de edição de pagamento e passar o objeto Pagamento para edição
        Intent intent = new Intent(ListarPagamentos.this, EditarPagamento.class);
        intent.putExtra("pagamento", pagamento);
        intent.putExtra("cavaloId", cavaloId);
        intent.putExtra("cavalo", cavalo);
        startActivity(intent);
    }

    private void carregaDados(){
        pagamentosList.clear();
        pagamentosList.addAll(dbHelper.getPagamentosByCavaloId(cavaloId));
        adapterPagamento.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Pagamento pagamento) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaDados();
    }



}