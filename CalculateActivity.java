package com.example.payrollmanagementsystemminorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class CalculateActivity extends AppCompatActivity {
    DBHelper DB;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        EditText id,name,gross,age;
        TextView basic;
        final int[] hra = new int[1];
        final int[] da = new int[1];
        final int[] other = new int[1];
        MaterialButton calculate,search,delete;
        id=findViewById(R.id.empid);
        name=findViewById(R.id.empname);
        DB=new DBHelper(this);
        delete=findViewById(R.id.btndelete2);
        basic=findViewById(R.id.basic);
        age=findViewById(R.id.empage);
        gross=findViewById(R.id.gross);
        search=findViewById(R.id.btnsearch);
        calculate=findViewById(R.id.btncalculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                    startActivity(intent);
                    finish();


                Integer gr = Integer.parseInt(gross.getText().toString());
                int b;

                hra[0] = 2000;
                da[0] = 2000;
                other[0] = 3000;
                b = gr - (hra[0] + da[0] + other[0]);
                basic.setText("Basic Salary:" + Integer.valueOf((int) b));


                String basicTXT = basic.getText().toString();

                Boolean checkinsert = DB.insert(basicTXT);
                if (checkinsert == true) {
                    Toast.makeText(CalculateActivity.this, "Calculated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CalculateActivity.this, "Not Calculated", Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String salaryTXT=basic.getText().toString();

                Boolean checkdelete=DB.delete(salaryTXT);
                if (checkdelete==true){
                    Toast.makeText(CalculateActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CalculateActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idTXT=id.getText().toString();
                if (idTXT.trim().length()>0){
                    Cursor search=DB.searchdata(idTXT);
                    if (search!=null){
                        if (search.getCount()>0){
                            if (search.moveToFirst()){
                                name.setText(search.getString(1));
                                age.setText(search.getString(2));
                                gross.setText(search.getString(3));
                            }else{
                                Toast.makeText(getApplicationContext(),"Data not found behind this id",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"Data not found behind this id",Toast.LENGTH_SHORT).show();
                                }
                    }else {
                                Toast.makeText(getApplicationContext(), "Data not found behind this id", Toast.LENGTH_SHORT).show();
                            }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Enter Id then click search",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
    }