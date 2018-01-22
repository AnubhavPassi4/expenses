package com.example.user.expenses2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 08-07-2017.
 */

public class Sqlitehelper extends SQLiteOpenHelper {

    String item="item";
    String cost= "cost";
    String date="date";
    String tablename="tablename";
    String column_id = "id" ;
    int bal;


    public Sqlitehelper(Context context) {
        super(context,"db1", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s= " Create table "+tablename+"(" + column_id +" INTEGER PRIMARY KEY AUTOINCREMENT, " +item+" text,"+cost+" INTEGER, "+date+" INTEGER "+")";
        db.execSQL(s);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long adddata(String item,String cost,String date){

        SQLiteDatabase db= getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(this.item,item);
        values.put(this.date,date);
        values.put(this.cost,cost);
        return db.insert(tablename,null,values);
    }
    public ArrayList<UserBean> getdata() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<UserBean> ar = new ArrayList<UserBean>();
        Cursor cr = db.rawQuery(" Select *from " + tablename, null);
        while (cr.moveToNext()) {
            UserBean u = new UserBean(cr.getString(0),cr.getString(1),cr.getString(2),cr.getString(3));
            ar.add(u);

        }
        return (ar);
    }

    public long DeleteData(Integer id)
    {
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL(" DELETE FROM " + tablename + " WHERE " + column_id + " = " + id + " ; ");
        // why toast not working???Toast.makeText(this,"item deleted",Toast.LENGTH_LONG).show();
        Log.d("delete","delete");
        return db.delete(tablename,"id =?",new String[]{id.toString()});
    }
    public long updateData(Integer id,String item,String cost,String date)
    {
        SQLiteDatabase db = getWritableDatabase();
        Log.d("edit","edit");
        ContentValues values = new ContentValues();
        values.put(this.item,item);
        values.put(this.date,date);
        values.put(this.cost,cost);
        return  db.update(tablename,values,"id=?",new String[]{id.toString()});

    }
   /* public int total()
    {
        SQLiteDatabase db =getReadableDatabase();
        Cursor c =db.rawQuery(" select sum(cost) from "+ tablename + ";",null);
        if(c.moveToNext())
        {
            bal= c.getInt(0);
        }
        else
            bal= 0;
        c.close();
        return bal;
    }*/

}
