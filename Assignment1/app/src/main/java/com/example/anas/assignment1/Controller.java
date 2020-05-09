package com.example.anas.assignment1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Controller {

    private static final String SHARED_PREFS = "datesPref";

    private String category;
    private int icon;

    public ArrayList<String> getDaysBetweenDates(String date1, String date2) throws ParseException {
        List<Date> dates = new ArrayList<Date>();
        ArrayList<String> date = new ArrayList<String>();


        DateFormat formatter;

        formatter = new SimpleDateFormat( "d-M-yyyy" );
        Date startDate = (Date) formatter.parse( date1 );
        Date endDate = (Date) formatter.parse( date2 );
        long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
        long endTime = endDate.getTime(); // create your endtime here, possibly using Calendar or Date
        long curTime = startDate.getTime();
        while (curTime <= endTime) {
            dates.add( new Date( curTime ) );
            curTime += interval;
        }
        for (int i = 0; i < dates.size(); i++) {
            Date lDate = (Date) dates.get( i );
            String ds = formatter.format( lDate );
            date.add( ds );

        }
        return date;
    }

    public String getName(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( context );
        String firstname = preferences.getString( "firstname", "" );
        String surname = preferences.getString( "surname", "" );
        return firstname + " " + surname;
    }

    public String getStartdate(Context context) {
        SharedPreferences preferences = context.getSharedPreferences( SHARED_PREFS, Context.MODE_PRIVATE );
        String startdate = preferences.getString( "startdate", "" );
        return startdate;
    }

    public String getEnddate(Context context) {
        SharedPreferences preferences = context.getSharedPreferences( SHARED_PREFS, Context.MODE_PRIVATE );
        String enddate = preferences.getString( "enddate", "" );
        return enddate;
    }

    public void loadCalendar(final TextView textView, Context context) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get( Calendar.YEAR );
        int mDay = c.get( Calendar.DAY_OF_MONTH );
        int mMonth = c.get( Calendar.MONTH );

        DatePickerDialog datePickerDialog = new DatePickerDialog( context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        textView.setText( dayOfMonth + "-" + (monthOfYear + 1) + "-" + year );

                    }
                }, mYear, mMonth, mDay );

        datePickerDialog.show();
    }

    public void saveDataLogin(final String FIRSTNAME, final String SURNAME, Context context, EditText firstname, EditText surname) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences( context );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( FIRSTNAME, firstname.getText().toString() );
        editor.putString( SURNAME, surname.getText().toString() );

        editor.apply();
        Toast.makeText( context, "data saved", Toast.LENGTH_SHORT ).show();
    }

    public void saveDates(Context context, final String STARTDATE, final String ENDDATE, TextView tvStartDate, TextView tvEndDate) {
        SharedPreferences sharedPreferences = context.getSharedPreferences( SHARED_PREFS, Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( STARTDATE, tvStartDate.getText().toString() );
        editor.putString( ENDDATE, tvEndDate.getText().toString() );
        editor.apply();
        Toast.makeText( context, "data saved", Toast.LENGTH_SHORT ).show();
    }
    public void saveBarcode(Context context,String code) {
        SharedPreferences sharedPreferences = context.getSharedPreferences( "getCode",Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( "code", code );
        editor.apply();
    }
    public String getCode(Context context) {
        SharedPreferences preferences = context.getSharedPreferences( "getCode", Context.MODE_PRIVATE );
        String code = preferences.getString( "code", "" );
        return code;
    }
}
