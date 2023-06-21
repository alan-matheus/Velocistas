package com.example.apptreinadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apptreinadores.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterCavalo.OnItemClickListener{

    ActivityMainBinding binding;
    private AdapterCavalo ca;
    private List<Cavalo> cavaloList;
    private DBHelperCavalo dbHelper;
    private DBHelperRemedio dbHelperRemedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper = new DBHelperCavalo(this);
        dbHelperRemedio = new DBHelperRemedio(this, dbHelper);
        cavaloList = new ArrayList<>();
        ca = new AdapterCavalo(cavaloList, this);

         binding.rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

         binding.rv.setAdapter(ca);

        carregaDados();

        binding.btnRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrarCavalo.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void onItemClick(Cavalo cavalo){
        Intent intent = new Intent(MainActivity.this, Menu.class);
        intent.putExtra("cavaloId", cavalo.getId());
        System.out.println(cavalo.getId());
        intent.putExtra("cavalo", cavalo);
        startActivity(intent);
    }


    private void carregaDados(){
        cavaloList.clear();
        cavaloList.addAll(dbHelper.getAllCavalos());
        ca.notifyDataSetChanged();
    }




}