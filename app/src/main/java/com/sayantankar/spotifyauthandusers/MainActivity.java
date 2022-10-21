package com.sayantankar.spotifyauthandusers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sayantankar.spotifyauthandusers.Auth01.GetOAuthId;
import com.sayantankar.spotifyauthandusers.Auth02.MainActivity01;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button(View view) {
        this.startActivity(new Intent(this, GetOAuthId.class));
    }

    public void button2(View view) {
        this.startActivity(new Intent(this, MainActivity01.class));

    }
}