package com.cybertech.cyberchat;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFireBaseID extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.i("TAG", "Refreshed token: " + token);
        TinyDB tinyDB = new TinyDB(this);
        tinyDB.putBoolean("tokenReady",true);
        tinyDB.putString("token",token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
       //(refreshedToken);
    }
}
