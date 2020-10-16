package com.example.e_clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Clinicpatientsregister extends AppCompatActivity {

private EditText userEmail,userPassword,usercontact,usernames,userage,userstatus;
private DatabaseReference mdatabase;
private Button btnRegisteruser;
private ProgressBar bar;
private FirebaseAuth firebaseAuth;
private FirebaseUser user;
private Dialog Progressdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicpatientsregister);

        user = FirebaseAuth.getInstance().getCurrentUser();
        mdatabase = FirebaseDatabase.getInstance().getReference("Users");
      userEmail=findViewById(R.id.eemail);
       userPassword=findViewById(R.id.epassword);
        btnRegisteruser=findViewById(R.id.btn);
        firebaseAuth=FirebaseAuth.getInstance();
        usercontact = findViewById(R.id.cont);
        usernames=findViewById(R.id.names);
        userage=findViewById(R.id.age);
        userstatus=findViewById(R.id.status);

       btnRegisteruser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startuserregistration();

            }
        });

            }
            public void startuserregistration(){
        String Email=userEmail.getText().toString();
        String Password=userPassword.getText().toString();
        String Contact=usercontact.getText().toString();
        String Names=usernames.getText().toString();
        String Age=userage.getText().toString();
        String Status=userstatus.getText().toString();

        if (Email.isEmpty()||Password.isEmpty()||Contact.isEmpty()||Names.isEmpty()||Age.isEmpty()||Status.isEmpty()){
            userEmail.setError("Required");
            userPassword.setError("Requred");
            usercontact.setError("Requred");
            usernames.setError("Requred");
            userage.setError("Requred");
            userstatus.setError("Requred");
        }
               else if (Password.isEmpty()){
                    userPassword.setError("Required");
               }
                    else if (Password.length()<6){
                        Toast.makeText(this,"password is too weak",Toast.LENGTH_SHORT).show();
                }

        else{
            firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        UserHelperClass users = new UserHelperClass(usercontact.getText().toString(),usernames.getText().toString(),userage.getText().toString(),userstatus.getText().toString(),user.getUid());
                        mdatabase.child(user.getUid()).setValue(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"successful storage",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        });
                        startActivity(new Intent(getApplicationContext(),Clinicpatientslogin.class));
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });



            }
}}