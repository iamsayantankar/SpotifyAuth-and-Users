package com.sayantankar.spotifyauthandusers.Auth03;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Track;

public class MyReceiver extends BroadcastReceiver {

    private static final String CLIENT_ID = "8147c0be26224f1e95caa48d87bf88d0";
    private static final String CLINT_SECRET = "a1045590f2244de2a58479d30bd51d43";
    private static final String REDIRECT_URI = "com.wgccbank.spotify://callback";
    private SpotifyAppRemote mSpotifyAppRemote;
    private Context context;
    AlarmManager alarmManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");

//        context_1.startService(new Intent(context_1, Spotify.class));

//        int time = 15;
//        long triggerTime = System.currentTimeMillis()+(time*1000);
//
//        Intent intent = new Intent(context_1,MyReceiver.class);
//        PendingIntent pi = PendingIntent.getBroadcast(context_1,100, intent,0);
//
//        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pi);
//        context = context_1;
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
//                        .setClientSecret("<your_client_secret>")
                        .showAuthView(true)
                        .build();

        String trackId = intent.getStringExtra("id");
        Log.d("getData", trackId);

        SpotifyAppRemote.connect(context, connectionParams,
                new Connector.ConnectionListener() {

                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("getData", "Connected! Yay!");
//                        Toast.makeText(Spotify.this, "kkk  "+String.valueOf(mSpotifyAppRemote), Toast.LENGTH_SHORT).show();
                        // Now you can start interacting with App Remote
//                        connected();
                        Toast.makeText(context, "Player Api: "+ mSpotifyAppRemote.getPlayerApi().toString(), Toast.LENGTH_SHORT).show();


                    }

                    public void onFailure(Throwable throwable) {
                        Log.e("getData", throwable.getMessage(), throwable);
                        Toast.makeText(context, String.valueOf(throwable.getMessage()), Toast.LENGTH_SHORT).show();

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
    }

    private void connected() {
        // Play a playlist
//        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL");
        Toast.makeText(context, "Player Api: "+ mSpotifyAppRemote.getPlayerApi().toString(), Toast.LENGTH_SHORT).show();

        if(mSpotifyAppRemote.getPlayerApi().getPlayerState().isCanceled()){
            Toast.makeText(context, "track.name" + " by " , Toast.LENGTH_SHORT).show();
        }
        // Subscribe to PlayerState
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("getData", track.name + " by " + track.artist);
                        Toast.makeText(context, track.name + " by " + track.artist.name, Toast.LENGTH_SHORT).show();
//                        Log.d("SPOTIFY",   " by " + track.artist.name);
//                        Toast.makeText(this, "Trake Name: "+track.name+"\nartist: "+String.valueOf(track.artist)+"\nuri: "+String.valueOf(track.uri)+"\n imageUri: "+String.valueOf(track.imageUri)+track.album.toString()+track.isEpisode+track.artists, Toast.LENGTH_SHORT).show();
                    }
                });

//        startService(new Intent(Spotify.this, Spotify.class));
//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, track.name + " by " + track.artist, Toast.LENGTH_SHORT).show();
    }
}