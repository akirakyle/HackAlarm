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
import android.os.SystemClock;
import java.util.Calendar;
import android.os.CountDownTimer;


public class addActivity extends Activity {

    final static private long ONE_SECOND = 1000;
    int thrity = (int)System.currentTimeMillis() + 30 * 1000;
    Intent listen;
    int currentHour;
    int currentMin;
    TimePicker timePicker;
    Button button;
    PendingIntent pi;
    AlarmManager am;
    BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        button = (Button) findViewById(R.id.button);
        listen = new Intent(this, Listen.class);

        pi = PendingIntent.getBroadcast(
                this,
                0, listen,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        am = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));


        br=new BroadcastReceiver() {
            public void onReceive(Context c, Intent i) {
                System.out.print("@$$$@");
            }
        };

        registerReceiver(br, new IntentFilter("listen") );



    }

    public void clickFunc(View view){

            Intent nextScreen = new Intent(this, MyActivity.class);

            currentHour = timePicker.getCurrentHour();
            currentMin = timePicker.getCurrentMinute();
            MyActivity.alarms[0] = currentHour;
            MyActivity.alarms[1] = currentMin;
            MyActivity.alarms[2] = 1;

            //am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            //        SystemClock.elapsedRealtime() +
            //                10 * 1000, pi );


            System.out.println("@@@");
            try { Thread.sleep(1000); }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("waah");
            startActivity(listen);

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
