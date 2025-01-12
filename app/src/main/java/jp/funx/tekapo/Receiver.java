package jp.funx.tekapo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Receiver extends android.content.BroadcastReceiver {

    final static String TAG = "Receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"onReceive() - " + intent.getAction() );

        /*
        Intent activityIntent = new Intent(context, PINActivity.class);
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activityIntent);
        */
        MainActivity.startService(context);
    }
}
