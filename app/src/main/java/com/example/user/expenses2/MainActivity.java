package com.example.user.expenses2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1= (EditText)findViewById(R.id.editText);
        e2= (EditText)findViewById(R.id.editText2);

        SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("isfirstTime",true);
        if(!isFirstTime) {
            startActivity(new Intent(MainActivity.this, loginActivity.class));
            finish();
        }


    }
    public void save_pref(View view)
    {
        String name = e1.getText().toString();
        String pass = e2.getText().toString();
        if(!name.equals(" "))
        {
            SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();   //SharedPreferences.Editor Interface used for modifying values in a SharedPreferences object
            edit.putString("name",name);
            edit.putString("password",pass);
            edit.putBoolean("isfirstTime",false);
            edit.commit();
            startActivity(new Intent(MainActivity.this, loginActivity.class));
            this.finish();
        }
    }
}
