package com.taskreminder;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE=101;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

    SQLiteDatabase db;
    DbHelper mDbHelper;
    ListView list;
    FloatingActionButton floatingActionButton;

//    AlarmManager alarmManager;
//    private PendingIntent pendingIntent;
//    private static MainActivity inst;
//
//    public static MainActivity instance() {
//        return inst;
//    }
//
//
//
//    public void setAlarmText(String alarmText) {
////        alarmTextView.setText(alarmText);
//        Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.Alertnotifty) , Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        inst = this;
//    }


//    private void setAlarm() {
//
//        int sec = Integer.parseInt("1");
//
//        Intent intent=new Intent(MainActivity.this, AlarmReceiver.class);
//        PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0, intent,0);
//        AlarmManager a=(AlarmManager)getSystemService(ALARM_SERVICE);
//        a.set(AlarmManager.RTC,System.currentTimeMillis() +sec*1000,pendingIntent);
//        Toast.makeText(getApplicationContext(),"Alarm set",Toast.LENGTH_LONG).show();
//
//
//    }


//    private AppNotificationManager mNotificationManager;
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        if (getActivity() != null) {
//            mNotificationManager = new AppNotificationManager(getActivity());
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("DIPUJA");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
//        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

//        mNotificationManager = new AppNotificationManager(this);
//
        final AppNotificationManager notificationManager = new AppNotificationManager(this);
        notificationManager.hideNotification(getIntent());

        list = (ListView) findViewById(R.id.commentlist);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                Intent openCreateNote = new Intent(MainActivity.this, CreateNote.class);
                startActivity(openCreateNote);

//                startAlarm(true, false);



//                mNotificationManager.showDetailsNotificationWithAllCitiesAction();




//                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
//
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                Calendar calendar = Calendar.getInstance();
//
//                calendar.setTimeInMillis(System.currentTimeMillis());
//                calendar.set(Calendar.HOUR_OF_DAY, 0);
//                calendar.set(Calendar.MINUTE, 1);
///*
//Alarm will be triggered once exactly at 5:30
//*/
//                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);



                //jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

//                findViewById(R.id.btn_startjob).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {

//                        setAlarm();


//                        Toast.makeText(MainActivity.this, "Alarm Set", Toast.LENGTH_SHORT).show();
//
//                    }
//                });




//                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
//
//                builder.setAutoCancel(true)
//                        .setDefaults(Notification.DEFAULT_ALL)
//                        .setWhen(System.currentTimeMillis())
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setContentTitle("Alarm actived!")
//                        .setContentText("THIS IS MY ALARM")
//                        .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
//                        .setContentInfo("Info");
//
//                NotificationManager notificationManager = (NotificationManager)MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
//                notificationManager.notify(1,builder.build());




/*
1st Param : Type of the Alarm
2nd Param : Time in milliseconds when the alarm will be triggered first
3rd Param :Pending Intent
Note that we have changed the type to RTC as we are using Wall clock time. Also device wont wake up
when the alarm is triggered
*/

            }
        });
        mDbHelper = new DbHelper(this);
        db = mDbHelper.getWritableDatabase();
        //final ImageView alarmImage = (ImageView) findViewById(R.id.alarmImage);

        String[] from = {
                mDbHelper.TITLE,
                mDbHelper.DETAIL,
                mDbHelper.TYPE,
                mDbHelper.TIME,
                mDbHelper.DATE
        };
        final String[] column = {
                mDbHelper.C_ID,
                mDbHelper.TITLE,
                mDbHelper.DETAIL,
                mDbHelper.TYPE,
                mDbHelper.TIME,
                mDbHelper.DATE
        };
        int[] to = {
                R.id.title,
                R.id.Detail,
                //R.id.type,
                R.id.time,
                R.id.date
        };

        final Cursor cursor = db.query(mDbHelper.TABLE_NAME, column, null, null, null, null, null);
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_entry, cursor, from, to, 0);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView < ? > listView, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, View_Note.class);
                intent.putExtra(getString(R.string.rodId), id);
                startActivity(intent);
            }

        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
   /*
   case R.id.action_new:
       Intent openCreateNote = new Intent(MainActivity.this, CreateNote.class);
       startActivity(openCreateNote);
       return true;
       */
            default:
                return super.onOptionsItemSelected(item);
        }

        // return super.onOptionsItemSelected(item);

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectDrawerItem(menuItem);
                return true;
            }
        });
    }

    //method untuk eksekusi action dari tiap menu item
    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_camera:
                //action
                break;
            case R.id.nav_gallery:
                //action
                break;
            case R.id.nav_slideshow:
                //action
                break;
            case R.id.nav_manage:
                //action
                break;

        }
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();
    }

}