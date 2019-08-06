package com.ls.dareman.Notifications;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class DMFirebaseMessagingService extends FirebaseMessagingService {
    private static String TAG = "DMFirebaseMessagingService";


    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage != null) {
            Log.d(TAG, "From: " + remoteMessage.getFrom());

            RemoteMessage.Notification notification = remoteMessage.getNotification();
            if (notification != null) {
                Log.d(TAG, "notification: " + notification);
            }

            Map<String, String> data = remoteMessage.getData();
            if (data != null) {
                Log.d(TAG, "data: " + data);
            }
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}
