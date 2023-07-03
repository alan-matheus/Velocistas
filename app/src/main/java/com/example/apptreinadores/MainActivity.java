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

import com.example.apptreinadores.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterCavalo.OnItemClickListener{

    ActivityMainBinding binding;
    private AdapterCavalo ca;
    private List<Cavalo> cavaloList;
    private DBHelperCavalo dbHelper;
    private SQLiteDatabase dbCavalo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper = new DBHelperCavalo(this);
        dbCavalo = dbHelper.getWritableDatabase();

        cavaloList = new ArrayList<>();
        ca = new AdapterCavalo(cavaloList, this);
        binding.rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rv.setAdapter(ca);

        carregaDados();
        configurarRecyclerView();

        binding.btnRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrarCavalo.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void configurarRecyclerView() {

        // Configurar o PopupMenu para os botões de opções nos CardViews
        ca.setOnButtonOptionsClickListener(new AdapterCavalo.OnButtonOptionsClickListener() {
            @Override
            public void onOptionsClick(View view, Cavalo cavalo) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                // Configurar ações para os itens do menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_editar:
                                editarCavalo(cavalo);
                                return true;
                            case R.id.menu_excluir:
                                excluirCavalo(cavalo);
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
    private void excluirCavalo(Cavalo cavalo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertaExcluirStyle);
        builder.setTitle("Confirmação");
        builder.setMessage("Deseja excluir o cavalo " + cavalo.getNome() + "?");
        builder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lógica para excluir o cavalo
                dbHelper.excluirCavalo(cavalo);
                carregaDados();
                Toast.makeText(MainActivity.this, "Cavalo excluído com sucesso!", Toast.LENGTH_SHORT).show();
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

    private void editarCavalo(Cavalo cavalo) {
        // Iniciar a activity de edição de cavalo e passar o objeto Cavalo para edição
        Intent intent = new Intent(MainActivity.this, EditarCavalo.class);
        intent.putExtra("cavalo", cavalo);
        startActivity(intent);
    }

    public void onItemClick(Cavalo cavalo){
        Intent intent = new Intent(MainActivity.this, Menu.class);
        intent.putExtra("cavaloId", cavalo.getId());
        intent.putExtra("cavalo", cavalo);
        startActivity(intent);
    }

    private void carregaDados(){
        cavaloList.clear();
        cavaloList.addAll(dbHelper.getAllCavalos());
        ca.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaDados();
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        dbCavalo.close();

    }

}