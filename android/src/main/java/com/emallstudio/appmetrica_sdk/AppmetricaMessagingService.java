package com.emallstudio.appmetrica_sdk;

import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.yandex.metrica.push.YandexMetricaPush;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.yandex.metrica.push.firebase.MetricaMessagingService;

public class AppmetricaMessagingService extends FirebaseMessagingService {

    public static final String ACTION_REMOTE_MESSAGE =
            "io.flutter.plugins.firebasemessaging.NOTIFICATION";
    public static final String EXTRA_REMOTE_MESSAGE = "notification";

    @Override
    public void onNewToken(String token) {
        new MetricaMessagingService().onNewToken(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage message) {
        new MetricaMessagingService().processPush(this, message);
        if(message.getData().containsKey("yamp")) {
            new MetricaMessagingService().processPush(this, message);
        } else {
            Intent intent = new Intent(ACTION_REMOTE_MESSAGE);
            intent.putExtra(EXTRA_REMOTE_MESSAGE, message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }
}
