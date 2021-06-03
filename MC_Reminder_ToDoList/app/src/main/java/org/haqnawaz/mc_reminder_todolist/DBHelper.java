package org.haqnawaz.mc_reminder_todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TASK_TITLE = "title";
    public static final String TASK_DATE = "date";
    public static final String TASK_TIME = "time";
    public static final String TASK_REPEAT = "repeat";
    public static final String TASK_ACTIVE = "active";
    public static final String TASK_INTERVALS = "interval";
    public static final String TASK_INTERVAL_TYPE = "intervalType";
    public static final String TASK_TABLE = "TaskTable";
    public static final String TASK_ID = "Id";

    public DBHelper(@Nullable Context context) {
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ALARM_TABLE =  "CREATE TABLE " + TASK_TABLE + " (" +
                 TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TASK_TITLE + " TEXT , " +
                TASK_TIME + " TEXT , " +
                TASK_DATE + " TEXT , " +
                TASK_REPEAT + " BOOL , " +
                TASK_INTERVALS + " INT , " +
                TASK_INTERVAL_TYPE + " TEXT , " +
                TASK_ACTIVE + " BOOL )";
        db.execSQL(SQL_CREATE_ALARM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TASK_TITLE, task.getTitle());
        cv.put(TASK_TIME, task.getTime());
        cv.put(TASK_DATE, task.getDate());
        cv.put(TASK_REPEAT, task.isRepeat());
        cv.put(TASK_ACTIVE, task.isActive());
        cv.put(TASK_INTERVALS, task.getIntervals());
        cv.put(TASK_INTERVAL_TYPE, task.getIntervalType());

        long insert = db.insert(TASK_TABLE, null, cv);
        if (insert == -1) { return false; }
        else{return true;}
    }
    public boolean deleteTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        long delete= db.delete(TASK_TABLE,TASK_ID+"=?",new String[]{String.valueOf(task.getId())});
        if (delete == -1) { return false; }
        else{return true;}
    }
    public boolean updateTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TASK_TITLE, task.getTitle());
        cv.put(TASK_TIME, task.getTime());
        cv.put(TASK_DATE, task.getDate());
        cv.put(TASK_REPEAT, task.isRepeat());
        cv.put(TASK_ACTIVE, task.isActive());
        cv.put(TASK_INTERVALS, task.getIntervals());
        cv.put(TASK_INTERVAL_TYPE, task.getIntervalType());
        long update= db.update(TASK_TABLE,cv, TASK_ID+"=?",new String[]{String.valueOf(task.getId())});
        if (update == -1) { return false; }
        else{return true;}
    }
    public List<Task> getAllTasks(){
        List<Task> myList=new ArrayList<>();
        String query="SELECT * FROM "+TASK_TABLE;
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor cursor=DB.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do{
                int taskId=cursor.getInt(0);
                String taskTitle=cursor.getString(1);
                String taskTime =cursor.getString(2);
                String taskDate =cursor.getString(3);
                Boolean taskRepeat=cursor.getInt(4)==1?true:false;
                int interval=cursor.getInt(5);
                String intervalType=cursor.getString(6);
                Task taskModel=new Task(taskId,taskTitle,taskTime,taskDate, taskRepeat, taskRepeat, interval, intervalType);
                myList.add(taskModel);
            }while (cursor.moveToNext());
        }
        cursor.close();;
        DB.close();
        return myList;
    }
    public void deleteTask(int id){
        SQLiteDatabase DB =this.getReadableDatabase();
        DB.delete(TASK_TABLE,"TASK_ID=?",new String[] {String.valueOf(id)});
        DB.close();
    }
}
