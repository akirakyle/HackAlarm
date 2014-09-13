package com.klee2011.hackalarm;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;



public class MyActivity extends Activity {

    public static int[] alarms = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        TextView time = (TextView) findViewById(R.id.time0);

        if (alarms[2]>0) {
            String result = "";
            result = result + Integer.toString(alarms[0]);
            result = result + " : ";
            result = result + Integer.toString(alarms[1]);
            time.setText(result);
            time.setTextSize(60);
        } else {
            time.setText("New Alarm");
            time.setTextSize(30);
        }
    }

    public void add(View view){
                Intent nextScreen = new Intent(this, addActivity.class);
                startActivity(nextScreen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
