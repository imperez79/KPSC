package com.example.kpsc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kpsc.model.Operation;
import com.example.kpsc.model.Route;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

//import org.apache.poi.ss.usermodel.Workbook;

public class RouteActivity extends AppCompatActivity {
    private static final String LIST= "operations";
    private  String jsonString;
    private TextView mName;
    private Button mCreateOperation;
    private Button mSaveRoute;
    private RecyclerView rv;
    private RecViewAdapter recViewAdapter;
    private SharedPreferences preferences;
    private ArrayList<Operation> operations ;
    private Route route;
   public static final String APP_Prefefnces ="p";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        Intent intent = getIntent();
        route = (Route) intent.getSerializableExtra("route");
        rv = findViewById(R.id.recyclerView);
        mCreateOperation = findViewById(R.id.create_operation);
        mSaveRoute = findViewById(R.id.save_route);
        mName = findViewById(R.id.route_name);
        mName.setText(route.getName());
        operations = (ArrayList<Operation>) route.getOperations();
        LinearLayoutManager layoutManager = new LinearLayoutManager(RouteActivity.this);
        rv.setLayoutManager(layoutManager);
        RecViewAdapter.onOperationClickListener clickListener = operation -> {
            Intent detOp = new Intent(RouteActivity.this,OperationActivity.class);
            detOp.putExtra("temp",operation);
            startActivityForResult(detOp,200);
        };
        recViewAdapter = new RecViewAdapter(clickListener,operations);
        rv.setAdapter(recViewAdapter);
        if (savedInstanceState != null){
        operations = (ArrayList<Operation>) savedInstanceState.getSerializable(LIST);
            initializerAdapter();
        }
        mCreateOperation.setOnClickListener(view -> {
            Intent intent1;
            intent1 = new Intent(RouteActivity.this,OperationActivity.class);
            startActivityForResult(intent1,1);
        });




        mSaveRoute.setOnClickListener(view -> {
            Route route = new Route();
            route.setName(mName.getText().toString());
            route.setOperations(operations);
            routeToGSON(route);
        });
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(LIST,  operations);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            assert data != null;
            Operation operation = (Operation) data.getSerializableExtra("operation");
            recViewAdapter.addOperation(operation);
        }
    }

    private void initializerAdapter() {
        recViewAdapter = new RecViewAdapter(operations);
        rv.setAdapter(recViewAdapter);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mSaveRoute.callOnClick();

    }

    private void routeToGSON(Route route) {
        Gson gson = new Gson();
        String str = gson.toJson(route);
        preferences = getSharedPreferences("route",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("route",str);
        editor.apply();
    }

}