package com.example.dmr.examplechatapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;


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
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    signUpUser();
                }
            }
        });
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

    boolean validate(){
        if(name.getText().toString().isEmpty()){
            name.setError("Please enter your name");
            name.requestFocus();
            return false;
        } else if (phone.getText().toString().isEmpty()){
            phone.setError("Please enter your Phone number");
            phone.requestFocus();
            return false;
        } else if (address.getText().toString().isEmpty()){
            address.setError("Please enter your Address");
            address.requestFocus();
            return false;
        } else if (city.getText().toString().isEmpty()){
            city.setError("Please enter your City");
            city.requestFocus();
            return false;
        } else if (email.getText().toString().isEmpty()){
            email.setError("Please enter your Email");
            email.requestFocus();
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            email.setError("Please enter correct Email");
            email.requestFocus();
            return false;
        } else if (!pass.getText().toString().equals(cPass.getText().toString())){
            pass.setError("Password does not match");
            pass.requestFocus();
            return false;
        } else if (pass.getText().toString().length()<8){
            pass.setError("Password id too short");
            pass.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    void signUpUser(){
        auth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Map<String,Object> map = new HashMap<>();
                map.put("Name",name.getText().toString());
                map.put("Contact No.", phone.getText().toString());
                map.put("Address",address.getText().toString());
                map.put("City",city.getText().toString());
                map.put("email",email.getText().toString());
                map.put("password",pass.getText().toString());
                firestore.collection("Users").document(email.getText().toString()).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent intent=new Intent(RegistrationActivity.this,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrationActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }
}