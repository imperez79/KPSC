package com.example.kpsc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kpsc.model.Operation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class StorageAcrivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_acrivity);
        ArrayList<Operation> operations=new ArrayList<>();
        sharedPreferences = getSharedPreferences(RouteActivity.APP_Prefefnces, MODE_PRIVATE);
        String listJson = sharedPreferences.getString("listOperations", "");
        Gson gson = new Gson();
        operations = gson.fromJson(listJson, new TypeToken<ArrayList<Operation>>() {
        }.getType());




    }



}