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

import com.example.apptreinadores.databinding.ActivityListarRacoesBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListarRacoes extends AppCompatActivity implements AdapterRacao.OnItemClickListener {
    ActivityListarRacoesBinding binding;
    Cavalo cavalo;
    private Integer cavaloId;
    private AdapterRacao adapterRacao;
    private List<Racao> racaoList;
    private DBHelperCavalo dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListarRacoesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperCavalo(this);
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);

        binding.txtNome.setText(cavalo.getNome());

        racaoList = new ArrayList<>();
        adapterRacao = new AdapterRacao(racaoList, this);
        binding.rvRacoes.setLayoutManager(new LinearLayoutManager(this));
        binding.rvRacoes.setAdapter(adapterRacao);

        carregaDados();
        configurarRecyclerView();

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListarRacoes.this, Menu.class);
                i.putExtra("cavalo", cavalo);
                i.putExtra("cavaloId", cavaloId);
                startActivity(i);
                finish();
            }
        });

        binding.btnAddRacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarRacoes.this, RegistrarRacoes.class);
                intent.putExtra("cavalo", cavalo);
                intent.putExtra("cavaloId", cavaloId);
                startActivity(intent);
                finish();
            }
        });


    }

    private void configurarRecyclerView() {

        // Configurar o PopupMenu para os botões de opções nos CardViews
        adapterRacao.setOnButtonOptionsClickListener(new AdapterRacao.OnButtonOptionsClickListener() {
            @Override
            public void onOptionsClick(View view, Racao racao) {
                PopupMenu popupMenu = new PopupMenu(ListarRacoes.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                // Configurar ações para os itens do menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_editar:
                                editarRacao(racao);
                                return true;
                            case R.id.menu_excluir:
                                excluirRacao(racao);
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

    private void excluirRacao(Racao racao) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertaExcluirStyle);
        builder.setTitle("Confirmação");
        builder.setMessage("Deseja excluir a ração " + racao.getNome() + "?");
        builder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lógica para excluir a ração
                dbHelper.excluirRacao(racao);
                carregaDados();
                Toast.makeText(ListarRacoes.this, "Ração excluída com sucesso!", Toast.LENGTH_SHORT).show();
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

    private void editarRacao(Racao racao) {
        // Iniciar a activity de edição de remedio e passar o objeto Remedio para edição
        Intent intent = new Intent(ListarRacoes.this, EditarRacao.class);
        intent.putExtra("racao", racao);
        intent.putExtra("cavaloId", cavaloId);
        intent.putExtra("cavalo", cavalo);
        startActivity(intent);
    }

    @Override
    public void onItemClick(Racao racao) {

    }

    private void carregaDados(){
        racaoList.clear();
        racaoList.addAll(dbHelper.getRacoesByCavaloId(cavaloId));
        adapterRacao.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaDados();
    }


}