package com.example.expandablenotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String CHANNEL_ID = "personal_notifications";
    public final static int NOTIFICATION_ID = 001;
    public static final String TXT_REPLY = "text_reply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayNotification(View view) {

        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms_notification);
        builder.setContentTitle("Simple Notification");
        builder.setContentText("This is my first notification");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        /*
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.sample);
        builder.setLargeIcon(bitmap);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null));

         */

        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.notification_text)));


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());





    }

    private void createNotificationChannel()
    {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {

            CharSequence name = "Personal Notification";
            String description = "Include all the personal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        }

    }

}
