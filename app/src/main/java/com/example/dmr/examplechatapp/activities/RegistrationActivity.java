package com.example.dmr.examplechatapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dmr.examplechattapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class RegistrationActivity extends AppCompatActivity {

    TextInputEditText name, address, phone, city, email, pass, cPass;
    MaterialButton signup;
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();

    }
    void init(){
        name=findViewById(R.id.full_name);
        address=findViewById(R.id.address);
        phone=findViewById(R.id.contact);
        city=findViewById(R.id.city);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        cPass=findViewById(R.id.confirm_password);
        signup=findViewById(R.id.signup);
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
    }
}