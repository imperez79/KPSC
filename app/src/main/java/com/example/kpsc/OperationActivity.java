package com.example.kpsc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.kpsc.model.Operation;
import com.example.kpsc.util.DataBaseHelper;
import com.example.kpsc.util.TimeUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class OperationActivity extends AppCompatActivity {
    private static final int PHOTO_INTENT_REQUEST_CODE = 1;
    private static final int SETUP_TIME_INTENT_REQUEST_CODE = 101;
    private static final int DESCRIPTION_INTENT_REQUEST_CODE = 1000;
    private static final int TOTAL_TIME_INTENT_REQUEST_CODE = 100;
    //private static final int STORAGE_DESCRIPTION_INTENT_REQUEST_CODE = 1001;
    //private static final int WORKING_DESCRIPTION_INTENT_REQUEST_CODE = 1002;
    public DataBaseHelper helper;
    private ImageView mPicture;
    private Spinner typeWork;
    private Button mSaveOperation;
    private Button mDescription;
    private ImageButton mCamera;
    private EditText nameOperation;
    private EditText wipBefore;
    private EditText wipAfter;
    private EditText totalTime;
    private EditText waitingTime;
  //  private Uri mUri;
    private int changePosition;
    private String mCurrentPath;
    private String work;
    private String transport;
    private String storage;
    private HashMap tempDescription= new HashMap<>();
    private int idOperation =-1;

    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        Intent editIntent = getIntent();
        mSaveOperation = findViewById(R.id.btn_save_operation);
        mDescription = findViewById(R.id.description);
        mCamera = findViewById(R.id.take_picture);
        mPicture = findViewById(R.id.oper_picture);
        typeWork = findViewById(R.id.spTypeWork);
        nameOperation = findViewById(R.id.text_name_operation);
        wipBefore = findViewById(R.id.wip_before2);
        wipAfter = findViewById(R.id.wip_after);
        totalTime = findViewById(R.id.total_time);
        waitingTime = findViewById(R.id.waiting_time);
        totalTime.setOnLongClickListener(view -> {
            Intent intent = new Intent(OperationActivity.this, ChronometrActivity.class);
            startActivityForResult(intent,TOTAL_TIME_INTENT_REQUEST_CODE);
            return false;
        });
        waitingTime.setOnLongClickListener(view -> {
            Intent intent = new Intent(OperationActivity.this, ChronometrActivity.class);
            startActivityForResult(intent,SETUP_TIME_INTENT_REQUEST_CODE);
            return false;
        });
        if (savedInstanceState!=null){
            mCurrentPath=  savedInstanceState.getString("path");
            bitmap = BitmapFactory.decodeFile(mCurrentPath);
           // mPicture.setRotation(90f);
            mPicture.setImageBitmap(bitmap);
        }
        mCamera.setOnClickListener(view -> {
            File pictureFile ;
            try {
                 pictureFile = generateFileUri();
            } catch (IOException e) {
                Toast.makeText(OperationActivity.this,
                        "Не получается создать файл, попробуйте еще раз",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            Uri pictureUri = FileProvider.getUriForFile(OperationActivity.this,
                    "com.example.kpsc.fileprovider",pictureFile) ;
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
            startActivityForResult(intent, PHOTO_INTENT_REQUEST_CODE);
        });
        mSaveOperation.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("operation", createOperation());
            setResult(RESULT_OK, intent);
            finish();
        });
        mDescription.setOnClickListener(view -> {
            Intent intent ;
          //  checkSpinner();
            typeWork.getSelectedItem().toString();
            switch (typeWork.getSelectedItem().toString()) {
                case ("Транспортировка"):
                    intent = new Intent(OperationActivity.this,TransportActivity.class);
                    break;
                case ("Складирование"):
                    intent = new Intent(OperationActivity.this,StorageAcrivity.class);
                    break;
                default:
                    intent = new Intent(OperationActivity.this,WorkOperatopnActivity.class);

            }
            startActivityForResult(intent,DESCRIPTION_INTENT_REQUEST_CODE);


        });



    }

    private File generateFileUri() throws IOException {

        File directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        String timeStamp = String.valueOf(System.currentTimeMillis());
        File image = File.createTempFile(timeStamp,"jpg", directory);
        mCurrentPath = image.getAbsolutePath();
        return image;
    }


    private Operation createOperation() {
        Operation operation = new Operation();
        operation.setTypeOfWork(typeWork.getSelectedItem().toString());
        operation.setNameOperation(nameOperation.getText().toString());
        switch (operation.getTypeOfWork()) {
            case ("Обработка"):
                operation.setLabelTypeWork(R.drawable.obrabotka_metalla);
                break;
            case ("Транспортировка"):
                operation.setLabelTypeWork(R.drawable.transport);
                break;
            case ("Складирование"):
                operation.setLabelTypeWork(R.drawable.storage);
                break;
            default:
                operation.setLabelTypeWork(R.drawable.ic_launcher_foreground);

        }
       if (totalTime.getText().toString().equals("")){
           totalTime.setText("0");}
       else{
           String strTime = totalTime.getText().toString();
           if (strTime.contains(":")){
               operation.setTimeOfWork(TimeUtil.convertStingTimeToInteger(strTime));
           }else{
               operation.setTimeOfWork(Integer.parseInt(totalTime.getText().toString()));
           }
       }


        if (waitingTime.getText().toString().equals("")){
           waitingTime.setText("0");}
        else{
            String strTime = waitingTime.getText().toString();
            if (strTime.contains(":")){
                operation.setTimeSetup(TimeUtil.convertStingTimeToInteger(strTime));
            }else{
                operation.setTimeSetup(Integer.parseInt(waitingTime.getText().toString()));
            }

        if (wipAfter.getText().toString().equals("")){
            wipAfter.setText("0");}

               operation.setWipAfter(Integer.parseInt(wipAfter.getText().toString()));}
        if (wipBefore.getText().toString().equals("")){
            wipBefore.setText("0");}

        operation.setWipBefore(Integer.parseInt(wipBefore.getText().toString()));
        operation.setDescription(tempDescription);


       // operation.setPathImage(Uri.parse(mCurrentPath));
    return operation;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_INTENT_REQUEST_CODE && resultCode != RESULT_CANCELED) {

            File imgFile = new  File(mCurrentPath);
            if(imgFile.exists())            {
                bitmap = BitmapFactory.decodeFile(mCurrentPath);
                mPicture.setRotation(90f);
                mPicture.setImageBitmap(bitmap);
                //mPicture.setImageURI(Uri.fromFile(imgFile));
            }
        }
        if (requestCode == DESCRIPTION_INTENT_REQUEST_CODE && resultCode == RESULT_OK) {
            tempDescription = (HashMap)data.getSerializableExtra("workDescription");

        }
        if (requestCode == TOTAL_TIME_INTENT_REQUEST_CODE && resultCode == RESULT_OK) {
         String time=  data.getStringExtra("totalTime");
          totalTime.setText(time);
        }
        if (requestCode == SETUP_TIME_INTENT_REQUEST_CODE && resultCode == RESULT_OK) {
            waitingTime.setText(data.getStringExtra("totalTime"));

        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("path",mCurrentPath);
        outState.putSerializable("tempMap",tempDescription);

    }


}


