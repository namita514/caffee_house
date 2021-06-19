package com.example.caffe_house;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sign_up extends AppCompatActivity {
    FirebaseAuth fAuth;
    EditText Email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fAuth=FirebaseAuth.getInstance();
        Email=(EditText)findViewById(R.id.Email);
        pass=(EditText)findViewById(R.id.pass);
    }
    public void takefoog(View view) {

        String email=Email.getText().toString().trim();
        String password=pass.getText().toString();


        if(TextUtils.isEmpty(email)){
            Email.setError("Email is required");
            return;
        }
        if(TextUtils.isEmpty(password)){
            pass.setError("password is required");
            return;
        }
        if(password.length()<6)
        {
            pass.setError("more the 6 characters are required");
            return;
        }
        signIn(email,password);
    }

    private void signIn(String email, String password){
        fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information

                    FirebaseUser user = fAuth.getCurrentUser();
                    updateUI(user);}
                else {
                    Toast.makeText(sign_up.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }

        });
    }
    private void updateUI(FirebaseUser user) {
        if(user != null){
            Intent intent = new Intent(sign_up.this,menu.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Entry Not Successful", Toast.LENGTH_SHORT).show();
        }
    }
}