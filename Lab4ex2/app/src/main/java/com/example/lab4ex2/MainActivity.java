package com.example.lab4ex2;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new IntroFragment());

        Button btnChapter1 = findViewById(R.id.btn_chapter1);
        Button btnChapter2 = findViewById(R.id.btn_chapter2);
        Button btnChapter3 = findViewById(R.id.btn_chapter3);
        Button btnChapter4 = findViewById(R.id.btn_chapter4);
        Button btnIntro = findViewById(R.id.btn_intro);

        btnChapter1.setOnClickListener(view -> loadFragment(new Chapter1Fragment()));
        btnChapter2.setOnClickListener(view -> loadFragment(new Chapter2Fragment()));
        btnChapter3.setOnClickListener(view -> loadFragment(new Chapter3Fragment()));
        btnChapter4.setOnClickListener(view -> loadFragment(new Chapter4Fragment()));
        btnIntro.setOnClickListener(view -> loadFragment(new IntroFragment()));
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
