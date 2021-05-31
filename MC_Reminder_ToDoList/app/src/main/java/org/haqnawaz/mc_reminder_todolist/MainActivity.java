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
import android.widget.Button;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setListAdapter();
    }
    private void setListAdapter(){
        Task t1 = new Task(-1, "abc", "14:2:46", "29/05/2021", true, true, "4", "monthly");
        Task t2 = new Task(-1, "def", "14:2:46", "29/05/2021", true, true, "4", "monthly");
        Task t3 = new Task(-1, "ghi", "14:2:46", "29/05/2021", true, true, "4", "monthly");
        Task t4 = new Task(-1, "jkl", "14:2:46", "29/05/2021", true, true, "4", "monthly");
        taskList = new ArrayList<Task>();
        taskList.addAll(Arrays.asList(new Task[]{t1, t2, t3, t4}));
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
}