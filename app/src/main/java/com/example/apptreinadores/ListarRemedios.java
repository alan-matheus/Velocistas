package com.example.apptreinadores;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.apptreinadores.databinding.ActivityListarRemediosBinding;

import java.util.ArrayList;
import java.util.List;

public class ListarRemedios extends AppCompatActivity implements AdapterRemedio.OnItemClickListener{
    ActivityListarRemediosBinding binding;
    private Cavalo cavalo;
    private Integer cavaloId;
    private AdapterRemedio adapterRemedio;
    private List<Remedio> remedioList;
    private DBHelperCavalo dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListarRemediosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperCavalo(this);

        cavalo = (Cavalo) getIntent().getSerializableExtra("cavalo");
        cavaloId = getIntent().getIntExtra("cavaloId", -1);

        binding.txtNome.setText(cavalo.getNome());

        remedioList = new ArrayList<>();
        adapterRemedio = new AdapterRemedio(remedioList, this);
        binding.rvRemedios.setLayoutManager(new LinearLayoutManager(this));
        binding.rvRemedios.setAdapter(adapterRemedio);

        carregaDados();
        configurarRecyclerView();

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListarRemedios.this, Menu.class);
                i.putExtra("cavalo", cavalo);
                i.putExtra("cavaloId", cavaloId);
                startActivity(i);
                finish();
            }
        });

        binding.btnAddRemedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarRemedios.this, RegistrarRemedios.class);
                intent.putExtra("cavalo", cavalo);
                intent.putExtra("cavaloId", cavaloId);
                startActivity(intent);
                finish();
            }
        });


    }

    private void configurarRecyclerView() {

        // Configurar o PopupMenu para os botões de opções nos CardViews
        adapterRemedio.setOnButtonOptionsClickListener(new AdapterRemedio.OnButtonOptionsClickListener() {
            @Override
            public void onOptionsClick(View view, Remedio remedio) {
                PopupMenu popupMenu = new PopupMenu(ListarRemedios.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                // Configurar ações para os itens do menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_editar:
                                editarRemedio(remedio);
                                return true;
                            case R.id.menu_excluir:
                                excluirRemedio(remedio);
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

    private void excluirRemedio(Remedio remedio) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertaExcluirStyle);
        builder.setTitle("Confirmação");
        builder.setMessage("Deseja excluir o remédio " + remedio.getNome() + "?");
        builder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lógica para excluir o remedio
                dbHelper.excluirRemedio(remedio);
                carregaDados();
                Toast.makeText(ListarRemedios.this, "Remedio excluído com sucesso!", Toast.LENGTH_SHORT).show();
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

    private void editarRemedio(Remedio remedio) {
        // Iniciar a activity de edição de remedio e passar o objeto Remedio para edição
        Intent intent = new Intent(ListarRemedios.this, EditarRemedio.class);
        intent.putExtra("remedio", remedio);
        intent.putExtra("cavaloId", cavaloId);
        intent.putExtra("cavalo", cavalo);
        startActivity(intent);
    }

    @Override
    public void onItemClick(Remedio remedio) {

    }

    private void carregaDados(){
        remedioList.clear();
        remedioList.addAll(dbHelper.getRemediosByCavaloId(cavaloId));
        adapterRemedio.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaDados();
    }
}