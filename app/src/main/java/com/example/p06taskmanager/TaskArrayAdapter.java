package com.example.p06taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class TaskArrayAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Task> tasks;
    int resource;
    public TaskArrayAdapter(@NonNull Context context, int resource, ArrayList<Task> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.tasks = tasks;
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);


        TextView tvID = (TextView) rowView.findViewById(R.id.tvID);
        TextView tvTaskName = (TextView) rowView.findViewById(R.id.tvTaskName);
        TextView tvDesc = (TextView)rowView.findViewById(R.id.tvDesc);

        Task task = tasks.get(position);

        tvID.setText(task.getId()+ "");
        tvTaskName.setText(task.getTaskName());
        tvDesc.setText(task.getDescription());

        return rowView;
    }
}
