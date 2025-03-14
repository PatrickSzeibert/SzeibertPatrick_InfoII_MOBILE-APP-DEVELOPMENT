package com.example.lab5ex4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class User2 extends AppCompatActivity {

    private TextView textViewReceivedMessage;
    private EditText editTextMessage;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);

        textViewReceivedMessage = findViewById(R.id.textViewReceivedMessage);
        editTextMessage = findViewById(R.id.editTextMessageUser2);
        buttonSend = findViewById(R.id.buttonSendUser2);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("message")) {
            String message = intent.getStringExtra("message");
            textViewReceivedMessage.setText("User1: " + message);
        }

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String replyMessage = editTextMessage.getText().toString();
                Intent intent = new Intent(User2.this, User1.class);
                intent.putExtra("replyMessage", replyMessage);
                startActivity(intent);
            }
        });
    }
}
