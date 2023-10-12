package com.example.payrollmanagementsystemminorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class AddActivity extends AppCompatActivity {

    MaterialButton add;
    DBHelper DB;
    EditText id,name,age,gross;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        id=findViewById(R.id.empid);
        name=findViewById(R.id.empname);
        gross=findViewById(R.id.empgross);
        add=findViewById(R.id.btnadd);
        DB=new DBHelper(this);
        age=findViewById(R.id.empage);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isValidate = checkValidation();
                if (isValidate) {
                    Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                    startActivity(intent);
                    finish();
                }
                String idTXT = id.getText().toString();
                String nameTXT = name.getText().toString();
                String ageTXT = age.getText().toString();
                String grossTXT = gross.getText().toString();

                Boolean checkinsertdata = DB.insertempdata(idTXT, nameTXT, ageTXT, grossTXT);
                if (checkinsertdata == true) {
                    Toast.makeText(AddActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }

            private Boolean checkValidation() {
                if (id.length() == 0) {
                    id.setError("Please Enter Employee Id");
                    return false;
                }
                if (name.length() == 0) {
                    name.setError("Please Enter Employee Name");
                    return false;
                }
                if (age.length() == 0) {
                    age.setError("Please Enter Employee Age");
                    return false;
                }
                if (gross.length() == 0) {
                    gross.setError("Please Enter Gross Salary");
                }
                return true;
            }
        });

        }

    }