package com.example.p06taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {
    Button btnAdd, btnCancel;
    EditText etName, etDes, etRemind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        etName = findViewById(R.id.etName);
        etDes = findViewById(R.id.etDes);
        etRemind = findViewById(R.id.etRemind);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskName = etName.getText().toString().trim();
                String desc = etDes.getText().toString().trim();
                String time = etRemind.getText().toString();
                int timeInt = Integer.parseInt(time);
                DBHelper dbh = new DBHelper(AddTaskActivity.this);
                long insert = dbh.insertTask(taskName, desc);
                if(insert != -1){
                    Toast.makeText(AddTaskActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.SECOND, timeInt);

                    Intent i = new Intent(AddTaskActivity.this, ScheduledNotificationReceiver.class);
                    int reqCode = 12345;
                    i.putExtra("name", taskName);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(AddTaskActivity.this,reqCode,
                            i, PendingIntent.FLAG_CANCEL_CURRENT);

                    AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
