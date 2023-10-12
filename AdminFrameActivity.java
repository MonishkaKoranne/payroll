package com.example.payrollmanagementsystemminorproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminFrameActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView add,edit,calculate,list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_frame);
        add=findViewById(R.id.card_add);
        edit=findViewById(R.id.card_edit);
        calculate=findViewById(R.id.card_calculate);
        list=findViewById(R.id.card_list);
        add.setOnClickListener(this);
        edit.setOnClickListener(this);
        calculate.setOnClickListener(this);
        list.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        //use switch case
        if (v.getId() == R.id.card_add) {
            i = new Intent(this, AddActivity.class);
            startActivity(i);
        } else if (v.getId()==R.id.card_calculate) {
            i=new Intent(this,CalculateActivity.class);
            startActivity(i);
        }else if (v.getId() == R.id.card_edit) {
            i = new Intent(this,EditActivity.class);
            startActivity(i);

        } else if (v.getId()==R.id.card_list) {
            i=new Intent(this,ListActivity.class);
            startActivity(i);

        } else {

        }
    }
}