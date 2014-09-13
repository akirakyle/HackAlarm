package com.klee2011.hackalarm;

import android.app.Activity;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.speech.RecognitionListener;


public class Listen extends Activity {
    private SpeechRecognizer sr;

    public void onClick(View view) {
        sr = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        MyRecognitionListener listener = new MyRecognitionListener();
        sr.setRecognitionListener((RecognitionListener) listener);
        sr.startListening(RecognizerIntent.getVoiceDetailsIntent(getApplicationContext()));
    }
}
