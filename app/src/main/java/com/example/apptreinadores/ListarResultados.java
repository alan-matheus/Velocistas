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
import com.example.apptreinadores.databinding.ActivityListarResultadosBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListarResultados extends AppCompatActivity implements AdapterResultado.OnItemClickListener{
    ActivityListarResultadosBinding binding;
    private Cavalo cavalo;
    private Integer cavaloId;
    private AdapterResultado adapterResultado;
    private List<Resultado> resultadosList;
    private DBHelperCavalo dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListarResultadosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperCavalo(this);
        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);

        binding.txtNome.setText(cavalo.getNome());

        resultadosList = new ArrayList<>();
        adapterResultado = new AdapterResultado(resultadosList, this);
        binding.rvResultados.setLayoutManager(new LinearLayoutManager(this));
        binding.rvResultados.setAdapter(adapterResultado);

        carregaDados();
        configurarRecyclerView();

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListarResultados.this, Menu.class);
                i.putExtra("cavalo", cavalo);
                i.putExtra("cavaloId", cavaloId);
                startActivity(i);
                finish();
            }
        });

        binding.btnAddResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarResultados.this, RegistrarResultado.class);
                intent.putExtra("cavalo", cavalo);
                intent.putExtra("cavaloId", cavaloId);
                startActivity(intent);
                finish();
            }
        });

    }

    private void configurarRecyclerView() {

        // Configurar o PopupMenu para os botões de opções nos CardViews
        adapterResultado.setOnButtonOptionsClickListener(new AdapterResultado.OnButtonOptionsClickListener() {
            @Override
            public void onOptionsClick(View view, Resultado resultado) {
                PopupMenu popupMenu = new PopupMenu(ListarResultados.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                // Configurar ações para os itens do menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_editar:
                                editarResultado(resultado);
                                return true;
                            case R.id.menu_excluir:
                                excluirResultado(resultado);
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

    private void excluirResultado(Resultado resultado) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertaExcluirStyle);
        builder.setTitle("Confirmação");
        builder.setMessage("Deseja excluir o resultado " + resultado.getNome() + "?");
        builder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lógica para excluir o resultado
                dbHelper.excluirResultado(resultado);
                carregaDados();
                Toast.makeText(ListarResultados.this, "Resultado excluído com sucesso!", Toast.LENGTH_SHORT).show();
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

    private void editarResultado(Resultado resultado) {
        // Iniciar a activity de edição de resultado e passar o objeto Resultado para edição
        Intent intent = new Intent(ListarResultados.this, EditarResultado.class);
        intent.putExtra("resultado", resultado);
        intent.putExtra("cavaloId", cavaloId);
        intent.putExtra("cavalo", cavalo);
        startActivity(intent);
    }

    private void carregaDados(){
        resultadosList.clear();
        resultadosList.addAll(dbHelper.getResultadosByCavaloId(cavaloId));
        adapterResultado.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Resultado resultado) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaDados();
    }
}