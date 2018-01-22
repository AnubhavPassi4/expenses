package com.example.user.expenses2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import java.util.Calendar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class contentActivity extends AppCompatActivity {
    Sqlitehelper s;
    Sqlitehelper2 S;
    Button button;
    SharedPreferences preferences;
    AlertDialog alertDialog;
    int current_month;
    int current_year;
    int cmin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Calendar calendar = Calendar.getInstance();
        current_month = calendar.get(Calendar.MONTH);
        current_year = calendar.YEAR;
        preferences = getSharedPreferences("pref1",MODE_PRIVATE);
        int old_month = preferences.getInt("month",0);
        int old_year = preferences.getInt("year",0);

        s=new Sqlitehelper(this);
        S=new Sqlitehelper2(this);
        boolean isFirstTime = preferences.getBoolean("isfirstTime",true);

        if (!isFirstTime) {
            if (current_month != old_month) {
                Toast.makeText(this, "Update Budget", Toast.LENGTH_LONG).show();
                preferences = getSharedPreferences("pref1", MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putInt("month", current_month);
                edit.putInt("year", current_year);
                edit.commit();
                //show alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(contentActivity.this);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.activity_dialog_box, null);
                builder.setView(view1);
                alertDialog = builder.create();
                alertDialog.show();
                final EditText editText = (EditText) view1.findViewById(R.id.editText9);
                button = (Button) view1.findViewById(R.id.button6);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        S.deleteAll();
                        long l = S.adddata(editText.getText().toString());
                        if (l > 0) {
                            Log.d("insert log", "budget added");
                            Toast.makeText(contentActivity.this, "budget added", Toast.LENGTH_LONG).show();
                            alertDialog.dismiss();


                        }

                    }
                });
            } else {
                startActivity(new Intent(contentActivity.this, addActivity.class));
            }
        }else
        {
            preferences = getSharedPreferences("pref1",MODE_PRIVATE);
            SharedPreferences.Editor edit= preferences.edit();
            edit.putBoolean("isfirstTime",false);
            edit.commit();

        AlertDialog.Builder builder = new AlertDialog.Builder(contentActivity.this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.activity_dialog_box, null);
        builder.setView(view1);
        alertDialog = builder.create();
        alertDialog.show();
        final EditText editText = (EditText)view1.findViewById(R.id.editText9);
        button = (Button)view1.findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long l = S.adddata(editText.getText().toString());
                if (l > 0) {
                    Log.d("insert log", "budget added");
                    Toast.makeText(contentActivity.this,"budget added",Toast.LENGTH_LONG).show();
                    alertDialog.dismiss();


                }

            }
        });


        }


    }
    public void fun1(View view)
    {
        startActivity(new Intent(contentActivity.this,addActivity.class));
        finish();
    }
    public void fun2(View view)
    {
        //List<String> a=S.getdata();
        Log.d("getdata","IIIIIIIIIII");
        startActivity(new Intent(contentActivity.this,showActivity.class));
        finish();
    }

  /* @Override
    public void onBackPressed() {
       super.onBackPressed();
        this.finish();
    }*/


}

