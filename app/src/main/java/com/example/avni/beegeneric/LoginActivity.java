package com.example.avni.beegeneric;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText etUsername, etPassword;
    Button BtnLogin,BtnSignup, btnLForgotPassword;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername=findViewById(R.id.etLUsername);
        etPassword=findViewById(R.id.etLPassword);
        BtnLogin=findViewById(R.id.btnLLogin);
        BtnSignup=findViewById(R.id.btnLSignup);
        btnLForgotPassword = findViewById(R.id.btnLForgotpw);
        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username= etUsername.getText().toString();
                String Password= etPassword.getText().toString();
                if(!Username.equals("")||!Password.equals(""))
                    {
                        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        if (Username.matches(emailPattern) && Username.length() > 0) {
                            //flag = true;
                               mAuth.signInWithEmailAndPassword(Username, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                       // If sign in fails, display a message to the user. If sign in succeeds
                                       // the auth state listener will be notified and logic to handle the
                                       // signed in user can be handled in the listener.
                                       if (!task.isSuccessful()) {
                                           try {
                                               throw task.getException();
                                           } catch (FirebaseAuthInvalidCredentialsException e1) {
                                               Toast.makeText(LoginActivity.this, "Invalid Credentials...", Toast.LENGTH_SHORT).show();
                                           } catch (Exception e) {
                                               e.printStackTrace();
                                               Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                           }
                                       }else{
                                           Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                           startActivity(intent);
                                           finish();
                                       }

                                   }


                               });



                        } else {
                            //flag=false;
                            Toast.makeText(LoginActivity.this,"Please enter valid Email",Toast.LENGTH_SHORT).show();
                        }
                    }
                else {
                    Toast.makeText(LoginActivity.this, "fill all", Toast.LENGTH_SHORT).show();
                }
            }

        });

        BtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);

            }
        });

        btnLForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ResetPasswordActivity.class);
                startActivity(intent);

            }
        });

    }
}
