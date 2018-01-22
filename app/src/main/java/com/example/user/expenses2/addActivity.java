package com.example.user.expenses2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class addActivity extends AppCompatActivity {

    AlertDialog alertDialog;
    int pos =0;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    EditText e1, e2;
    Calendar c;
    TextView textView3;
    int year;
    int month;
    int day;
    Sqlitehelper s;
    Sqlitehelper2 S;
    TextView textView;
    TextView textView2;
    int i1,i2,i3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        e1 = (EditText) findViewById(R.id.editText5);
        e2 = (EditText) findViewById(R.id.editText6);
        textView = (TextView) findViewById(R.id.textView8);
        textView2 = (TextView) findViewById(R.id.showBalance);
        S = new Sqlitehelper2(this);
        s = new Sqlitehelper(this);
        List<String> ar2 = S.getdata();
        textView.setText(ar2.get(0));
        List<UserBean> ar = s.getdata();
        textView2.setText(Integer.toString(S.getRemainingBudget()));

        s = new Sqlitehelper(this);
        textView3 = (TextView) findViewById(R.id.date);

        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        textView3.setText("" + year + "/" + month + "/" + day);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);

        }
        return null;
    }

        private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                textView3.setText(i + "/" + i1 + "/" + i2);
            }

    };
    public void clickphoto(View view)
    {
        Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap photo;
        pos = pos + 1;
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            photo = (Bitmap) extras.get("data");
            File sdCardDirectory = Environment.getExternalStorageDirectory() ;

            File image = new File(sdCardDirectory,pos+"test.png");
            boolean success = false;
            FileOutputStream outputStream;
            try {
                outputStream = new FileOutputStream(image);
                photo.compress(Bitmap.CompressFormat.PNG, 100 , outputStream);
                outputStream.flush();
                outputStream.close();
                success = true;
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            if(success){
                Toast.makeText(getApplicationContext(),"Image Saved",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"error during image saving",Toast.LENGTH_LONG).show();
            }

        }
    }


    public void addButton(View view) {
        long l = s.adddata(e1.getText().toString(), e2.getText().toString(), textView3.getText().toString());
        S.updateRemainingBudget(e2.getText().toString());
        S.getRemainingBudget();

        if (l > 0) {
            Toast.makeText(this, "data added", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Remaining budget is" + S.getRemainingBudget(), Toast.LENGTH_LONG).show();
            Log.d("insert log", "user added");

        }
        //List<String>ar= s.getdata();

        // S.updateRemainingBudget(ar.get(1));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logout)
        {
            startActivity(new Intent(addActivity.this,loginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
