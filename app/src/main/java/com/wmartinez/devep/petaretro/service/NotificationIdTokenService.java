package com.wmartinez.devep.petaretro.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by WilsonMartinez on 7/7/2016.
 */
public class NotificationIdTokenService extends FirebaseInstanceIdService {

    private static final String TAG = "FIREBASE TOKEN";

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        sendRegisterToken(token);
    }

    private void sendRegisterToken(String token) {
        Log.d(TAG, token);
    }
}
