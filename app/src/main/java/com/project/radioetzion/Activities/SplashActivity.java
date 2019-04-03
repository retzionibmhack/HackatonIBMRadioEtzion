package com.project.radioetzion.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.project.radioetzion.Fragments.HomeFragment;
import com.project.radioetzion.MainActivity;
import com.project.radioetzion.Model.JSONData;
import com.project.radioetzion.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.BreakIterator;

public class SplashActivity extends AppCompatActivity {

    private String jsonString;
    private FirebaseDatabase database;
    public DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseApp.initializeApp(this);

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(500);
                    Intent intent = new Intent(getApplicationContext(), BottomActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };



        myThread.start();

    }





}