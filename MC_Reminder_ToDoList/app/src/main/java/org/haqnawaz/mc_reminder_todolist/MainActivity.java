package org.haqnawaz.mc_reminder_todolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.TaskListener {

    Button add_btn;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Task> taskList = new ArrayList<Task>();
    private Toolbar toolbar;
    boolean b;

    DBHelper dbHelper = new DBHelper(MainActivity.this);
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        if(savedInstanceState==null) {
            taskList = dbHelper.getAllTasks();
        }
        else{
            cancelAlarms();
            taskList=savedInstanceState.getParcelableArrayList("taskList");
        }
        setListAdapter();
        setAlarms();

    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("taskList", (ArrayList<Task>) taskList);
    }
    private void setListAdapter(){

        recyclerView = findViewById(R.id.list);

        //recyclerView.setHasFixedSize(true);

        //LinearLayoutManager GridLayoutManager

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(taskList,MainActivity.this,this) {

        };

        recyclerView.setAdapter(adapter);
    }

    public void AddReminder(View view) {
        Intent intent = new Intent(MainActivity.this, Reminder.class);
        startActivity(intent);
    }

    @Override
    public void onClickListener(int position) {
        Intent intent = new Intent(MainActivity.this, Reminder.class);
        intent.putExtra("EditTask",taskList.get(position));
        startActivity(intent);

    }
    @Override
    protected void onResume()
    {
        Log.d("ALC","RESUME");
        super.onResume();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setAlarm(Calendar calendar,Task task) {
        Toast.makeText(this, "hhh",Toast.LENGTH_SHORT).show();
        AlarmManager alarmMgr;
        PendingIntent alarmIntent;
        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("Task",task);
        alarmIntent = PendingIntent.getBroadcast(this, task.getId(), intent, 0);
        Log.d("ms", String.valueOf(calendar.getTimeInMillis()));
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),1,
                 alarmIntent);
    }

 

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setAlarms(){
        Calendar calender=Calendar.getInstance();

        for(int i=0;i<taskList.size();i++){
              Task t=taskList.get(i);
              long hour=Integer.parseInt(t.getTime().split(":")[0].trim());
              long min=Integer.parseInt(t.getTime().split(":")[1].split(" ")[1].trim());
              calender.set(Calendar.HOUR_OF_DAY, (int)hour);
              calender.set(Calendar.MINUTE, (int)min);
              calender.set(Calendar.DAY_OF_MONTH, Integer.parseInt(t.getDate().split("/")[0].trim()));
              calender.set(Calendar.MONTH, Integer.parseInt(t.getDate().split("/")[1].trim()));
              calender.set(Calendar.YEAR, Integer.parseInt(t.getDate().split("/")[2].trim()));
              long seconds=(hour*3600)+(min*60);
              calender.setTimeInMillis(seconds* 1000);
              if(!calender.before(Calendar.getInstance())){
                  setAlarm(calender,t);
              }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}