package com.example.lab5ex1;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBrowser = findViewById(R.id.button_browser);
        Button btnCall = findViewById(R.id.button_call);
        Button btnMessage = findViewById(R.id.button_message);

        btnBrowser.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com"));
            startActivity(browserIntent);
        });

        btnCall.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0712345678"));
            startActivity(callIntent);
        });

        btnMessage.setOnClickListener(v -> {
            Intent messageIntent = new Intent(Intent.ACTION_SEND);
            messageIntent.setType("text/plain");
            messageIntent.putExtra(Intent.EXTRA_TEXT, "Hello!");
            startActivity(messageIntent);
        });
    }
}
