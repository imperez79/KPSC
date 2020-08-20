package com.example.kpsc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kpsc.model.Route;
import com.example.kpsc.util.DataBaseHelper;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button mStartMap;
    private EditText nameKPSC;
    private Route route;
    private SharedPreferences preferencesRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartMap = findViewById(R.id.create_route);
        nameKPSC = findViewById(R.id.name_kpsc);
        preferencesRoute = getSharedPreferences("route",MODE_PRIVATE);
        String str =   preferencesRoute.getString("route","empty");

        assert str != null;
        if(!str.equals("empty")){
            //Toast.makeText(this,"fff", Toast.LENGTH_LONG).show();
           AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
           builder.setTitle("Внимание");
           builder.setMessage("Обнаружен проект");
           builder.setPositiveButton("Продолжить", (dialogInterface, i) -> {
                Gson gson = new Gson();
               route = gson.fromJson(str,new TypeToken<Route>(){}.getType());
               nameKPSC.setEnabled(false);
               nameKPSC.setText(route.getName());
               mStartMap.setText("Продолжить");
           });
           builder.setNegativeButton("Начать новый", (dialogInterface, i) -> {
               SharedPreferences.Editor editor = preferencesRoute.edit();
               editor.clear();
               editor.apply();
           });
           builder.create();
           builder.show();
        }

        mStartMap.setOnClickListener(view -> {
            if(route==null){
                route =new Route();
                route.setOperations(new ArrayList<>());

            }
            Intent intent = new Intent(MainActivity.this,RouteActivity.class);
            if(nameKPSC.getText().length()!=0){
                route.setName(nameKPSC.getText().toString());
            intent.putExtra("route",route);
                startActivity(intent);
            }
            else {
                Toast.makeText(MainActivity.this,"Введите название КПСЦ",Toast.LENGTH_SHORT).show();
            }

        });
    }




}