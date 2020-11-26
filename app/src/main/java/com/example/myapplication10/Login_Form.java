package com.example.myapplication10;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Form extends AppCompatActivity {
    TextInputLayout txtEmail,txtPassward;
    Button btn_login, btn_signup;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
    //    getSupportActionBar().setTitle("Login Form");
        btn_signup = findViewById(R.id.button2);
        txtEmail=findViewById(R.id.txtEmail);
        txtPassward=findViewById(R.id.txtPassward);
        btn_login=(Button)findViewById(R.id.btn_login);
        firebaseAuth=FirebaseAuth.getInstance();

            btn_signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login_Form.this,Signup_Form.class);
                    startActivity(intent);




                }
            });





        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=txtEmail.getEditText().getText().toString().trim();
                String Passward=txtPassward.getEditText().getText().toString().trim();
                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(Login_Form.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Passward)){
                    Toast.makeText(Login_Form.this,"Please Enter Passward",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(txtPassward.getEditText().getText().length()<6){
                    Toast.makeText(Login_Form.this,"Passward too short",Toast.LENGTH_SHORT).show();

                }
                firebaseAuth.createUserWithEmailAndPassword(Email, Passward)
                        .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent obj = new Intent(Login_Form.this, MainActivity.class);
                                    startActivity(obj);

                                } else {
                                    Toast.makeText(Login_Form.this,"Login Failed OR User Not Available",Toast.LENGTH_SHORT).show();


                                }

                                // ...
                            }
                        });

                }
        });


    }




}