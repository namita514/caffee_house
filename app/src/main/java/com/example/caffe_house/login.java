package com.example.caffe_house;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class login extends AppCompatActivity {
    private static final int RC_SIGN_IN = 100;
    TextView g_sign_in;
    TextView newaccount,signin;
    EditText Email;
    EditText pass;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Intent intent=new Intent(login.this,menu.class);
            startActivity(intent);}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
g_sign_in=findViewById(R.id.g_sign_in);
        signin=findViewById(R.id.sign_in);
        Email=(EditText)findViewById(R.id.Email);
        pass=(EditText)findViewById(R.id.pass);
        newaccount=findViewById(R.id.new_account);
        mAuth=FirebaseAuth.getInstance();
        createrequest();
        g_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        newaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,sign_up.class);
                startActivity(intent);
            }
        });
    }

    private void createrequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            updateUI(null);
                        }
                    }
                });
    }
    public void signin(View view) {
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
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(login.this,"logged in successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login.this,MainActivity.class));
                }
                else{
                    Toast.makeText(login.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void forgot(View view) {
        EditText resetMail=new EditText(view.getContext());
        AlertDialog.Builder passwordresetdialog=new AlertDialog.Builder(view.getContext());
        passwordresetdialog.setTitle("Reset password?");
        passwordresetdialog.setMessage("enter your email to recieve reset link");
        passwordresetdialog.setView(resetMail);
        passwordresetdialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String mail=resetMail.getText().toString();
                mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(login.this,"reset link has been set to tour email",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        Toast.makeText(login.this,"reset link has not sent"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        passwordresetdialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        passwordresetdialog.create().show();
    }
    private void updateUI(FirebaseUser user) {
        if(user != null){
            Intent intent = new Intent(login.this,menu.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Entry Not Successful", Toast.LENGTH_SHORT).show();
        }
    }
}