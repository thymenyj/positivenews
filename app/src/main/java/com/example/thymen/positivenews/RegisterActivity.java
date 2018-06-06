package com.example.thymen.positivenews;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    public EditText registerName, registerEmail, registerPassword;
    public TextView registerBack;
    public ImageView registerRegister;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;

    public String userName, userEmail, userPassword;
    public ArrayList<String> preferences, savedArticles;
    public String validateName, validateEmail, validatePassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        database = FirebaseDatabase.getInstance().getReference();

        registerName = findViewById(R.id.registerName);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);

        registerRegister = findViewById(R.id.registerRegister);
        registerBack = findViewById(R.id.registerBack);

        preferences = new ArrayList<>();
        savedArticles = new ArrayList<>();


        firebaseAuth = FirebaseAuth.getInstance();

        registerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    userName = registerName.getText().toString().trim();
                    userEmail = registerEmail.getText().toString().trim();
                    userPassword = registerPassword.getText().toString().trim();


                    firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "registerd successful", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String userId = user.getUid();
                                User newUser = new User();
                                newUser.setName(userName);
                                newUser.setEmail(userEmail);
                                newUser.setPassword(userPassword);
                                newUser.setPreferences(preferences);
                                newUser.setSavedArticles(savedArticles);
                                database.child("users").child(userId).setValue(newUser);
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        registerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validate() {
        Boolean result = false;

        validateName = registerName.getText().toString();
        validateEmail = registerEmail.getText().toString();
        validatePassword = registerPassword.getText().toString();

        if (validateName.isEmpty() || validateEmail.isEmpty() || validatePassword.isEmpty()) {
            Toast.makeText(this, "please enter all details", Toast.LENGTH_SHORT).show();
        }
        else if(validatePassword.length() < 6) {
            Toast.makeText(this, "password requires 6 characters", Toast.LENGTH_SHORT).show();
        }
        else {
            result = true;
        }

        return result;
    }

}
