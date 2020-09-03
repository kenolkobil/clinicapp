package com.example.e_clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registerpatient extends AppCompatActivity {
    EditText r1,r2,r3,r4,r5,r6,r7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpatient);
        r1=(EditText) findViewById(R.id.r1);
        r2=(EditText) findViewById(R.id.r2);
        r3=(EditText) findViewById(R.id.r3);
        r4=(EditText) findViewById(R.id.r4);
        r5=(EditText) findViewById(R.id.r5);
        r6=(EditText) findViewById(R.id.r6);
        r7=(EditText) findViewById(R.id.r7);
    }
    public void addRecord(View view){
        DBmanager db=new DBmanager(this);
        String res=db.addRecord(r1.getText().toString(),r2.getText().toString(),r3.getText().toString(),r4.getText().toString(),r5.getText().toString(),r6.getText().toString(),r7.getText().toString());
        Toast.makeText(this,res,Toast.LENGTH_LONG).show();
        r1.setText("");
        r2.setText("");
        r3.setText("");
        r4.setText("");
        r5.setText("");
        r6.setText("");
        r7.setText("");


    }
}