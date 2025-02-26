package com.example.lab3ex2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private TextView textViewCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCounter = findViewById(R.id.textViewCounter);
    }

    public void showToast(View view) {
        Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show();
    }

    public void incrementCounter(View view) {
        count++;
        textViewCounter.setText(String.valueOf(count));
    }
}
