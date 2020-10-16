package com.example.e_clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Adminlogin extends AppCompatActivity {
    private EditText admin_email,admin_password;
    private Button admin_login;
    private FirebaseAuth firebaseAuth;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
       admin_email = findViewById(R.id.loginemail);
        admin_password = findViewById(R.id.loginpassword);
        admin_login = findViewById(R.id.loginbtn);
        firebaseAuth = FirebaseAuth.getInstance();
        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (admin_email.getText().toString().isEmpty()||admin_password.getText().toString().isEmpty()){
                    admin_email.setError("requred!");
                    admin_password.setError("requred!");
                }
                else

                        firebaseAuth.signInWithEmailAndPassword(admin_email.getText().toString(),admin_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"successiful login",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),homeadmin.class));
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }

}