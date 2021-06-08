package org.haqnawaz.mc_reminder_todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.TaskListener {

    Button add_btn;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Task> taskList = new ArrayList<Task>();
    private Toolbar toolbar;
    ArrayAdapter<Task> arrayAdapter;
    TextView NO_REMINDER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        NO_REMINDER=findViewById(R.id.no_reminder_text);
        setListAdapter();
    }
    private void setListAdapter(){


        DBHelper dbHelper = new DBHelper(MainActivity.this);
        taskList = dbHelper.getAllTasks();
        if (taskList.size()!=0){
            NO_REMINDER.setVisibility(View.INVISIBLE);
        }
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
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        setListAdapter();
    }
}