package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;


public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private ProgressBar progressBar;
    Integer count =0;



    SimpleAsyncTask(TextView tv, ProgressBar pb) {
        mTextView = new WeakReference<>(tv);
        this.progressBar = pb;
    }

    @Override
    protected String doInBackground(Void... voids) {
        count =1;
        Random r = new Random();
        int n = r.nextInt(11);

        int s = n * 200;
        for (; count <= n; count++){
            try {

                Thread.sleep(200);
                publishProgress(count);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        return "Awake at last after sleeping for " + s + " milliseconds!";

    }

    protected void onProgressUpdate(Integer... value) {
        //super.onProgressUpdate(value);
        //Log.i("makemachine", "OnProgressUpdate:" + String.valueOf(progress[0]));
        //mTextView.get().setText(progress + "%");
        progressBar.setProgress(value[0]);
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
        progressBar.setVisibility(View.GONE);
    }
}
