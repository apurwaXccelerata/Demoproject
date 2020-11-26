package com.example.myapplication10;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_Form extends AppCompatActivity {
    TextInputLayout txt_fullName, txt_username, txt_email, txt_passward, txt_passward1;
    Button btn_register;
    RadioButton radioGenderMale, radioGenderFemale;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
       // getSupportActionBar().setTitle("Signup Form");
        txt_fullName = findViewById(R.id.txt_full_name);
        txt_username =  findViewById(R.id.txt_username);
        txt_email = findViewById(R.id.txt_email);
        txt_passward =  findViewById(R.id.txt_passward);
        txt_passward1 = findViewById(R.id.txt_passward1);
        btn_register = (Button) findViewById(R.id.btn_register);
        radioGenderFemale = (RadioButton) findViewById(R.id.radio_male);
        radioGenderFemale = (RadioButton) findViewById(R.id.radio_female);
        databaseReference = FirebaseDatabase.getInstance().getReference("Student");
        firebaseAuth = FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Gender = "";
                String FullName = txt_fullName.getEditText().getText().toString().trim();
                String Username = txt_username.getEditText().getText().toString().trim();
                String Email = txt_email.getEditText().getText().toString().trim();
                String Passward = txt_passward.getEditText().getText().toString().trim();
                String ConfirmPassward = txt_passward1.getEditText().getText().toString().trim();
                if (radioGenderMale.isChecked()) {
                    Gender = "Male";
                }
                if (radioGenderFemale.isChecked()) {
                    Gender = "Female";
                }
                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(Signup_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(Username)) {
                    Toast.makeText(Signup_Form.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(FullName)) {
                    Toast.makeText(Signup_Form.this, "Please Enter FullName", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (TextUtils.isEmpty(Passward)) {
                    Toast.makeText(Signup_Form.this, "Please Enter Passward", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(ConfirmPassward)) {
                    Toast.makeText(Signup_Form.this, "Please Enter ConfirmPassward", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txt_passward.getEditText().length() < 6) {
                    Toast.makeText(Signup_Form.this, "Passward too short", Toast.LENGTH_SHORT).show();
                }

                if(Passward.equals(ConfirmPassward)){
                    firebaseAuth.createUserWithEmailAndPassword(Email, Passward)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(Signup_Form.this,MainActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(Signup_Form.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                       ;
                                    } else {
                                        Toast.makeText(Signup_Form.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }


                }

        });


    }
    }