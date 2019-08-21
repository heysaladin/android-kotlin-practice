package com.taskreminder;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobScheduler;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static android.widget.Toast.LENGTH_SHORT;

//import static com.taskreminder.AppUtils.activeTitle;

//import static com.taskreminder.AppUtils.activeTitle;

public class MainActivity extends AppCompatActivity

{

    final int MY_CAL_WRITE_REQ = 42;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

    SQLiteDatabase db;
    DbHelper mDbHelper;
    ListView list;
    FloatingActionButton floatingActionButton;

    TextView activeTitleHome;

    public static String activeTitleText = "";

    private LinearLayout cont;


    // Projection array. Creating indices for this array instead of doing
// dynamic lookups improves performance.
    public static final String[] EVENT_PROJECTION = new String[] {
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
            CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
    };

    // The indices for the projection array above.
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;



//    // Your Activity code
//
//    public void updateTheTextView(String t) {
//        TextView textV1 = (TextView) findViewById(R.id.active_title_tv);
//        textV1.setText(t);
//    }
//
//    @Override
//    public void doSomething(String result){
//        updateTheTextView(result);          // Calling method from Interface
//    }


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

    public void setActive (String start, String end, String act) {

//        activeTitleHome = (TextView) findViewById(R.id.active_title_tv);
        activeTitleHome.setText(act);

//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("hh a");
//            Date timeseven = (Date) dateFormat.parse(start);
//            Date timeTen = (Date) dateFormat.parse(end);
//            Date now = new Date(System.currentTimeMillis());
//            Date CurrentTime = dateFormat.parse(now.toString());
//            if (CurrentTime.after(timeseven) && CurrentTime.before(timeTen)) {
//                Toast.makeText(this, "FIRST", Toast.LENGTH_SHORT).show();
//                activeTitle.setText("BETULLLL!!!!");
//            } else {
//                activeTitle.setText("SALAHHH!!!!");
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    }

//    public void setActive (String start, String end) {
//
//        activeTitle = (TextView) findViewById(R.id.active_title_tv);
//
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("hh a");
//            Date timeseven = (Date) dateFormat.parse(start);
//            Date timeTen = (Date) dateFormat.parse(end);
//            Date now = new Date(System.currentTimeMillis());
//            Date CurrentTime = dateFormat.parse(now.toString());
//            if (CurrentTime.after(timeseven) && CurrentTime.before(timeTen)) {
//                Toast.makeText(this, "FIRST", Toast.LENGTH_SHORT).show();
//                activeTitle.setText("BETULLLL!!!!");
//            } else {
//                activeTitle.setText("SALAHHH!!!!");
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//    }

    @Override
    protected void onResume() {
        super.onResume();
        activeTitleHome.setText(activeTitleText);
    }


    @Override
    protected void onStart() {
        super.onStart();
        activeTitleHome.setText(activeTitleText);
    }

    @SuppressLint("MissingPermission")
    private void get() {


// Run query
        Cursor cur = null;
        ContentResolver cr = getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[] {"didien.geonk@gmail.com", "didien.geonk@gmail.com",
                "didien.geonk@gmail.com"};
// Submit the query and get a Cursor object back.
        cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);



        Log.d("CUR", ">>>>>>>>>>>>> " + cur);

        if(cur != null) {
            Log.d("CUR", ">>>>>>>>>>>>> ADA");


// Use the cursor to step through the returned records
//                assert cur != null;
            while (cur.moveToNext()) {
                long calID = 0;
                String displayName = null;
                String accountName = null;
                String ownerName = null;

                // Get the field values
                calID = cur.getLong(PROJECTION_ID_INDEX);
                displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
                accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
                ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);

                // Do something with the values...

                Log.d("CUR", ">>>>>>>>>>>>> " + accountName);
//   ...
            }


        } else {
            Log.d("CUR", ">>>>>>>>>>>>> TIDAK ADA");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("DIPUJA");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setElevation(0);
//        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

//        activeTitleText = "";

//        mNotificationManager = new AppNotificationManager(this);
//
        final AppNotificationManager notificationManager = new AppNotificationManager(this);
        notificationManager.hideNotification(getIntent());


        cont = (LinearLayout) findViewById(R.id.cont);

        activeTitleHome = (TextView) findViewById(R.id.active_title_tv);
        activeTitleHome.setText(activeTitleText);

        list = (ListView) findViewById(R.id.commentlist);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"NewApi", "MissingPermission"})
            @Override
            public void onClick(View view) {
//                Intent openCreateNote = new Intent(MainActivity.this, CreateNote.class);
//                startActivity(openCreateNote);

//                startAlarm(true, false);

//                addCalendar();

//                getDataFromCalendarTable(view);

//                addEvent(view);

//                addInstance(view);

//                deleteSince(3);


/*
                long calID = 3;
                long startMillis = 0;
                long endMillis = 0;
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2012, 9, 14, 7, 30);
                startMillis = beginTime.getTimeInMillis();
                Calendar endTime = Calendar.getInstance();
                endTime.set(2012, 9, 14, 8, 45);
                endMillis = endTime.getTimeInMillis();
//...

                ContentResolver cr = getContentResolver();
                ContentValues values = new ContentValues();
                values.put(CalendarContract.Events.DTSTART, startMillis);
                values.put(CalendarContract.Events.DTEND, endMillis);
                values.put(CalendarContract.Events.TITLE, "Jazzercise");
                values.put(CalendarContract.Events.DESCRIPTION, "Group workout");
                values.put(CalendarContract.Events.CALENDAR_ID, calID);
                values.put(CalendarContract.Events.EVENT_TIMEZONE, "America/Los_Angeles");
                Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

// get the event ID that is the last element in the Uri
                long eventID = Long.parseLong(uri.getLastPathSegment());
//
// ... do something with event ID
//
//

                uri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventID);
                Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setData(uri);
                startActivity(intent);
                */

get();




//                Calendar cal = Calendar.getInstance();
//                Intent intent = new Intent(Intent.ACTION_EDIT);
//                intent.setType("vnd.android.cursor.item/event");
//                intent.putExtra("beginTime", cal.getTimeInMillis());
//                intent.putExtra("allDay", true);
//                intent.putExtra("rrule", "FREQ=YEARLY");
//                intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
//                intent.putExtra("title", "A Test Event from android app");
//                startActivity(intent);



//                Cursor cursor = getContentResolver().query(Uri.parse("content://com.android.calendar/events"), new String[]{ "calendar_id", "title", "description", "dtstart", "dtend", "eventLocation" }, null, null, null);
//                //Cursor cursor = cr.query(Uri.parse("content://calendar/calendars"), new String[]{ "_id", "name" }, null, null, null);
//                String add = null;
//                cursor.moveToFirst();
//                String[] CalNames = new String[cursor.getCount()];
//                int[] CalIds = new int[cursor.getCount()];
//                for (int i = 0; i < CalNames.length; i++) {
//                    CalIds[i] = cursor.getInt(0);
//                    CalNames[i] = "Event"+cursor.getInt(0)+": \nTitle: "+ cursor.getString(1)+"\nDescription: "+cursor.getString(2)+"\nStart Date: "+new Date(cursor.getLong(3))+"\nEnd Date : "+new Date(cursor.getLong(4))+"\nLocation : "+cursor.getString(5);
//                    if(add == null)
//                        add = CalNames[i];
//                    else{
//                        add += CalNames[i];
//                    }
////                    ((TextView)findViewById(R.id.calendars)).setText(add);
//
//            TextView tv1 =  new TextView(MainActivity.this);
//            tv1.setText(add);
//            cont.addView(tv1);
//
//                    cursor.moveToNext();
//                }
//                cursor.close();






//// Use the cursor to step through the returned records
//                assert cur != null;
//                while (cur.moveToNext()) {
//                    long calID = 0;
//                    String displayName = null;
//                    String accountName = null;
//                    String ownerName = null;
//
//                    // Get the field values
//                    calID = cur.getLong(PROJECTION_ID_INDEX);
//                    displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
//                    accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
//                    ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
//
//                    // Do something with the values...
//
//                    Log.d("CUR", ">>>>>>>>>>>>> " + accountName);
////   ...
//                }
//
//
//                cur.close();



//                TextView tv1 =  new TextView(MainActivity.this);
//                tv1.setText("awww");
//                cont.addView(tv1);


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
                mDbHelper.DATE,
                mDbHelper.TIME_END
        };
        final String[] column = {
                mDbHelper.C_ID,
                mDbHelper.TITLE,
                mDbHelper.DETAIL,
                mDbHelper.TYPE,
                mDbHelper.TIME,
                mDbHelper.DATE,
                mDbHelper.TIME_END
        };
        int[] to = {
                R.id.title,
                R.id.Detail,
                R.id.type,
                R.id.time,
                R.id.date,
                R.id.time_end
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


//    public void addInstance(View view) {
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_CAL_WRITE_REQ);
//        }
//
//        ContentResolver cr = getContentResolver();
//        ContentValues contentValues = new ContentValues();
//
//        Calendar beginTime = Calendar.getInstance();
//        beginTime.set(2019, 8, 21, 18, 15);
//
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(2019, 8, 21, 19, 45);
//
//        ContentValues values = new ContentValues();
//        values.put(CalendarContract.Instances.EVENT_ID, "1");
//        values.put(CalendarContract.Instances.BEGIN, beginTime.getTimeInMillis());
//        values.put(CalendarContract.Instances.END, endTime.getTimeInMillis());
//
//
//        cr.insert(CalendarContract.Reminders.CONTENT_URI, values);
//    }
//
//
//
//    public void getDataFromCalendarTable(View v) {
//        Cursor cur = null;
//        ContentResolver cr = getContentResolver();
//
//        String[] mProjection =
//                {
//                        CalendarContract.Calendars.ALLOWED_ATTENDEE_TYPES,
//                        CalendarContract.Calendars.ACCOUNT_NAME,
//                        CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
//                        CalendarContract.Calendars.CALENDAR_LOCATION,
//                        CalendarContract.Calendars.CALENDAR_TIME_ZONE
//                };
//
//        Uri uri = CalendarContract.Calendars.CONTENT_URI;
//        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
//                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
//                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
//        String[] selectionArgs = new String[]{"didien.geonk@gmail.com", "didien.geonk@gmail.com",
//                "didien.geonk@gmail.com"};
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, MY_CAL_WRITE_REQ);
//        }
//        cur = cr.query(uri, mProjection, selection, selectionArgs, null);
//
//        while (cur.moveToNext()) {
//            String displayName = cur.getString(cur.getColumnIndex(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME));
//            String accountName = cur.getString(cur.getColumnIndex(CalendarContract.Calendars.ACCOUNT_NAME));
////            String x = cur.getString(cur.getColumnIndex(CalendarContract.Calendars.NAME));
//
//            TextView tv1 =  new TextView(this);
//            tv1.setText(accountName);
//            cont.addView(tv1);
//        }
//
//    }
//
//    public void addEvent(View view) {
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_CAL_WRITE_REQ);
//        }
//
//        ContentResolver cr = getContentResolver();
//        ContentValues contentValues = new ContentValues();
//
//        Calendar beginTime = Calendar.getInstance();
//        beginTime.set(2019, 8, 21, 18, 30);
//
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(2019, 8, 21, 19, 35);
//
//        ContentValues values = new ContentValues();
//        values.put(CalendarContract.Events.DTSTART, beginTime.getTimeInMillis());
//        values.put(CalendarContract.Events.DTEND, endTime.getTimeInMillis());
//        values.put(CalendarContract.Events.TITLE, "Tech Stores");
//        values.put(CalendarContract.Events.DESCRIPTION, "Successful Startups");
//        values.put(CalendarContract.Events.CALENDAR_ID, 2);
//        values.put(CalendarContract.Events.EVENT_TIMEZONE, "Europe/London");
//        values.put(CalendarContract.Events.EVENT_LOCATION, "London");
//        values.put(CalendarContract.Events.GUESTS_CAN_INVITE_OTHERS, "1");
//        values.put(CalendarContract.Events.GUESTS_CAN_SEE_GUESTS, "1");
//
//        cr.insert(CalendarContract.Events.CONTENT_URI, values);
//    }
//
//
//
//    public void addCalendar() {
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(CalendarContract.Calendars.ACCOUNT_NAME, "didien.geonk@gmail.com");
//        contentValues.put(CalendarContract.Calendars.ACCOUNT_TYPE, "didien.geonk@gmail.com");
//        contentValues.put(CalendarContract.Calendars.NAME, "didien calendar");
//        contentValues.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, "farooq-agent.web.app Calendar");
//        contentValues.put(CalendarContract.Calendars.CALENDAR_COLOR, "232323");
//        contentValues.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
//        contentValues.put(CalendarContract.Calendars.OWNER_ACCOUNT, "didien.geonk@gmail.com");
//        contentValues.put(CalendarContract.Calendars.ALLOWED_REMINDERS, "METHOD_ALERT, METHOD_EMAIL, METHOD_ALARM");
//        contentValues.put(CalendarContract.Calendars.ALLOWED_ATTENDEE_TYPES, "TYPE_OPTIONAL, TYPE_REQUIRED, TYPE_RESOURCE");
//        contentValues.put(CalendarContract.Calendars.ALLOWED_AVAILABILITY, "AVAILABILITY_BUSY, AVAILABILITY_FREE, AVAILABILITY_TENTATIVE");
//
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_CAL_WRITE_REQ);
//        }
//
//        Uri uri = CalendarContract.Calendars.CONTENT_URI;
//        uri = uri.buildUpon().appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER,"true")
//                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, "didien.geonk@gmail.com")
//                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, "didien.geonk@gmail.com").build();
//        getContentResolver().insert(uri, contentValues);
////        getContentResolver().delete(uri,
////                "", new String[] { String.valueOf(0) });
//
////        Uri calUri = CalendarContract.Calendars.CONTENT_URI;
////        calUri = calUri.buildUpon()
////                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
////                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, "didien.geonk@gmail.com")
////                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, "didien.geonk@gmail.com").build();
////
////
//////        AsyncQueryHandler calendarHandler;
//////        calendarHandler = new AsyncQueryHandler(this.getContentResolver());
////        calendarHandler.startDelete(0,-1,calUri,null,null);
//
//
//
//    }
//
////    private void deleteCalendarTest()
////    {
////        Uri.Builder builder = CalendarContract.Calendars.CONTENT_URI.buildUpon();
////        builder.appendPath("3").appendQueryParameter(android.provider.CalendarContract.CALLER_IS_SYNCADAPTER, String.valueOf(true))
////                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, private)
////           .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CalendarContract.ACCOUNT_TYPE_LOCAL);
////
//////        builder = builder.appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER,"true")
//////                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, "didien.geonk@gmail.com")
//////                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, "didien.geonk@gmail.com").build();
////
////        Uri uri = builder.build();
////
////        getContentResolver().delete(uri, null, null);
////        Toast.makeText(this, "JOS", Toast.LENGTH_SHORT).show();
////    }
//
//    private void deleteSince(long threshold) {
//        Uri uri = CalendarContract.Calendars.CONTENT_URI;
//        uri = uri.buildUpon().appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER,"true")
//                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, "didien.geonk@gmail.com")
//                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, "didien.geonk@gmail.com").build();
//        int rows = getContentResolver().delete(uri,
//                "requestDate <= ?", new String[] { String.valueOf(threshold) });
//        Log.i("AW", rows + " transactions deleted");
//    }

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