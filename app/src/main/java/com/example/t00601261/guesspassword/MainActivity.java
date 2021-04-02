package com.example.t00601261.guesspassword;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final long RANGE = 100000000;
    private long password;
    TextView textView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        password = (int) (Math.random() * RANGE);
        Log.v("GuessingGame", "Password is- " + password);

        textView = findViewById(R.id.result);

    }

    public void guessPass(View view) {

        textView.setText("Searching...");
        Unfreeze unfreeze = new Unfreeze();
        unfreeze.execute();
    }


    private class Unfreeze extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... integers) {
            int guess = 0;
            long count = 0;
            while (guess != password) {
                guess = (int) (Math.random() * RANGE);
                count++;
                float ratio = (count * 100 / RANGE);

                if (count % 1000 == 0) {
                    publishProgress((int) ratio);
                }
            }
            return guess;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(100);
            progressBar.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            textView.setText("Found it! " + integer);
            super.onPostExecute(integer);
        }
    }
}


