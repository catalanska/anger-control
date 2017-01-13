package com.javierjv.angercontrol;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class Anger extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anger);

        startCountDown();

    }

    protected void startCountDown() {
        final Intent resultIntent = new Intent(this, Happy.class);
        final Context angerActivity = this;

        new CountDownTimer(300000, 1000) {
            TextView title =  (TextView) findViewById(R.id.main_message);
            String end_message_title = getString(R.string.end_message_title);
            String end_message = getString(R.string.end_message);

            public void onTick(long millisUntilFinished) {
                TextView countdown =  (TextView) findViewById(R.id.countdown);
                countdown.setText(""+String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onFinish() {
                title.setText(end_message);

                Notification.Builder builder = new Notification.Builder(angerActivity)
                        .setSmallIcon(R.drawable.caritas_1)
                        .setContentTitle(end_message_title)
                        .setContentText(end_message);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(angerActivity);
                stackBuilder.addParentStack(Happy.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                builder.setContentIntent(resultPendingIntent);

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = builder.build();
                manager.notify(0, notification);

                startActivity(resultIntent);

            }
        }.start();

    }

}

