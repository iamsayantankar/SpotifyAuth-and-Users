package com.sayantankar.spotifyauthandusers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sayantankar.spotifyauthandusers.Auth01.GetOAuthId;
import com.sayantankar.spotifyauthandusers.Auth02.MainActivity01;
import com.sayantankar.spotifyauthandusers.Auth03.MyBroadcastReceiver;
import com.sayantankar.spotifyauthandusers.Auth03.MyBroadcastReceiverSpotify;
import com.sayantankar.spotifyauthandusers.Auth03.MyReceiver;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<BroadcastReceiver> receivers = new ArrayList<BroadcastReceiver>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button1(View view) {
        this.startActivity(new Intent(this, GetOAuthId.class));
    }

    public void button2(View view) {
        this.startActivity(new Intent(this, MainActivity01.class));

    }

    public void button3(View view) {

//        IntentFilter filter1 = new IntentFilter();
//        filter1.addAction("com.spotify.music.metadatachanged");
//
//        MyReceiver myReceiver = new MyReceiver();
//        registerReceiver(myReceiver, filter1);
//        Log.d("getData", "track.name +   + track.artist");


        IntentFilter filter1 = new IntentFilter();
        filter1.addAction("com.spotify.music.metadatachanged");

        MyBroadcastReceiver myReceiver = new MyBroadcastReceiver();
        boolean registered = receivers.contains(myReceiver);
        if (registered) {
            unregisterReceiver(myReceiver);
            Log.d("getData", "Unregister   01");

        }

        registerReceiver(myReceiver, filter1);

        if (myReceiver!=null) {
            try {
                unregisterReceiver(myReceiver);
                Log.d("getData", "Unregister   02");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        registerReceiver(myReceiver, filter1);
        Log.d("getData", "track.name +   + track.artist");
    }
}