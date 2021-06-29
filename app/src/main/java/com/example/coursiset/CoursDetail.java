package com.example.coursiset;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.coursiset.databinding.ActivityMainActivity2DBinding;

public class CoursDetail extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainActivity2DBinding binding;
private int coursid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainActivity2DBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        Bundle bundle=getIntent().getExtras();
        coursid=bundle.getInt("cours_id");
        Toast.makeText(CoursDetail.this,"cours id "+coursid,Toast.LENGTH_SHORT).show();


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return true;
    }
}