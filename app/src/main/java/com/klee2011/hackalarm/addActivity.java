package com.klee2011.hackalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.widget.TimePicker;
import java.util.Calendar;

public class addActivity extends Activity {

    int currentHour;
    int currentMin;
    TimePicker timePicker;
    Button button;
    PendingIntent pi;
    AlarmManager am;
    BroadcastReceiver br;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        button = (Button) findViewById(R.id.button);
        Intent listen = new Intent(this, Listen.class);

        pi = PendingIntent.getActivity (this, 0, listen , PendingIntent.FLAG_ONE_SHOT );

        br=new BroadcastReceiver() {
            public void onReceive(Context c, Intent i) {
            }
        };
        am = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));
        registerReceiver(br, new IntentFilter("") );

    }

    public void clickFunc(View view){

            Intent nextScreen = new Intent(this, MyActivity.class);

            currentHour = timePicker.getCurrentHour();
            currentMin = timePicker.getCurrentMinute();
            MyActivity.alarms[0] = currentHour;
            MyActivity.alarms[1] = currentMin;
            MyActivity.alarms[2] = 1;

            cal.set(Calendar.HOUR_OF_DAY, currentHour);
            cal.set(Calendar.MINUTE, currentMin);

            am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pi);

            startActivity(nextScreen);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
