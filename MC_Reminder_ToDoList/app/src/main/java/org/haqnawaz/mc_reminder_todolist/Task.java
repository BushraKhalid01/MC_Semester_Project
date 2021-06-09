package org.haqnawaz.mc_reminder_todolist;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
    private int Id;
    private String title;
    private String time;
    private String date;
    private boolean repeat;
    private boolean active;
    private int intervals;
    private String intervalType;
    private String FirstLetter;


    public Task(int id, String title, String time, String date, boolean repeat, boolean active, int intervals, String intervalType) {
        this.title = title;
        this.time = time;
        this.date = date;
        this.repeat = repeat;
        this.active = active;
        this.intervals = intervals;
        this.intervalType = intervalType;
        this.Id = id;
    }

    protected Task(Parcel in) {
        Id = in.readInt();
        title = in.readString();
        time = in.readString();
        date = in.readString();
        repeat = in.readByte() != 0;
        active = in.readByte() != 0;
        intervals = in.readInt();
        intervalType = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
    public String getFirstLetter() {
        return FirstLetter;
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
    public int getIntervals() {
        return intervals;
    }

    public String getIntervalType() {
        return intervalType;
    }

    public int getId() {
        return Id;
    }

    public void setFirstLetter(String letter) {
        this.FirstLetter=FirstLetter;
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

    public void setIntervals(int intervals) {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(title);
        parcel.writeString(time);
        parcel.writeString(date);
        parcel.writeByte((byte) (repeat ? 1 : 0));
        parcel.writeByte((byte) (active ? 1 : 0));
        parcel.writeInt(intervals);
        parcel.writeString(intervalType);
    }
}
