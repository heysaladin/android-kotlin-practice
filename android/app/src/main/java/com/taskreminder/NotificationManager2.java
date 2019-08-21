package com.taskreminder;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

//import static com.taskreminder.AppUtils.activeTitle;

@SuppressWarnings("deprecation")
public class NotificationManager2 extends BroadcastReceiver {

    private AppNotificationManager mNotificationManager;
//
//    MainActivity ma; //a reference to activity's context
//
//    public NotificationManager2(MainActivity maContext){
//        ma=maContext;
//    }


    SQLiteDatabase db;


    @Override
    public void onReceive(Context context, Intent intent) {
//        String Title = intent.getStringExtra(context.getString(R.string.titttle));
//        String content = intent.getStringExtra(context.getString(R.string.alert_content));
//        NotificationCompat.Builder builder =
//                new NotificationCompat.Builder(context)
//                        .setSmallIcon(R.drawable.ic_action_alarms)
//                        .setContentTitle(Title)
//                        .setContentText(content).setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                        "://" + context.getPackageName() + "/raw/notify"));
//
//        // Add as notification
//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(0, builder.build());


        mNotificationManager = new AppNotificationManager(context);

        String title = intent.getStringExtra(context.getString(R.string.titttle));
        String message = intent.getStringExtra("message");
//        long id = Long.parseLong(intent.getStringExtra(context.getString(R.string.rodId)));

//        ma.setActive("12:00", "12:30", title);

//        activeTitle = title;


        // other things done here like notification

        // NUPDATE TEXTV1 IN MAINACTIVITY HERE
//        listener.doSomething("Some Result");

        //AppUtils.changeActive(context, title);

//        MainActivity.activeTitleText = title;


//        Cursor c = db.rawQuery("SELECT * FROM tasks WHERE TRIM(name) = '"+title.trim()+"'", null);
//        c.moveToFirst();
//        String cs = DatabaseUtils.dumpCursorToString(c);
//        Log.d("REC", "????????????? " + cs);

        mNotificationManager.showDetailsNotificationWithAllCitiesAction(title, message);


//        String title = intent.getStringExtra(context.getString(R.string.titttle));



    }

}