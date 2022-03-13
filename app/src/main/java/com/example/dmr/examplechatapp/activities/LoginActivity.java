package com.example.dmr.examplechatapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.dmr.examplechatapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText email,password;
    MaterialButton signin;
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    signinUser();
                }
            }
        });
    }
    void signinUser(){
        auth.signInWithEmailAndPassword(
                email.getText().toString(),
                password.getText().toString())
                .addOnSuccessListener(
                        authResult -> startActivity(
                                new Intent(LoginActivity.this,MainActivity.class)))
                .addOnFailureListener(
                        e -> Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show());
    }
    boolean validate(){
        if (TextUtils.isEmpty(email.getText().toString())){
            email.requestFocus();
            email.setError("Please enter email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            email.requestFocus();
            email.setError("Please enter valid email");
        } else if (TextUtils.isEmpty(password.getText().toString())){
            password.setError("Please enter password");
            password.requestFocus();
        } else if (password.getText().toString().length()<8){
            password.setError("Password is too short");
            password.requestFocus();
            return false;
        }
        return true;
    }

    void init(){
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signin=findViewById(R.id.login);
        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
    }
}