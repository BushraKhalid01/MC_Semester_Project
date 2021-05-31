package org.haqnawaz.mc_reminder_todolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Reminder extends AppCompatActivity {

    int day;
    int month;
    int year;
    int currHour;
    int currMin;
    String amPm;
    Calendar calendar;
    TextView date1;
    TextView date2;
    TextView time1;
    TextView time2;
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        date1 = findViewById(R.id.date_text);
        date2 = findViewById(R.id.set_date);
        time1 = findViewById(R.id.time_text);
        time2 = findViewById(R.id.set_time);
        Task editTask= getIntent().getParcelableExtra("EditTask");
        if(editTask==null)
        {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm a");
            date2.setText(date.format(c.getTime()));
            time2.setText(time.format(c.getTime()));
        }
        else
        {
            date2.setText(editTask.getDate());
            time2.setText(editTask.getTime());
        }

    }

    public void selectDate(View view) {
        calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date2.setText(dayOfMonth + "/" + (month+1) + "/" + year);
            }
        },year,month,day);
        Log.d("sss", String.valueOf(date2));
        datePickerDialog.show();
    }

    public void selectTime(View view) {
        calendar=Calendar.getInstance();
        currHour=calendar.get(Calendar.HOUR_OF_DAY);
        currMin=calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay>=12){
                    amPm="PM";
                }
                else{
                    amPm="AM";
                }
                time2.setText(String.format("%02d:%02d",hourOfDay, minute)+" "+amPm);
            }
        },currHour,currMin,false);
        timePickerDialog.show();
    }
}
