package com.example.coursiset.cotroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

import com.example.coursiset.R;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountuserActivity extends AppCompatActivity {
private EditText FirstName,LastName,Email,Pasword,Phone;
private Button CreatAccount;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_accountuser);
        FirstName=findViewById(R.id.userfirstaccu);
        LastName=findViewById(R.id.userLasttaccu);
        Email=findViewById(R.id.userMail);
        Phone=findViewById(R.id.Phone);
        CreatAccount=findViewById(R.id.buttonCreatUserAcc);
        auth=FirebaseAuth.getInstance();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}