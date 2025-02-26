package com.example.lab3ex3;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private LinearLayout background;
    private Button toggleLightButton, autoModeButton, customColorButton;
    private boolean isLightOn = false;
    private boolean isAutoMode = false;
    private static final String CHANNEL_ID = "smart_light_notifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = findViewById(R.id.background);
        toggleLightButton = findViewById(R.id.toggleLightButton);
        autoModeButton = findViewById(R.id.autoModeButton);
        customColorButton = findViewById(R.id.customColorButton);

        createNotificationChannel();

        toggleLightButton.setOnClickListener(view -> toggleLight());
        autoModeButton.setOnClickListener(view -> toggleAutoMode());
        customColorButton.setOnClickListener(view -> showColorSelection());
    }

    private void toggleLight() {
        isLightOn = !isLightOn;
        updateBackground();
    }

    private void toggleAutoMode() {
        isAutoMode = !isAutoMode;
        if (isAutoMode) {
            autoAdjustLight();
        }
    }

    private void autoAdjustLight() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= 7 && hour < 18) {
            background.setBackgroundColor(Color.WHITE);
            if (isLightOn) {
                sendNotification("Good morning! Do you need more light?");
            }
        } else if (hour >= 18 && hour < 22) {
            background.setBackgroundColor(Color.parseColor("#FFA500")); // Orange
        } else {
            isLightOn = false;
            updateBackground();
            sendNotification("Good night! The light has turned off automatically.");
        }
    }

    private void updateBackground() {
        if (isLightOn) {
            background.setBackgroundColor(Color.YELLOW);
        } else {
            background.setBackgroundColor(Color.DKGRAY);
        }
    }

    private void showColorSelection() {
        final String[] colors = {"White", "Blue", "Red", "Green"};
        final int[] colorValues = {Color.WHITE, Color.BLUE, Color.RED, Color.GREEN};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a light color")
                .setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        background.setBackgroundColor(colorValues[which]);
                    }
                });
        builder.create().show();
    }

    private void sendNotification(String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_light)
                .setContentTitle("Smart Light Control")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Smart Light Notifications";
            String description = "Notifications for automatic light changes";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
