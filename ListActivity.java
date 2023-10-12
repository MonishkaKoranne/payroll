package com.example.payrollmanagementsystemminorproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class ListActivity extends AppCompatActivity {
DBHelper DB;
MaterialButton view;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        view=findViewById(R.id.btnview);
        DB=new DBHelper(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=DB.getdata();
                Cursor rec=DB.getall();
                if (res.getCount()==0){
                    Toast.makeText(ListActivity.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(rec.getCount()==0){
                    Toast.makeText(ListActivity.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id:"+res.getString(0)+"\n");
                    buffer.append("Name:"+res.getString(1)+"\n");
                    buffer.append("Age:"+res.getString(2)+"\n");
                    buffer.append("Gross Salary:"+res.getString(3)+"\n\n");
                }
                while (rec.moveToNext()){
                    buffer.append(""+rec.getString(0)+"\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(ListActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Employee Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}