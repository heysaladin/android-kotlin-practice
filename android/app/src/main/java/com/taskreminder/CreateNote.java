package com.taskreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateNote extends AppCompatActivity {

    SQLiteDatabase db;
    DbHelper mDbHelper;
    EditText mTitleText;
    EditText mDescriptionText;
    Spinner mSpinner;
    DatePicker pickerDate;
    TimePicker pickerTime;
    TimePicker pickerTimeEnd;
    TextView time;
    TextView timeEnd;
    TextView date;
    CheckBox checkBoxAlarm, checkboxnotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        mDbHelper = new DbHelper(this);
        db = mDbHelper.getWritableDatabase();

        mTitleText = (EditText) findViewById(R.id.txttitle);
        mDescriptionText = (EditText) findViewById(R.id.description);
        mSpinner = (Spinner) findViewById(R.id.spinnerNoteType);
        pickerDate = (DatePicker) findViewById(R.id.datePicker);
        pickerTime = (TimePicker) findViewById(R.id.timePicker);
        pickerTimeEnd = (TimePicker) findViewById(R.id.timePickerEnd);
        time = (TextView) findViewById(R.id.txtTime);
        timeEnd = (TextView) findViewById(R.id.txtTimeEnd);
        date = (TextView) findViewById(R.id.txtDate);
        checkBoxAlarm = (CheckBox) findViewById(R.id.checkBox);
        checkboxnotify = (CheckBox) findViewById(R.id.checkBox2);


        pickerDate.setVisibility(View.INVISIBLE);
        pickerTime.setVisibility(View.INVISIBLE);
        pickerTimeEnd.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
        date.setVisibility(View.INVISIBLE);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.note_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(
                    AdapterView parent, View view, int position, long id) {
                if (id == 2) {
                    showToast(getString(R.string.added_alert));
                    checkBoxAlarm.setEnabled(true);
                } else {
                    checkBoxAlarm.setEnabled(false);
                    checkBoxAlarm.setChecked(false);
                }
                if (id == 3) {
                    showToast(getString(R.string.notify));
                    checkboxnotify.setEnabled(true);
                } else {
                    checkboxnotify.setEnabled(false);
                    checkboxnotify.setChecked(false);
                }
            }

            public void onNothingSelected(AdapterView parent) {}
        });

        checkBoxAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    pickerDate.setVisibility(View.VISIBLE);
                    pickerTime.setVisibility(View.VISIBLE);
                    pickerTimeEnd.setVisibility(View.VISIBLE);
                    time.setVisibility(View.VISIBLE);
                    date.setVisibility(View.VISIBLE);
                } else {
                    pickerDate.setVisibility(View.INVISIBLE);
                    pickerTime.setVisibility(View.INVISIBLE);
                    pickerTimeEnd.setVisibility(View.INVISIBLE);
                    time.setVisibility(View.INVISIBLE);
                    date.setVisibility(View.INVISIBLE);
                }
            }
        });
        checkboxnotify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    pickerDate.setVisibility(View.VISIBLE);
                    pickerTime.setVisibility(View.VISIBLE);
                    pickerTimeEnd.setVisibility(View.VISIBLE);
                    time.setVisibility(View.VISIBLE);
                    date.setVisibility(View.VISIBLE);
                } else {
                    pickerDate.setVisibility(View.INVISIBLE);
                    pickerTime.setVisibility(View.INVISIBLE);
                    pickerTimeEnd.setVisibility(View.INVISIBLE);
                    time.setVisibility(View.INVISIBLE);
                    date.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, MainActivity.class);
        startActivity(setIntent);
    }

    void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:
                String title = mTitleText.getText().toString();
                String detail = mDescriptionText.getText().toString();
                String type = mSpinner.getSelectedItem().toString();
                ContentValues cv = new ContentValues();
//                ContentValues cvEnd = new ContentValues();
                cv.put(mDbHelper.TITLE, title);
                cv.put(mDbHelper.DETAIL, detail);
                cv.put(mDbHelper.TYPE, type);
                cv.put(mDbHelper.TIME, getString(R.string.Not_Set));

//                Calendar calenderGeneral = Calendar.getInstance();
//                calenderGeneral.clear();
//                calenderGeneral.set(Calendar.MONTH, pickerDate.getMonth());
//                calenderGeneral.set(Calendar.DAY_OF_MONTH, pickerDate.getDayOfMonth());
//                calenderGeneral.set(Calendar.YEAR, pickerDate.getYear());
//                calenderGeneral.set(Calendar.HOUR, pickerTime.getCurrentHour());
//                calenderGeneral.set(Calendar.MINUTE, pickerTime.getCurrentMinute());
//                calenderGeneral.set(Calendar.SECOND, 00);
//
//                AlarmManager alarmMgrGeneral = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                Intent intentGeneral = new Intent(this, ActivityReceiverx.class);
//                PendingIntent pendingIntentGeneral = PendingIntent.getBroadcast(this, 0, intentGeneral, 0);
//                alarmMgrGeneral.set(AlarmManager.RTC_WAKEUP, calenderGeneral.getTimeInMillis(), pendingIntentGeneral);

                if (checkBoxAlarm.isChecked()) {
                    Calendar calender = Calendar.getInstance();
                    calender.clear();
                    calender.set(Calendar.MONTH, pickerDate.getMonth());
                    calender.set(Calendar.DAY_OF_MONTH, pickerDate.getDayOfMonth());
                    calender.set(Calendar.YEAR, pickerDate.getYear());
                    calender.set(Calendar.HOUR, pickerTime.getCurrentHour());
                    calender.set(Calendar.MINUTE, pickerTime.getCurrentMinute());
                    calender.set(Calendar.SECOND, 00);

                    SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.hour_minutes));
                    String timeString = formatter.format(new Date(calender.getTimeInMillis()));
                    SimpleDateFormat dateformatter = new SimpleDateFormat(getString(R.string.dateformate));
                    String dateString = dateformatter.format(new Date(calender.getTimeInMillis()));

                    AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(this, AlarmReceiver.class);

                    String alertTitle = mTitleText.getText().toString();
                    intent.putExtra(getString(R.string.alert_title), alertTitle);
                    intent.putExtra("message", mDescriptionText.getText().toString());

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

                    alarmMgr.set(AlarmManager.RTC, calender.getTimeInMillis(), pendingIntent);
                    cv.put(mDbHelper.TIME, timeString);
                    cv.put(mDbHelper.DATE, dateString);



                    Calendar calenderEnd = Calendar.getInstance();
                    calenderEnd.clear();
                    calenderEnd.set(Calendar.MONTH, pickerDate.getMonth());
                    calenderEnd.set(Calendar.DAY_OF_MONTH, pickerDate.getDayOfMonth());
                    calenderEnd.set(Calendar.YEAR, pickerDate.getYear());
                    calenderEnd.set(Calendar.HOUR, pickerTimeEnd.getCurrentHour());
                    calenderEnd.set(Calendar.MINUTE, pickerTimeEnd.getCurrentMinute());
                    calenderEnd.set(Calendar.SECOND, 00);

                    SimpleDateFormat formatterEnd = new SimpleDateFormat(getString(R.string.hour_minutes));
                    String timeStringEnd = formatterEnd.format(new Date(calenderEnd.getTimeInMillis()));
                    SimpleDateFormat dateformatterEnd = new SimpleDateFormat(getString(R.string.dateformate));
                    String dateStringEnd = dateformatterEnd.format(new Date(calenderEnd.getTimeInMillis()));

                    AlarmManager alarmMgrEnd = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent intentEnd = new Intent(this, AlarmReceiver.class);

                    String alertTitleEnd = mTitleText.getText().toString();
                    intentEnd.putExtra(getString(R.string.alert_title), alertTitleEnd);
                    intentEnd.putExtra("message", mDescriptionText.getText().toString());
//                    intentEnd.putExtra(getString(R.string.rodId), mDescriptionText.getText().toString());

                    PendingIntent pendingIntentEnd = PendingIntent.getBroadcast(this, 0, intentEnd, 0);

                    alarmMgrEnd.set(AlarmManager.RTC, calender.getTimeInMillis(), pendingIntentEnd);
                    cv.put(mDbHelper.TIME_END, timeStringEnd);
//                    cvEnd.put(mDbHelper.DATE, dateStringEnd);




                } else if (checkboxnotify.isChecked()) {
                    Calendar calender = Calendar.getInstance();
                    calender.clear();
                    calender.set(Calendar.MONTH, pickerDate.getMonth());
                    calender.set(Calendar.DAY_OF_MONTH, pickerDate.getDayOfMonth());
                    calender.set(Calendar.YEAR, pickerDate.getYear());
                    calender.set(Calendar.HOUR, pickerTime.getCurrentHour());
                    calender.set(Calendar.MINUTE, pickerTime.getCurrentMinute());
                    calender.set(Calendar.SECOND, 00);

                    SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.hour_minutes));
                    String timeString = formatter.format(new Date(calender.getTimeInMillis()));
                    SimpleDateFormat dateformatter = new SimpleDateFormat(getString(R.string.dateformate));
                    String dateString = dateformatter.format(new Date(calender.getTimeInMillis()));

                    AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(this, NotificationManager2.class);

                    String alertTitle = mTitleText.getText().toString();
                    String alertContent = mDescriptionText.getText().toString();
                    intent.putExtra(getString(R.string.alert_title), alertTitle);
                    intent.putExtra(getString(R.string.alert_content), alertContent);
                    intent.putExtra("message", mDescriptionText.getText().toString());

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

                    alarmMgr.set(AlarmManager.RTC, calender.getTimeInMillis(), pendingIntent);
                    cv.put(mDbHelper.TIME, timeString);
                    cv.put(mDbHelper.DATE, dateString);



                    Calendar calenderEnd = Calendar.getInstance();
                    calenderEnd.clear();
                    calenderEnd.set(Calendar.MONTH, pickerDate.getMonth());
                    calenderEnd.set(Calendar.DAY_OF_MONTH, pickerDate.getDayOfMonth());
                    calenderEnd.set(Calendar.YEAR, pickerDate.getYear());
                    calenderEnd.set(Calendar.HOUR, pickerTimeEnd.getCurrentHour());
                    calenderEnd.set(Calendar.MINUTE, pickerTimeEnd.getCurrentMinute());
                    calenderEnd.set(Calendar.SECOND, 00);

                    SimpleDateFormat formatterEnd = new SimpleDateFormat(getString(R.string.hour_minutes));
                    String timeStringEnd = formatterEnd.format(new Date(calenderEnd.getTimeInMillis()));
                    SimpleDateFormat dateformatterEnd = new SimpleDateFormat(getString(R.string.dateformate));
                    String dateStringEnd = dateformatterEnd.format(new Date(calenderEnd.getTimeInMillis()));

                    AlarmManager alarmMgrEnd = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent intentEnd = new Intent(this, NotificationManager2.class);

                    String alertTitleEnd = mTitleText.getText().toString();
                    String alertContentEnd = mDescriptionText.getText().toString();
                    intentEnd.putExtra(getString(R.string.alert_title), alertTitleEnd);
                    intentEnd.putExtra(getString(R.string.alert_content), alertContentEnd);
                    intentEnd.putExtra("message", mDescriptionText.getText().toString());

                    PendingIntent pendingIntentEnd = PendingIntent.getBroadcast(this, 0, intentEnd, 0);

                    alarmMgrEnd.set(AlarmManager.RTC, calender.getTimeInMillis(), pendingIntentEnd);
                    cv.put(mDbHelper.TIME_END, timeStringEnd);
//                    cv.put(mDbHelper.DATE, dateStringEnd);


                }
                db.insert(mDbHelper.TABLE_NAME, null, cv);

                Intent openMainScreen = new Intent(this, MainActivity.class);
                openMainScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(openMainScreen);
                return true;

            case R.id.action_back:
                Intent openMainActivity = new Intent(this, MainActivity.class);
                startActivity(openMainActivity);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}