package com.example.payrollmanagementsystemminorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class EditActivity extends AppCompatActivity {
    MaterialButton update,delete;

    DBHelper DB;
    EditText id,name,age,gross;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        id=findViewById(R.id.empid);
        name=findViewById(R.id.empname);
        update=findViewById(R.id.btnupdate);
        delete=findViewById(R.id.btndelete);
        DB=new DBHelper(this);
        age=findViewById(R.id.empage);
        gross=findViewById(R.id.gross);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isValidate = checkValidation();
                if (isValidate) {
                    Intent intent = new Intent(getApplicationContext(), CalculateActivity.class);
                    startActivity(intent);
                    finish();
                }
                String idTXT=id.getText().toString();
                String nameTXT=name.getText().toString();
                String ageTXT=age.getText().toString();
                String grossTXT=gross.getText().toString();


                Boolean checkupdatedata=DB.updateempdata(idTXT,nameTXT,ageTXT,grossTXT);
                if (checkupdatedata==true){
                    Toast.makeText(EditActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditActivity.this,"Data Not Updated",Toast.LENGTH_SHORT).show();
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
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isValidate = checkValidation();
                if (isValidate) {
                    Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                    startActivity(intent);
                    finish();
                }
                String idTXT=id.getText().toString();
                Boolean checkdeletedata=DB.deleteempdata(idTXT);
                if (checkdeletedata==true){
                    Toast.makeText(EditActivity.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditActivity.this,"Data Not Deleted",Toast.LENGTH_SHORT).show();
                }
            }
            private Boolean checkValidation() {
                if (id.length() == 0) {
                    id.setError("Please Enter Employee Id");
                    return false;
                }
                return true;
            }


        });

    }
}