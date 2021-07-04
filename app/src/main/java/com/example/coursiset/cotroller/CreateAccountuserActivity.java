package com.example.coursiset.cotroller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.coursiset.MainActivity;
import com.example.coursiset.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.installations.Utils;

public class CreateAccountuserActivity extends AppCompatActivity {
private EditText FirstName,LastName,Email,Password,Phone;
private Button CreatAccount;
private ProgressBar  Progres;
    FirebaseAuth Mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_accountuser);
        FirstName=findViewById(R.id.userfirstaccu);
        LastName=findViewById(R.id.userLasttaccu);
        Password=findViewById(R.id.userpassword);
        Email=findViewById(R.id.userMail);
        Progres=findViewById(R.id.progressBar);
        Phone=findViewById(R.id.Phone);
        CreatAccount=findViewById(R.id.buttonCreatUserAcc);
        Mauth=FirebaseAuth.getInstance();
       /* if(Mauth.getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            }*/
        CreatAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=Email.getText().toString().trim();
                String passwd=Password.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(passwd)){
                    Password.setError("Password is required");
                    return;
                }
                if(passwd.length() <6){
                    Password.setError("Password has to be more then 6 Carateur");
                    return;
                }
                Progres.setVisibility(View.VISIBLE);
                Mauth.createUserWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CreateAccountuserActivity.this,"User Created",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(CreateAccountuserActivity.this, MainActivity.class));
                        }
                        else{
                            Toast.makeText(CreateAccountuserActivity.this,"Errer " +passwd+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                //Toast.makeText(CreateAccountuserActivity.this,"itwork",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}