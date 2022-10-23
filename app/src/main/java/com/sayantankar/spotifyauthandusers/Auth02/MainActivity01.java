package com.sayantankar.spotifyauthandusers.Auth02;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sayantankar.spotifyauthandusers.R;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Track;


public class MainActivity01 extends AppCompatActivity {


    private static final String CLIENT_ID = "8e089619777f47a9a0ad9b2e55a08966";
    private static final String CLINT_SECRET = "f5d3d7aae4cd4f169d6506fbbaefa3ff";
    private static final String REDIRECT_URI = "com.sayantankar.spotifyauthandusers://callback";
    private SpotifyAppRemote mSpotifyAppRemote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_o_auth_id);


//        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // Set the connection parameters
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("MainActivity", "Connected! Yay!");

                        Toast.makeText(MainActivity01.this, "\"MainActivity\", \"Connected! Yay!", Toast.LENGTH_SHORT).show();

                        // Now you can start interacting with App Remote
                        connected();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("MainActivity", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
    }

    private boolean appAvailable(String package_name) {
        PackageManager pm = getPackageManager();
        boolean installed;
        try {
            pm.getPackageInfo(package_name, PackageManager.GET_ACTIVITIES);
            installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    @Override
    protected void onStart() {
        super.onStart();

        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
//                        .setClientSecret("<your_client_secret>")
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(getApplicationContext(), connectionParams,
                new Connector.ConnectionListener() {

                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("getData: MainActivity", "Connected! Yay!");
                        // Now you can start interacting with App Remote
                        connected();

                    }

                    public void onFailure(Throwable throwable) {
                        Log.e("MyActivity", throwable.getMessage(), throwable);
                        Toast.makeText(getApplicationContext(), String.valueOf(throwable.getMessage()), Toast.LENGTH_SHORT).show();

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
    }



    @Override
    protected void onStop() {
        super.onStop();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    static final int ALARM_REQ_CODE = 85866;

    private void connected() {
        // Play a playlist
//        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL");
//
//        // Subscribe to PlayerState
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("getData: play song", track.uri + " by " + track.artist);
                        Log.d("getData: play song", String.valueOf(                                               mSpotifyAppRemote.getPlayerApi().getPlayerState().toString()));
                        Toast.makeText(this,  playerState.track.uri, Toast.LENGTH_SHORT).show();
//                        Log.d("MainActivity",   " by " + track.artist.name);
//                        Toast.makeText(this, "Trake Name: "+track.name+"\nartist: "+String.valueOf(track.artist)+"\nuri: "+String.valueOf(track.uri)+"\n imageUri: "+String.valueOf(track.imageUri)+track.album.toString()+track.isEpisode+track.artists, Toast.LENGTH_SHORT).show();
                    }
                });
        mSpotifyAppRemote.getPlayerApi().seekTo(30000);
        mSpotifyAppRemote.getPlayerApi().skipNext();
        mSpotifyAppRemote.getPlayerApi().skipPrevious();
        mSpotifyAppRemote.getPlayerApi().skipToIndex("music uri",0);
        mSpotifyAppRemote.getPlayerApi().pause();
        mSpotifyAppRemote.getPlayerApi().resume();



//        mSpotifyAppRemote.getPlayerApi().getPlayerState();

//        Intent intent = new Intent(MainActivity.this, SpotifyService.class);
//        stopService(intent);
//
//        startService(new Intent(MainActivity.this, SpotifyService.class));
//        Toast.makeText(this, "jjjjjjjjjjjj", Toast.LENGTH_SHORT).show();

//        int timee = 3000;
//        long triggertime = System.currentTimeMillis()+timee;
//
//        Intent iBrodcast = new Intent(MainActivity.this, SpotifyRecever.class);
//
//        PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this,ALARM_REQ_CODE,iBrodcast,PendingIntent.FLAG_UPDATE_CURRENT);
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC_WAKEUP,triggertime, pi);

//        ffffffloo(MainActivity.this);

//        int timee = 3000;
//        long triggertime = System.currentTimeMillis()+timee;
//
//        Intent iBrodcast = new Intent(this, SpotifyRecever.class);
//
//        PendingIntent pi = PendingIntent.getBroadcast(this,ALARM_REQ_CODE,iBrodcast,PendingIntent.FLAG_UPDATE_CURRENT);
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC_WAKEUP,triggertime, pi);



//        startService(new Intent(MainActivity.this, Spotify.class));
////        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
////        Toast.makeText(this, track.name + " by " + track.artist, Toast.LENGTH_SHORT).show();
    }
}