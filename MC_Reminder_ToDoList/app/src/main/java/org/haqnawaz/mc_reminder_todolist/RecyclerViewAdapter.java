package org.haqnawaz.mc_reminder_todolist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    List<Task> taskList;
    Activity myActivity;
    TaskListener taskListener;
    public RecyclerViewAdapter(List<Task> taskList, Activity myAct ,TaskListener tskListener) {
        this.taskList = taskList;
        this.myActivity = myAct;
        this.taskListener=tskListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tasks, parent, false);
        return new MyViewHolder(itemView,taskListener);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.data= taskList.get(position);
        holder.textViewTaskName.setText(holder.data.getTitle());
        holder.textViewTaskDate.setText(String.valueOf(holder.data.getDate()));
        holder.textViewTaskTime.setText(holder.data.getTime());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageViewTask;
        TextView textViewTaskName;
        TextView textViewTaskTime;
        TextView textViewTaskDate;
        Task data;
        TaskListener onTaskListener;
        public MyViewHolder(@NonNull View itemView,TaskListener listener) {
            super(itemView);
            imageViewTask = itemView.findViewById(R.id.imageViewTaskPicture);
            textViewTaskName = itemView.findViewById(R.id.textViewTaskName);
            textViewTaskDate = itemView.findViewById(R.id.textViewTaskDate);
            textViewTaskTime= itemView.findViewById(R.id.textViewTime);
            this.onTaskListener=listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTaskListener.onClickListener(getAdapterPosition());
        }

    }
    public interface TaskListener{
        void onClickListener(int position);
    }


}

