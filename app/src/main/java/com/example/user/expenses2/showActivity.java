package com.example.user.expenses2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class showActivity extends AppCompatActivity {

    ListView l;
    ArrayAdapter<UserBean> adapter;
    Sqlitehelper S = new Sqlitehelper(this);
    Sqlitehelper2 sqlitehelper2 = new Sqlitehelper2(this);
    ArrayList<UserBean> ar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        l=(ListView)findViewById(R.id.Listview);
         ar = S.getdata();
         adapter  = new Customlist(this,ar);
        l.setAdapter(adapter);
        registerForContextMenu(l);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.delete:
                ar.remove(info.position);
                adapter.notifyDataSetChanged();
                ar = S.getdata();

               long l= S.DeleteData(Integer.parseInt(ar.get(info.position).getColumn_id()));
                if(l>0){
                    Toast.makeText(this,"data deleted",Toast.LENGTH_LONG).show();
                }
                sqlitehelper2.addtoRemainingBudget(ar.get(info.position).getCost());
                return  true;

            case R.id.edit:
                ar.remove(info.position);
                adapter.notifyDataSetChanged();
                ar = S.getdata();

                long s= S.DeleteData(Integer.parseInt(ar.get(info.position).getColumn_id()));

                //  long s= S.updateData(Integer.parseInt(ar.get(info.position).getColumn_id()),ar.get(1).toString(),ar.get(2).toString(),ar.get(3).toString());
                if(s>0){
                    startActivity(new Intent(showActivity.this,addActivity.class));

                    Toast.makeText(this,"edit data",Toast.LENGTH_LONG).show();
                }
              //  sqlitehelper2.addtoRemainingBudget(S.cost.toString());
                sqlitehelper2.addtoRemainingBudget(ar.get(info.position).getCost());



                return  true;



            default:
                  return super.onContextItemSelected(item);

        }

    }
}
