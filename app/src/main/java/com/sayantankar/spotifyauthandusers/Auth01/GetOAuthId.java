package com.sayantankar.spotifyauthandusers.Auth01;

import static java.util.jar.Pack200.Packer.ERROR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.sayantankar.spotifyauthandusers.R;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

public class GetOAuthId extends AppCompatActivity {

    private SpotifyAppRemote mSpotifyAppRemote;

    private static final String clientId = "8e089619777f47a9a0ad9b2e55a08966";
    private static final String clientSecret = "f5d3d7aae4cd4f169d6506fbbaefa3ff";
    private static final String redirectUri = "com.sayantankar.spotifyauthandusers://callback";
    private static final int REQUEST_CODE = 1337;

//    private SharedPreferences.Editor editor;
//    private SharedPreferences msharedPreferences;

//    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_o_auth_id);

        authenticateSpotify();
        Toast.makeText(this, "jjjjjjjjjjjj", Toast.LENGTH_SHORT).show();

//        msharedPreferences = this.getSharedPreferences("SPOTIFY", 0);
//        queue = Volley.newRequestQueue(this);
    }

    private void authenticateSpotify() {
        AuthorizationRequest.Builder builder =
                new AuthorizationRequest.Builder(clientId, AuthorizationResponse.Type.TOKEN, redirectUri);

        builder.setScopes(new String[]{"streaming"});
        AuthorizationRequest request = builder.build();

        AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, intent);
            Log.d("getData: STARTING", String.valueOf(response));

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
//                    editor = getSharedPreferences("SPOTIFY", 0).edit();
//                    editor.putString("token", response.getAccessToken());
                    Log.d("getData: STARTING", "GOT AUTH TOKEN");
                    Log.d("getData: tokentoken", response.getAccessToken());
                    Log.d("getData: response", String.valueOf(response));
                    Log.d("getData: STARTING", "GOT AUTH TOKEN");
//                    editor.apply();
                    Toast.makeText(this, response.getAccessToken(), Toast.LENGTH_SHORT).show();
//                    waitForUserInfo();
                    break;

                // Auth flow returned an error
                case ERROR:
                    Log.d("getData: STARTING", "Error");
                    Log.d("getData: STARTING", String.valueOf(ERROR));
                    // Handle error response
                    break;

                // Most likely auth flow was cancelled
                default:
                    break;
                    // Handle other cases
            }
//            startService(new Intent(MainActivity2.this, SpotifyService.class));

        }

    }

//    private void waitForUserInfo() {
//        GetOAuth_UserService getOAuth_userService = new GetOAuth_UserService(queue, msharedPreferences);
//        getOAuth_userService.get(() -> {
//            GetOAuth_User getOAuth_user = getOAuth_userService.getUser();
//            editor = getSharedPreferences("SPOTIFY", 0).edit();
//            Log.d("getData: userid", getOAuth_user.id);
//
//            editor.putString("userid", getOAuth_user.id);
//            Log.d("STARTING", "GOT USER INFORMATION");
//            // We use commit instead of apply because we need the information stored immediately
//            editor.commit();
////            startMainActivity();
//        });
//    }
}