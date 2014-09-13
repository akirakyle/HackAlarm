package com.klee2011.hackalarm;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Listen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listen);
        Button listen = (Button) findViewById(R.id.listen);
        final TextView listenStat = (TextView) findViewById(R.id.listenStat);
        final TextView listenResults = (TextView) findViewById(R.id.listenResults);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();

        class MyRecognitionListener implements RecognitionListener {

            @Override
            public void onBeginningOfSpeech() {
                listenStat.setText("Speech Detected");
                Log.d("Speech", "onBeginningOfSpeech");
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                //listenStat.setText("onBufferReceived");
                Log.d("Speech", "onBufferReceived");
            }

            @Override
            public void onEndOfSpeech() {
                listenStat.setText("End of Speech Detected");
                Log.d("Speech", "onEndOfSpeech");
            }

            @Override
            public void onError(int error) {
                String str = "onError: " + Integer.toString(error);
                Log.d("Speech", str);
                //listenStat.setText(str);
            }

            @Override
            public void onEvent(int eventType, Bundle params) {
                listenStat.setText("onEvent");
                Log.d("Speech", "onEvent");
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                //listenStat.setText("onPartialResults");
                Log.d("Speech", "onPartialResults");
            }

            @Override
            public void onReadyForSpeech(Bundle params) {
                listenStat.setText("Ready For Speech");
                Log.d("Speech", "onReadyForSpeech");
            }


            @Override
            public void onResults(Bundle results) {
                listenStat.setText("Results:");
                Log.d("Speech", "onResults");
                ArrayList<String> strResults = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                for (int i = 0; i < strResults.size();i++ ) {
                    //Log.d("Speech", "result=" + strResults.get(i));
                    listenResults.setText(strResults.get(i));
                }
                Log.d("Speech", Integer.toString(listenResults.getText().length()));
                if (listenResults.getText().toString().equalsIgnoreCase("I am a lazy human")) {
                    listenStat.append(" - congratulations, you are a little less lazy");
                    r.stop();
                    try {
                        Thread.sleep(5000);                 //1000 milliseconds is one second.
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    returnScreen();
                }
                else {
                    listenStat.append(" - try again, you lazy human :(");
                }
            }

            @Override
            public void onRmsChanged(float rmsdB) {
                //listenStat.setText("onRmsChanged");
                //Log.d("Speech", "onRmsChanged");
            }

        }
        //Listening to button event
        listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpeechRecognizer sr = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
                MyRecognitionListener listener = new MyRecognitionListener();
                sr.setRecognitionListener(listener);
                sr.startListening(RecognizerIntent.getVoiceDetailsIntent(getApplicationContext()));
            }
        });
    }

    public void returnScreen(){
        Intent nextScreen = new Intent(this, MyActivity.class);
        startActivity(nextScreen);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.speech_recognizer, menu);
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