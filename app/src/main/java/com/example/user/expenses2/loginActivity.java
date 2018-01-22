package com.example.user.expenses2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    EditText e3, e4;
    Button b;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e4 = (EditText) findViewById(R.id.editText4);
        b = (Button) findViewById(R.id.button2);
        e3 = (EditText) findViewById(R.id.editText3);
        // SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
        //String name =preferences.getString("name","default");
        // String pass= preferences.getString("password","default");
        // Log.e("name",name);
        //Log.e("password",pass);
    }

    public void btnClick(View view) {
        SharedPreferences preferences = getSharedPreferences("pref", MODE_PRIVATE);
        String name = preferences.getString("name", "default");
        String pass = preferences.getString("password", "default");
        String et1 = e3.getText().toString();
        String et2 = e4.getText().toString();
        if (name.equals(et1) && pass.equals(et2)) {
            startActivity(new Intent(loginActivity.this,contentActivity.class));

           /* AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view1 = inflater.inflate(R.layout.activity_dialog_box, null);
            builder.setView(view1);
            alertDialog = builder.create();
            alertDialog.show();
            final EditText editText = (EditText) findViewById(R.id.editText9);
            Button button = (Button) findViewById(R.id.button6);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    long l = s.adddata(editText.getText().toString());
                    if (l > 0) {
                        Log.d("insert log", "budget added");


                    }
                    startActivity(new Intent(loginActivity.this, contentActivity.class));

                }
            }); */
        }
    }
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);

        builder.setTitle("ExpensesApp").setMessage("Do u want to finish")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(loginActivity.this, "You clicked no", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        }).setCancelable(false);
        builder.create().show();

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.customdialog, null);
        builder.setView(view1);
        alertDialog = builder.create();
        alertDialog.show();

    /*Button button = (Button)view1.findViewById(R.id.button);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            alertDialog.dismiss();
        }*/
    }
}
