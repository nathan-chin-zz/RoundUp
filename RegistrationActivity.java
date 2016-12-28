package com.example.vikasperaka.firebasetestlogin1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    //different fields in design. Subject to change based on graphics.
    private EditText txtEmailAdress;
    private EditText txtPassword;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        txtEmailAdress = (EditText)findViewById(R.id.txtEmailRegistration);
        txtPassword = (EditText)findViewById(R.id.txtPasswordRegistration);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    /*
    Registration button is clicked after the user has filled in the fields to register
     */
    public void btnRegistrationUser_Click(View view){
        //creates pop up to show loading while the system is registering user
        final ProgressDialog progressDialog = ProgressDialog.show(RegistrationActivity.this, "Please Wait...", "Processing", true);
        String email = txtEmailAdress.getText().toString();
        String password = txtPassword.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                //notify user if successful registration. Takes to login screen.
                if (task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                else{
                    //print out error. User won't know what this means. Need to translate to show what is wrong.
                    Log.e("ERROR", task.getException().toString());
                    Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
