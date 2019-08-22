package com.taskreminder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Utility {
    public static ArrayList<String> nameOfEvent = new ArrayList<String>();
    public static ArrayList<String> startDates = new ArrayList<String>();
    public static ArrayList<String> endDates = new ArrayList<String>();
    public static ArrayList<String> descriptions = new ArrayList<String>();

    public static ArrayList<String> readCalendarEvent(Context context) {
        Cursor cursor = context.getContentResolver()
                .query(
                        Uri.parse("content://com.android.calendar/events"),
                        new String[] { "calendar_id", "title", "description",
                                "dtstart", "dtend", "eventLocation" }, null,
                        null, null);
        cursor.moveToFirst();
        // fetching calendars name
        String CNames[] = new String[cursor.getCount()];

        // fetching calendars id
        nameOfEvent.clear();
        startDates.clear();
        endDates.clear();
        descriptions.clear();

        String endDate = "0";

        for (int i = 0; i < CNames.length - 1; i++) {

            if(cursor.getString(4) == null || cursor.getString(4).equals("")) {
                endDate = "0";
            }

//            String des = "";
//            if(cursor.getString(4) == null || cursor.getString(4).equals("")) {
//                des = "0";
//            }

            nameOfEvent.add(cursor.getString(1));
            startDates.add(getDate(Long.parseLong(cursor.getString(3))));
            endDates.add(getDate(Long.parseLong(endDate)));
            descriptions.add(cursor.getString(2));
            CNames[i] = cursor.getString(1);
            cursor.moveToNext();

            Log.d("CAL", ">>>>>>>>>>>>>> 1 "+cursor.getString(1));
            Log.d("CAL", ">>>>>>>>>>>>>> 3 "+cursor.getString(3));
            Log.d("CAL", ">>>>>>>>>>>>>> 4 "+cursor.getString(4));
            Log.d("CAL", ">>>>>>>>>>>>>> 2 "+cursor.getString(2));

        }
        return nameOfEvent;
    }

    public static String getDate(long milliSeconds) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy hh:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
