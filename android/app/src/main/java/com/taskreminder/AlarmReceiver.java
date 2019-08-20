package com.taskreminder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {

    private AppNotificationManager mNotificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        mNotificationManager = new AppNotificationManager(context);

//        final AppNotificationManager notificationManager = new AppNotificationManager(context);
//        notificationManager.hideNotification(context);

//        Toast.makeText(context,"Times up",Toast.LENGTH_LONG).show();
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context, "")
//                .setContentTitle("Alarm Notify")
//                .setContentText("This is Alarm Notification")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(0,builder.build());

//        if (!isNetworkAvailable(context)) {
//            Notification(context, "Wifi Connection Off");
//
//        } else {
//            Notification(context, "Wifi Connection On");
//        }

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//
//        builder.setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Alarm actived!")
//                .setContentText("THIS IS MY ALARM")
//                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
//                .setContentInfo("Info");
//
//        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(1,builder.build());

//        Bitmap alarm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_action_alarms);
//
//        NotificationCompat.Builder builder =
//                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
//                        .setSmallIcon(R.drawable.ic_action_alarms)
//                        .setContentTitle("Notifications Example")
//                        .setContentText("This is a test notification");
//
//        Intent notificationIntent = new Intent(context, MainActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(contentIntent);
//
//        // Add as notification
//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(0, builder.build());

        // prepare intent which is triggered if the
// notification is selected

//        Intent intent = new Intent(this, AlarmReceiverReceiver.class);
//// use System.currentTimeMillis() to have a unique ID for the pending intent
//        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

// build notification
// the addAction re-use the same intent to keep the example short
//        Notification n  = new Notification.Builder(context)
//                .setContentTitle("New mail from " + "test@gmail.com")
//                .setContentText("Subject")
//                .setSmallIcon(R.drawable.ic_action_alarms)
////                .setContentIntent(pIntent)
//                .setAutoCancel(true)
////                .addAction(R.drawable.ic_action_alarms, "Call", pIntent)
////                .addAction(R.drawable.ic_action_alarms, "More", pIntent)
////                .addAction(R.drawable.ic_action_alarms, "And more", pIntent)
//                .build();
//
//
//        NotificationManager notificationManager =
//                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0, n);

//        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
//                .setSmallIcon(R.drawable.ic_action_alarms)
//                .setContentTitle("My notification")
//                .setContentText("Much longer text that cannot fit one line...")
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText("Much longer text that cannot fit one line..."))
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

//        final Notification notification = new Notification.Builder(context)
//                /* Make app open when you click on the notification. */
//                .setContentIntent(PendingIntent.getActivity(
//                        context,
//                        0,
//                        new Intent(context, MainActivity.class),
//                        PendingIntent.FLAG_CANCEL_CURRENT))
//                .setContentTitle("title")
//                .setAutoCancel(true)
//                .setContentText(String.format("id = %d", 0))
//                // Starting on Android 5, only the alpha channel of the image matters.
//                // https://stackoverflow.com/a/35278871/895245
//                // `android.R.drawable` resources all seem suitable.
//                .setSmallIcon(android.R.drawable.star_on)
//                // Color of the background on which the alpha image wil drawn white.
//                //.setColor(Color.RED)
//                .build();
//        final NotificationManager notificationManager =
//                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, notification);

//        Bitmap alarm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_action_alarms);
//
//        Notification noti = new Notification.Builder(context)
//                .setContentTitle("New mail from Didin")
//                .setContentText("AW AW AW")
//                .setSmallIcon(R.drawable.ic_bookmark_white_24dp)
//                .setLargeIcon(alarm)
//                .build();

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//
//        builder.setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Alarm actived!")
//                .setContentText("THIS IS MY ALARM")
//                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
//                .setContentInfo("Info");
//
//        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(1,builder.build());
//
        //Toast.makeText(context, context.getString(R.string.Alertnotifty) + intent.getStringExtra("title") , Toast.LENGTH_LONG).show();

        //Log.d("REDEIVER", context.getString(R.string.Alertnotifty) + intent.getStringExtra("title") );

//        String Title = intent.getStringExtra(context.getString(R.string.titttle));
//        Intent x = new Intent(context, Alert.class);
//        x.putExtra(context.getString(R.string.titttle), Title);
//        x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(x);

        String title = intent.getStringExtra(context.getString(R.string.titttle));
        String message = intent.getStringExtra("message");

        mNotificationManager.showDetailsNotificationWithAllCitiesAction(title, message);

    }

//    public void Notification(Context context, String message) {
//        // Set Notification Title
//        String strtitle = context.getString(R.string.notificationtitle);
//        // Open NotificationView Class on Notification Click
//        Intent intent = new Intent(context, MainActivity.class);
//        // Send data to NotificationView Class
//        intent.putExtra("title", strtitle);
//        intent.putExtra("text", message);
//        // Open NotificationView.java Activity
//        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//
//        // Create Notification using NotificationCompat.Builder
//        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(
//                context)
//                // Set Icon
//                .setSmallIcon(R.drawable.ic_action_alarms)
//                // Set Ticker Message
//                .setTicker(message)
//                // Set Title
//                .setContentTitle(context.getString(R.string.notificationtitle))
//                // Set Text
//                .setContentText(message)
//                // Add an Action Button below Notification
//                .addAction(R.drawable.ic_action_alarms, "Action Button", pIntent)
//                // Set PendingIntent into Notification
//                .setContentIntent(pIntent)
//                // Dismiss Notification
//                .setAutoCancel(true);
//
//        // Create Notification Manager
//        NotificationManager notificationmanager = (NotificationManager) context
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        // Build Notification with Notification Manager
//        notificationmanager.notify(0, builder.build());
//
//    }
//
//    // Check for network availability
//    private boolean isNetworkAvailable(Context context) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager
//                .getActiveNetworkInfo();
//        return activeNetworkInfo != null;
//    }


}