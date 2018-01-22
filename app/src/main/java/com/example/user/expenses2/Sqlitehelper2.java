package com.example.user.expenses2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11-07-2017.
 */

public class Sqlitehelper2 extends SQLiteOpenHelper {



    String tablename="tablename2";
    String budget="budget";
    String remainingbudget = "remainingbudget";
    public Sqlitehelper2(Context context) {
        super(context,"db2", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s= " Create table "+tablename+"("+budget+" INTEGER, " + remainingbudget +" INTEGER " +")";
        db.execSQL(s);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long adddata(String budget){

        SQLiteDatabase db= getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(this.budget,budget);
        values.put(this.remainingbudget,budget);
        return db.insert(tablename,null,values);
    }
    public  int getRemainingBudget() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(" select " + remainingbudget + " from " + tablename, null);
        if (cursor.moveToNext()) {
            return cursor.getInt(0);
        }
        return  0;
    }
    public long updateRemainingBudget(String cost)
    {
        SQLiteDatabase db = getWritableDatabase();
        int remainingBudget = getRemainingBudget();
        int updatePrice = remainingBudget - Integer.parseInt(cost);
        ContentValues contentValues = new ContentValues();
        contentValues.put(remainingbudget,updatePrice);
        return db.update(tablename,contentValues,null,null);

    } public long addtoRemainingBudget(String cost)
    {
        SQLiteDatabase db = getWritableDatabase();
        int remainingBudget = getRemainingBudget();
        int updatePrice = remainingBudget + Integer.parseInt(cost);
        ContentValues contentValues = new ContentValues();
        contentValues.put(remainingbudget,updatePrice);
        return db.update(tablename,contentValues,null,null);

    }
    public List<String> getdata() {
        SQLiteDatabase db = getReadableDatabase();
        List<String> ar2 = new ArrayList<String>();
        Cursor cr = db.rawQuery(" Select *from " + tablename, null);
        while (cr.moveToNext()) {
            ar2.add(cr.getString(0));
        }
        return (ar2);
    }
    public void deleteAll(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" delete from "+ tablename);
    }



}

