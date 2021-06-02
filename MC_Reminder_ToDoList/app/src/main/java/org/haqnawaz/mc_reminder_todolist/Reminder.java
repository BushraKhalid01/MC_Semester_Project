package org.haqnawaz.mc_reminder_todolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Reminder extends AppCompatActivity {
    Task task;
    int day;
    int month;
    int year;
    int currHour;
    int currMin;
    String amPm;
    Calendar calendar;
    TextView date1 = findViewById(R.id.date_text);
    TextView date2 = findViewById(R.id.set_date);
    TextView time1 = findViewById(R.id.time_text);
    TextView time2 = findViewById(R.id.set_time);
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    private String repeat;
    private String repeatNo;
    private String repeatType;
    private String isActive;
    Switch active = findViewById(R.id.repeat_switch);
    TextView repeatText = findViewById(R.id.set_repeat);
    TextView repeatNum = findViewById(R.id.set_repeat_no);
    TextView repType = findViewById(R.id.set_repeat_type);
    TextView repeatTypeText = findViewById(R.id.set_repeat_type);
    EditText title = findViewById(R.id.reminder_title);
    RecyclerViewAdapter recyclerViewAdapter;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);



        isActive="true";
        repeatNo=Integer.toString(1);
        repeatType="Hour";
        repeat="true";

        Task editTask= getIntent().getParcelableExtra("EditTask");
        if(editTask==null)
        {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm a");
            date2.setText(date.format(c.getTime()));
            time2.setText(time.format(c.getTime()));

            repeatNum.setText(repeatNo);
            repType.setText(repeatType);
            repeatText.setText("Every " + repeatNo + " " + repeatType + "(s)");
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
                if (hourOfDay >=0 && hourOfDay < 12){
                    if(hourOfDay==0)
                    {
                        hourOfDay=hourOfDay+12;
                    }
                    amPm = "AM";
                } else{
                    if(hourOfDay>12)
                    {
                        hourOfDay=hourOfDay-12;
                    }
                    amPm = "PM";
                }
                if(minute<10)
                {
                    if(hourOfDay<10)
                    {
                        time2.setText("0"+hourOfDay+" : 0"+ minute+" "+amPm);
                    }
                    else
                    {
                        time2.setText(hourOfDay+" : 0"+ minute+" "+amPm);
                    }
                }
                else
                {
                    if(hourOfDay<10)
                    {
                        time2.setText("0"+hourOfDay+" : "+ minute+" "+amPm);
                    }
                    else
                    {
                        time2.setText(hourOfDay+" : "+ minute+" "+amPm);
                    }
                }
            }
        },currHour,currMin,false);
        timePickerDialog.show();
    }
    public void onSwitchRepeat(View view) {
        boolean on = ((Switch) view).isChecked();
        if (on) {
            repeat = "true";
            repeatText.setText("Every " + repeatNo + " " + repeatType + "(s)");
        } else {
            repeat = "false";
            repeatText.setText(R.string.repeat_off);
        }
    }
    public void setRepeatNo(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter Number");

        // Create EditText box to input repeat number
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        alert.setView(input);
        alert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        if (input.getText().toString().length() == 0) {
                            repeatNo = Integer.toString(1);
                            repeatNum.setText(repeatNo);
                            repeatText.setText("Every " + repeatNo + " " + repeatType + "(s)");
                        }
                        else {
                            repeatNo = input.getText().toString().trim();
                            repeatNum.setText(repeatNo);
                            repeatText.setText("Every " + repeatNo + " " + repeatType + "(s)");
                        }
                    }
                });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // do nothing
            }
        });
        alert.show();
    }

    public void selectRepeatType(View v){
        final String[] items = new String[5];

        items[0] = "Minute";
        items[1] = "Hour";
        items[2] = "Day";
        items[3] = "Week";
        items[4] = "Month";

        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Type");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                repeatType = items[item];
                repeatTypeText.setText(repeatType);
                repeatText.setText("Every " + repeatNo + " " + repeatType + "(s)");
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public void SaveTask(View view) {


        task = new Task(1,title.getText().toString(),date2.getText().toString(),time2.getText().toString(),active.isChecked(),active.isChecked(),
                Integer.parseInt(repeatNum.getText().toString()),repeatTypeText.getText().toString());
        Toast.makeText(Reminder.this, task.toString(), Toast.LENGTH_SHORT).show();
       /* try {
            task = new Task(1,title.getText().toString(),date2.getText().toString(),time2.getText().toString(),active.isChecked(),active.isChecked(),
                    Integer.parseInt(repeatNum.getText().toString()),repeatTypeText.getText().toString());
            //task = new Task(1,"ABC","2/6/2021","08:56 PM",true,true,10,"Day");
            Toast.makeText(Reminder.this, task.toString(), Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(Reminder.this, "Error", Toast.LENGTH_SHORT).show();
        }
        DBHelper dbHelper = new DBHelper(Reminder.this);
        boolean b = dbHelper.addTask(task);*/
    }
}
