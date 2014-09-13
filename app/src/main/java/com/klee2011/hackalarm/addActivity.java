package com.klee2011.hackalarm;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.widget.TimePicker;

public class addActivity extends Activity {

    int currentHour;
    int currentMin;
    TimePicker timePicker;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        currentHour = timePicker.getCurrentHour();
        currentMin = timePicker.getCurrentMinute();
        button = (Button) findViewById(R.id.button);

        //Listening to button event
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), MyActivity.class);

                //Sending data to another Activity
                nextScreen.putExtra("hour", new Integer(currentHour).toString());
                nextScreen.putExtra("minute", new Integer(currentMin).toString());

                startActivity(nextScreen);

            }
        });
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
