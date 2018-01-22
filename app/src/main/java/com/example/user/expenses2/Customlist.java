package com.example.user.expenses2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Customlist extends ArrayAdapter<UserBean> {

    ArrayList<UserBean> List;
    UserBean u ;

    Context context;
    public Customlist(Context context, ArrayList<UserBean> ar) {
        super(context,R.layout.activity_customlist,ar);
        this.List = ar;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(getContext());
        View v= inflater.inflate(R.layout.activity_customlist,parent,false);
        //String b= getItem(position);

        ImageView img= (ImageView)v.findViewById(R.id.imageView);
        TextView item=(TextView)v.findViewById(R.id.textView12);
        TextView cost=(TextView)v.findViewById(R.id.textView14);
        TextView date=(TextView)v.findViewById(R.id.textView16);
        u = List.get(position);
        item.setText(u.getItem());
        cost.setText(u.getCost());
        date.setText(u.getDate());

       // t1.setText(b);
       img.setImageResource(R.drawable.check);
       return v;

    }
}
