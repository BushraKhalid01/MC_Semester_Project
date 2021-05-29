package org.haqnawaz.mc_reminder_todolist;

import androidx.annotation.NonNull;


public class Task {
    private int Id;
    private String title;
    private String time;
    private String date;
    private boolean repeat;
    private boolean active;
    private String intervals;
    private String intervalType;

    public Task(int id, String title, String time, String date, boolean repeat, boolean active, String intervals, String intervalType) {
        this.title = title;
        this.date = time;
        this.date = date;
        this.repeat = repeat;
        this.active = active;
        this.intervals = intervals;
        this.intervalType = intervalType;
        this.Id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public boolean isActive() {
        return active;
    }
    public String getIntervals() {
        return intervals;
    }

    public String getIntervalType() {
        return intervalType;
    }

    public int getId() {
        return Id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }

    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", String=" + time +
                ", String=" + date +
                ", repeat=" + repeat +
                ", intervals='" + intervals + '\'' +
                ", intervalType='" + intervalType + '\'' +
                ", Id=" + Id +
                '}';
    }
}
