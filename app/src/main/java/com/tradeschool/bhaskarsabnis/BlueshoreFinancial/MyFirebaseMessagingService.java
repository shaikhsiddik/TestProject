package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.BlueshoreFinancial.clientapp3.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tradeschool.bhaskarsabnis.BlueshoreFinancial.Tips_Notification;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {

    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        Log.i("Remote Message", remoteMessage.getNotification().getBody().toString());
      //  sendNotification(remoteMessage.getNotification().getBody());
                Tips_Notification notification=new Tips_Notification();
                notification.showAlert();


    }

    private void sendNotification(String remoteMessage) {

        Log.i("AAAAAAAAAAAA","AAAAAAAAAAA");
        Intent i = new Intent(this, Tips_Notification.class);
       /* //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(Tips_Notification.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(i);
        PendingIntent pi = stackBuilder.getPendingIntent( 0, PendingIntent.FLAG_UPDATE_CURRENT
                | PendingIntent.FLAG_ONE_SHOT);*/
       PendingIntent pi=PendingIntent.getActivity(this,0,i,0);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.ic_stat_name);
        notificationBuilder.setContentTitle("Blueshore Financial");
        notificationBuilder.setContentText(remoteMessage);
        notificationBuilder.setAutoCancel(false);
        notificationBuilder.setSound(defaultSoundUri);
       // notificationBuilder.setSound(Uri.parse("android.resource://"+getApplicationContext().getPackageName()+"/"+R.raw.notificationsound));
        notificationBuilder.setContentIntent(pi);
        notificationBuilder.setDefaults(Notification.DEFAULT_ALL|Notification.DEFAULT_VIBRATE);
        notificationBuilder.setPriority(Notification.PRIORITY_HIGH);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH; //Important for heads-up notification
            NotificationChannel channel = new NotificationChannel("data_notification","abcd",importance);
            channel.setDescription("data show");
            channel.setShowBadge(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
                notificationManager.notify(0, notificationBuilder.build());
            }
        }else{

            NotificationManager mNotifyMgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            if (mNotifyMgr != null) {
                mNotifyMgr.notify(0, notificationBuilder.build());
            }
        }

    }
}
