package com.example.e_clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
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

public class registercaregiver extends AppCompatActivity {
    private EditText userEmail,userPassword,usercontact,usernames,userage,useridno;
    private DatabaseReference mdatabase;
    private Button btnRegisteruser;
    private ProgressBar bar;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private Dialog Progressdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registercaregiver);
        user = FirebaseAuth.getInstance().getCurrentUser();
        mdatabase = FirebaseDatabase.getInstance().getReference("caregivers");
        userEmail=findViewById(R.id.eemail);
        userPassword=findViewById(R.id.epassword);
        btnRegisteruser=findViewById(R.id.btn);
        firebaseAuth=FirebaseAuth.getInstance();
        usercontact = findViewById(R.id.cont);
        usernames=findViewById(R.id.names);
        userage=findViewById(R.id.age);
        useridno=findViewById(R.id.idno);
        btnRegisteruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startcaregiverregistration();

            }
        });

    }
    public void startcaregiverregistration(){
        String Email=userEmail.getText().toString();
        String Password=userPassword.getText().toString();
        String Contact=usercontact.getText().toString();
        String Names=usernames.getText().toString();
        String Age=userage.getText().toString();
        String IDNO=useridno.getText().toString();
        if (Email.isEmpty()||Password.isEmpty()||Contact.isEmpty()||Names.isEmpty()||Age.isEmpty()||IDNO.isEmpty()){
            userEmail.setError("Required");
            userPassword.setError("Requred");
            usercontact.setError("Requred");
            usernames.setError("Requred");
            userage.setError("Requred");
            useridno.setError("Requred");
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
                        Caregiver users = new Caregiver(usercontact.getText().toString(),usernames.getText().toString(),
                                userage.getText().toString(), useridno.getText().toString());
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

    }
}