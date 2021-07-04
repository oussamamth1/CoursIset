package com.example.coursiset;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Build;
import android.os.Bundle;

import com.example.coursiset.data.coursdata;
import com.example.coursiset.model.Cours;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.motion.widget.Animatable;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.coursiset.databinding.ActivityMainActivity2DBinding;

import org.w3c.dom.Comment;

import java.util.ArrayList;

public class CoursDetail extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainActivity2DBinding binding;
private int coursid;
private Cours cours;
private ImageView imageCoursid;
private TextView CoursTitle;
private InputMethodManager manager;
private LinearLayout revealView;
private EditText comment;
private FloatingActionButton  buttonadd;
//coment
private ArrayList<String>comments;
private ArrayAdapter<String>arrayAdapterComent;
private ListView commentsList;
private boolean isEditTextVisible=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainActivity2DBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        setUpUI();
        setUpAdapter();
        LoadCours();
        Toast.makeText(CoursDetail.this,"cours id "+coursid,Toast.LENGTH_SHORT).show();
        //imageCoursid.setImageResource(R.drawable.course1);
    }

    private void setUpAdapter() {
        commentsList=(ListView) findViewById(R.id.DetailCommentsListView);
        comments=new ArrayList<>();

        arrayAdapterComent=new ArrayAdapter<String>(this,R.layout.comment_row,comments);
        commentsList.setAdapter(arrayAdapterComent);
    }

    private void LoadCours() {
        Bundle bundle=getIntent().getExtras();
        coursid=bundle.getInt("cours_id");
        cours=new coursdata().coursArrayList().get(coursid);
        imageCoursid.setImageResource(cours.getImageResourcid(this));
        CoursTitle.setText(cours.getcoursname());
    }
    private void setUpUI() {
        manager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imageCoursid= findViewById(R.id.Detailcoursimage);

        CoursTitle=findViewById(R.id.detailidcourstitle);
        comment=findViewById(R.id.edittextdetail);
        revealView=findViewById(R.id.relativeview);
        revealView.setVisibility(View.INVISIBLE);
        isEditTextVisible=false;
        buttonadd=findViewById(R.id.buttonadd);
        buttonadd.setOnClickListener(CoursDetail.this);
    }
    @Override
    public boolean onSupportNavigateUp() {
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonadd:
                if(!isEditTextVisible){
                    comment.requestFocus();
                    manager.showSoftInput(comment,InputMethodManager.SHOW_IMPLICIT);
                    revaleddittext(revealView);
                }else {hideTexxt(revealView);
                addToComnent(comment.getText().toString().trim());
                comment.setText("");
                manager.hideSoftInputFromWindow(comment.getWindowToken(),0);}
                break;
        }
    }

    private void addToComnent(String usercomment) {
comments.add(usercomment);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void hideTexxt(final LinearLayout revealView) {
        int cx=revealView.getRight() - 30;
        int cy=revealView.getBottom() - 60;
        int initailReduis=revealView.getWidth();
        Animator anim=ViewAnimationUtils.createCircularReveal(revealView,cx,cy,initailReduis,0f);
        revealView.setVisibility(View.VISIBLE);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                revealView.setVisibility(View.INVISIBLE);
            }
        });
       isEditTextVisible=false;
       anim.start();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void revaleddittext(LinearLayout revealView) {
        int cx=revealView.getRight() - 30;
        int cy=revealView.getBottom() - 60;
        int finalreduis=Math.max(revealView.getWidth(),revealView.getHeight());
        Animator anim=ViewAnimationUtils.createCircularReveal(revealView,cx,cy,0f,finalreduis);
        revealView.setVisibility(View.VISIBLE);
        isEditTextVisible=true;
        anim.start();
    }
}