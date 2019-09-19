package com.example.avni.beegeneric;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    TextInputEditText etUsername, etPassword, etFirstName, etLastName;
    Button BtnBack, BtnSignup;
    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etUsername = findViewById(R.id.etSUsername);
        etPassword = findViewById(R.id.etSPassword);

        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        //Get Firebase auth instance
        //mFirebaseDatabase = FirebaseAuth.getInstance();

        // get reference to 'users' node
        //mFirebaseDatabase = mFirebaseInstance.getReference("UserData");

        BtnBack = findViewById(R.id.btnSBack);
        BtnSignup = findViewById(R.id.btnSSignup);


        BtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String FirstName = etFirstName.getText().toString();
                final String LastName = etLastName.getText().toString();
                final String Username = etUsername.getText().toString();
                final String Password = etPassword.getText().toString();

                if (FirstName.equals("") || LastName.equals("") || Username.equals("") || Password.equals("")) {
                    Toast.makeText(SignupActivity.this, "Fill All", Toast.LENGTH_SHORT).show();
                } else {
                    //flag=true;
                    mAuth.createUserWithEmailAndPassword(Username, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    Toast.makeText(SignupActivity.this, "Weak Password", Toast.LENGTH_SHORT).show();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    Toast.makeText(SignupActivity.this, "Invalid email id!", Toast.LENGTH_SHORT).show();
                                } catch (FirebaseAuthUserCollisionException e) {
                                    Toast.makeText(SignupActivity.this, "User already exist!", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    Toast.makeText(SignupActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                    Log.d("tag","Error+++"+e.toString() );
                                }
                            } else {
                                UserInfo userInfo = new UserInfo();
                                userInfo.setFirstname(FirstName);
                                userInfo.setLastname(LastName);
                                userInfo.setUsername(Username);
                                userInfo.setPassword(Password);

                                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                                startActivity(intent);

                            }
                        }
                    });
                }
            }
        });


        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignupActivity.super.onBackPressed();
            }
        });

    }
}
