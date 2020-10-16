package com.example.e_clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Clinicpatientslogin extends AppCompatActivity {
    EditText email_log,password_log;
    Button log_btn,btn_createaccount;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicpatientslogin);
        email_log = findViewById(R.id.email_login);
        password_log = findViewById(R.id.password_login);
        btn_createaccount=findViewById(R.id.btn_create);
        btn_createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });
        log_btn = findViewById(R.id.btnlogin);
        firebaseAuth = FirebaseAuth.getInstance();

        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email_log.getText().toString().isEmpty()||password_log.getText().toString().isEmpty()){
                    email_log.setError("requred");
                    password_log.setError("requred");
                }
                else{
                    firebaseAuth.signInWithEmailAndPassword(email_log.getText().toString(),password_log.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"successiful login",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),patientsmenu.class));
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
}
    protected void create(){
        Intent intent=new Intent(this,Clinicpatientsregister.class);
        startActivity(intent);
    }

    }



