package com.kpu.cashwalktmap;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 * Created by ydwin on 2016-08-24.
 */

// token을 얻기위한 class

public class RegistrationIntentService extends IntentService {
    private static final String TAG = "MyInstanceIDService";

    public RegistrationIntentService()    {
        super(TAG);
        Log.d(TAG, "RegistrationIntentService()");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            //TODO server로 token 전송
            Log.i(TAG, "token: " + token);
        }
        catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
        }
    }
}