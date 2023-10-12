package com.example.payrollmanagementsystemminorproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        TextView username=findViewById(R.id.username);
        TextView password=findViewById(R.id.password);
        MaterialButton loginbtn=findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isValidate=checkValidation();
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("123"))
                {

                    Toast.makeText(AdminActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminActivity.this, AdminFrameActivity.class);
                    startActivity(intent);
                }else
                    Toast.makeText(AdminActivity.this, "LOGIN FAILED!", Toast.LENGTH_SHORT).show();
            }
            private Boolean checkValidation(){
                if(username.length()==0){
                    username.setError("Please Enter Username");
                    return false;
                }
                if (password.length()==0){
                    password.setError("Please Enter Password");
                    return false;
                }
                return null;
            }
        });

    }
}