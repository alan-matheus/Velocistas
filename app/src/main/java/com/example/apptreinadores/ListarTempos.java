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

import com.example.apptreinadores.databinding.ActivityListarTemposBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListarTempos extends AppCompatActivity implements AdapterTempo.OnItemClickListener{
    ActivityListarTemposBinding binding;
    private Cavalo cavalo;
    private Integer cavaloId;
    private AdapterTempo adapterTempo;
    private List<TempoTreino> tempoList;
    private DBHelperCavalo dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListarTemposBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperCavalo(this);
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);

        binding.txtNome.setText(cavalo.getNome());

        tempoList = new ArrayList<>();
        adapterTempo = new AdapterTempo(tempoList, this);
        binding.rvTempos.setLayoutManager(new LinearLayoutManager(this));
        binding.rvTempos.setAdapter(adapterTempo);

        carregaDados();
        configurarRecyclerView();

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListarTempos.this, Menu.class);
                i.putExtra("cavalo", cavalo);
                i.putExtra("cavaloId", cavaloId);
                startActivity(i);
                finish();
            }
        });

        binding.btnAddTempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarTempos.this, RegistrarTempoTreino.class);
                intent.putExtra("cavalo", cavalo);
                intent.putExtra("cavaloId", cavaloId);
                startActivity(intent);
                finish();
            }
        });

    }

    private void configurarRecyclerView() {

        // Configurar o PopupMenu para os botões de opções nos CardViews
        adapterTempo.setOnButtonOptionsClickListener(new AdapterTempo.OnButtonOptionsClickListener() {
            @Override
            public void onOptionsClick(View view, TempoTreino tempoTreino) {
                PopupMenu popupMenu = new PopupMenu(ListarTempos.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                // Configurar ações para os itens do menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_editar:
                                editarTempo(tempoTreino);
                                return true;
                            case R.id.menu_excluir:
                                excluirTempo(tempoTreino);
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

    private void excluirTempo(TempoTreino tempoTreino) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertaExcluirStyle);
        builder.setTitle("Confirmação");
        builder.setMessage("Deseja excluir o tempo " + tempoTreino.getTempo() + "?");
        builder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lógica para excluir o tempo
                dbHelper.excluirTempo(tempoTreino);
                carregaDados();
                Toast.makeText(ListarTempos.this, "Tempo excluído com sucesso!", Toast.LENGTH_SHORT).show();
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

    private void editarTempo(TempoTreino tempoTreino) {
        // Iniciar a activity de edição de tempo e passar o objeto Tempo para edição
        Intent intent = new Intent(ListarTempos.this, EditarTempo.class);
        intent.putExtra("tempo", tempoTreino);
        intent.putExtra("cavaloId", cavaloId);
        intent.putExtra("cavalo", cavalo);
        startActivity(intent);
    }

    private void carregaDados(){
        tempoList.clear();
        tempoList.addAll(dbHelper.getTemposByCavaloId(cavaloId));
        adapterTempo.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(TempoTreino tempoTreino) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaDados();
    }

}