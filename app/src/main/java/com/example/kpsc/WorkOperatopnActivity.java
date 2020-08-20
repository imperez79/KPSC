package com.example.kpsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kpsc.util.DataBaseHelper;

import java.util.HashMap;

public class WorkOperatopnActivity extends AppCompatActivity {
    public DataBaseHelper helper;
    EditText mTextWho;
    EditText mTextDocument;
    EditText mTextNextPart;
    EditText mTextNextPartInf;
    EditText mTextNote;
    int operationID;
    Button mSaveDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_operatopn);
        mTextWho = findViewById(R.id.text_who);
        mTextDocument = findViewById(R.id.text_document);
        mTextNextPart= findViewById(R.id.text_next_part);
        mTextNextPartInf = findViewById(R.id.text_next_part_inf);
        mTextNote = findViewById(R.id.text_note);
        mSaveDescription = findViewById(R.id.saveDescription);
        mSaveDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> desc = new HashMap<>();
                desc.put("кто",mTextWho.getText().toString());
                desc.put("На основании какого документа",mTextDocument.getText().toString());
                desc.put("Какая деталь следующая",mTextNextPart.getText().toString());
                desc.put("Кто скажет где брать ",mTextNextPartInf.getText().toString());
                desc.put("Примечание",mTextNote.getText().toString());
                Intent intent = getIntent();
                intent.putExtra("workDescription",desc);
                setResult(RESULT_OK,intent);
                finish();

            }
        });
    }
}