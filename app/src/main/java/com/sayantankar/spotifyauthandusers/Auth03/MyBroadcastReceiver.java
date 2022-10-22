package com.sayantankar.spotifyauthandusers.Auth03;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.sayantankar.spotifyauthandusers.R;

public class MyBroadcastReceiver extends BroadcastReceiver {


    static final class BroadcastTypes {
        static final String SPOTIFY_PACKAGE = "com.spotify.music";
        static final String PLAYBACK_STATE_CHANGED = SPOTIFY_PACKAGE + ".playbackstatechanged";
        static final String QUEUE_CHANGED = SPOTIFY_PACKAGE + ".queuechanged";
        static final String METADATA_CHANGED = SPOTIFY_PACKAGE + ".metadatachanged";
    }

    String bb="tftfrtf";
    NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("getData 1", "Receiving a data");


        // This is sent with all broadcasts, regardless of type. The value is taken from
        // System.currentTimeMillis(), which you can compare to in order to determine how
        // old the event is.
        long timeSentInMs = intent.getLongExtra("timeSent", 0L);

        String action = intent.getAction();

        if (action.equals(BroadcastTypes.METADATA_CHANGED)) {
            String trackId = intent.getStringExtra("id");
            String artistName = intent.getStringExtra("artist");
            String albumName = intent.getStringExtra("album");
            String trackName = intent.getStringExtra("track");
            int trackLengthInSec = intent.getIntExtra("length", 0);
            bb = trackId;
            // Do something with extracted information...
        } else if (action.equals(BroadcastTypes.PLAYBACK_STATE_CHANGED)) {
            boolean playing = intent.getBooleanExtra("playing", false);
            int positionInMs = intent.getIntExtra("playbackPosition", 0);
            // Do something with extracted information
            bb = String.valueOf(playing);

        } else if (action.equals(BroadcastTypes.QUEUE_CHANGED)) {
            // Sent only as a notification, your app may want to respond accordingly.
            bb = String.valueOf("ggg");

        }



        Log.d("getData 2", bb);



        NotificationCompat.Builder notificationBuilder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notificationBuilder = new NotificationCompat.Builder(context, "FCM")
                    .setContentTitle("Title")
                    .setContentText(bb)
                    .setAutoCancel(false)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    //.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.win))
//					.setContentIntent(pendingIntent)
//					.setContentInfo("Hello")
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setColor(Color.BLUE)
                    .setLights(Color.RED, 1000, 300)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setNumber(100)
                    .setSmallIcon(R.drawable.ic_launcher_background);
        }else{
            notificationBuilder = new NotificationCompat.Builder(context, "FCM")
                    .setContentTitle("Title Icon")
                    .setContentText(bb)
                    .setAutoCancel(false)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    //.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.win))
//					.setContentIntent(pendingIntent)
//					.setContentInfo("Hello")
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setLights(Color.RED, 1000, 300)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setNumber(100)
                    .setSmallIcon(R.drawable.ic_launcher_background);
        }

//        try {
//            String picture = notification.getImageUrl().toString();
//            if (picture != null && !"".equals(picture)) {
//                URL url = new URL(picture);
//                Bitmap bigPicture = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                notificationBuilder.setStyle(
//                        new NotificationCompat.BigPictureStyle().bigPicture(bigPicture).setSummaryText(notification.getBody())
//                );
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(
//                    "FCM", "CHANNEL_NAME", NotificationManager.IMPORTANCE_DEFAULT
//            );
//            channel.setDescription("CHANNEL_DESC");
//            channel.setShowBadge(true);
//            channel.canShowBadge();
//            channel.enableLights(true);
////            channel.setLightColor(Color.RED);
//            channel.enableVibration(true);
//            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500});
//
////            assert notificationManager != null;
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        assert notificationManager != null;
//        notificationManager.notify(0, notificationBuilder.build());

//        if(data!=null){
//            if(data.get("user_uid")!=null){
//                String user_uid = data.get("user_uid");
//                if(!user_uid.equals(url_list.MyUserUid)){
//
//                    notificationManager.notify(0, notificationBuilder.build());
//                }
//            }else{
//                notificationManager.notify(0, notificationBuilder.build());
//            }
//        }else{
//            notificationManager.notify(0, notificationBuilder.build());
//        }

    }
}