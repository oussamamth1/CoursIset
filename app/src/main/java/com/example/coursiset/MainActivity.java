package com.example.coursiset;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.coursiset.cotroller.CreateAccountuserActivity;
import com.example.coursiset.cotroller.DashborActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.coursiset.databinding.ActivityMainBinding;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private Button Login;
private EditText Email;
FirebaseAuth mAuth;

private ProgressBar LoginProg;
private EditText Password;
private TextView creatAccount;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mAuth = FirebaseAuth.getInstance();
       LoginProg=findViewById(R.id.LoginProgr);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
Login=findViewById(R.id.loginbutton);
creatAccount=findViewById(R.id.singin);
        Email=findViewById(R.id.Login);
        Password=findViewById(R.id.Password);
        setSupportActionBar(binding.toolbar);


Login.setOnClickListener(new View.OnClickListener() {
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
       // LoginProg.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
                if(task.isSuccessful()){
                   startActivity(new Intent(getApplicationContext(),DashborActivity.class));
                    Toast.makeText(MainActivity.this,"wellcom" + email,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Errer try to sign in " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
});
creatAccount.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,CreateAccountuserActivity.class));
        //finish();
        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
});
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                       .setAction("Action", null).show();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
   /* private void updateUI(FirebaseUser currentUser) {


        if(currentUser != null){

            startActivity(new Intent(MainActivity.this,DashborActivity.class));
        }

    }*/
    public void Login(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) { showDialog();}
        else if (Email.getText().toString().length() == 0){   Email.setError("Email is required!");}
        else if (Password.getText().toString().length() == 0) {   Password.setError("Password is required!");}

        else {

          /*  mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    });
        }*/}
    }
    void showDialog() {

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.alert_dialog, null);

       // Button acceptButton = view.findViewById(R.id.acceptButton);
       // Button cancelButton = view.findViewById(R.id.cancelButton);

     /*   acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //  Log.e(TAG, "onClick: cancel button" );
            }
        });**/

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
        alertDialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_toggel) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
      return true;
    }
}