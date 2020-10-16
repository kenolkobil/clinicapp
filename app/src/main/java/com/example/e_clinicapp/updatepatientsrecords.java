package com.example.e_clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updatepatientsrecords extends AppCompatActivity {
    EditText jina, miaka,type,statasi;
    Button update;
    FirebaseDatabase rootnode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepatientsrecords);
        jina=findViewById(R.id.names);
       miaka=findViewById(R.id.age);
        type=findViewById(R.id.gender);
       statasi=findViewById(R.id.status);
       update=findViewById(R.id.updates);


    }
}