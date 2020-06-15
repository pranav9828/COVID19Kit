package com.corona.covid_19explorer.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.corona.covid_19explorer.Classes.AlarmReceiver;
import com.corona.covid_19explorer.R;

import java.util.Calendar;


public class WorkReminder extends AppCompatActivity{

    private int notificationId = 1;
    EditText reminder_text;
    TimePicker timePicker;
    Button setReminder, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_reminder);
        getSupportActionBar().setTitle("Create reminders");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        createNotificationChannel();
        reminder_text = (EditText) findViewById(R.id.reminder_text);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        setReminder = (Button) findViewById(R.id.set_button);
        cancel = (Button) findViewById(R.id.cancel_button);

        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (reminder_text.getText().toString().isEmpty()){
                    reminder_text.setError("Fields can't be empty.");
                    reminder_text.setFocusable(true);
            }
            else {
                Toast.makeText(WorkReminder.this, "Reminder Set!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WorkReminder.this, AlarmReceiver.class);
                intent.putExtra("notificationId", notificationId);
                intent.putExtra("todo", reminder_text.getText().toString());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(WorkReminder.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // Set alarm.
                // set(type, milliseconds, intent)
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
            }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WorkReminder.this, "Reminder cancelled!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WorkReminder.this, AlarmReceiver.class);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(WorkReminder.this, 0, intent, 0);
                alarmManager.cancel(pendingIntent);
            }
        });
    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence title = "Reminder!";
            String description = "test";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("reminder", title, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
