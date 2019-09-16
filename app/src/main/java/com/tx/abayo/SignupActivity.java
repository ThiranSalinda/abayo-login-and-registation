package com.tx.abayo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    EditText password,email,name,pnum,location;
    Button btnsignup;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editText10);
        password = findViewById(R.id.editText8);
        name = findViewById(R.id.editText11);
        pnum = findViewById(R.id.editText12);
        location = findViewById(R.id.editText13);
        btnsignup = findViewById(R.id.button5);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String pwd = password.getText().toString();
                String name1 = name.getText().toString();
                String phoneno = pnum.getText().toString();
                String lock = location.getText().toString();
                if (pwd.isEmpty()){
                    password.setError("Please Enter Password!");
                    password.requestFocus();
                }else if (email1.isEmpty()){
                    email.setError("Please Enter E-mail!");
                    email.requestFocus();
                }else if (name1.isEmpty()){
                    name.setError("Please Enter Name!");
                    name.requestFocus();
                }else if (phoneno.isEmpty()){
                    pnum.setError("Please Enter Phone Number!");
                    pnum.requestFocus();
                }else if (email1.isEmpty() && pwd.isEmpty() && name1.isEmpty() && phoneno.isEmpty() && lock.isEmpty()){
                    Toast.makeText(SignupActivity.this,"Fields are Empty!",Toast.LENGTH_SHORT).show();
                }else if (!(email1.isEmpty()&& pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email1,pwd).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignupActivity.this,"Sign up Unsuccessful, Please Try again",Toast.LENGTH_SHORT).show();
                            }else{
                                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                            }
                        }
                    });
                }else{
                    Toast.makeText(SignupActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
