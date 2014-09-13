package com.klee2011.hackalarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.Menu;
import android.view.MenuItem;
import android.speech.RecognitionListener;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;


public class Listen extends Activity {

    Button listen_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listen);

        listen_button = (Button) findViewById(R.id.button);

        //Listening to button event
        listen_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpeechRecognizer sr = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
                sr = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
                MyRecognitionListener listener = new MyRecognitionListener();
                sr.setRecognitionListener((RecognitionListener) listener);
                sr.startListening(RecognizerIntent.getVoiceDetailsIntent(getApplicationContext()));
            }
        });
    }
}
