package com.example.lab5ex4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class User1 extends AppCompatActivity {

    private EditText editTextMessage;
    private Button buttonSend;
    private TextView textViewReceivedMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);

        editTextMessage = findViewById(R.id.editTextMessageUser1);
        buttonSend = findViewById(R.id.buttonSendUser1);
        textViewReceivedMessage = findViewById(R.id.textViewReceivedMessageUser1);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("replyMessage")) {
            String replyMessage = intent.getStringExtra("replyMessage");
            textViewReceivedMessage.setText("User2: " + replyMessage);
        }

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMessage.getText().toString();
                Intent intent = new Intent(User1.this, User2.class);
                intent.putExtra("message", message);
                startActivity(intent);
            }
        });
    }
}
