package org.haqnawaz.mc_reminder_todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class AddReminder extends AppCompatActivity {

    int day;
    int month;
    int year;
    Calendar calendar;
    TextView date1;
    TextView date2;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        date1 = findViewById(R.id.date_text);
        date2 = findViewById(R.id.set_date);
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
        datePickerDialog.show();
    }
}