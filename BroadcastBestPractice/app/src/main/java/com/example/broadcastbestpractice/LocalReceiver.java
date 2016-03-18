package com.example.broadcastbestpractice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

import static android.util.Log.d;

/**
 * Created by Nick on 2016/3/3.
 */
public class LocalReceiver extends BroadcastReceiver {
    private static final String TAG = "Nick---LocalReceiver";
    @Override
    public void onReceive(final Context context, Intent intent) {
        d(TAG, "LocalReceiver.onReceive.");
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Offline Alert").
                setMessage("You have to Login again!").
                setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        d(TAG, "LocalReceiver.onClick.context=="+context.toString()+" this=="+this.toString());
                        ActivityCollector.finishAll();
                        Intent intent1 = new Intent(context, LoginActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent1);
                    }
                }).
                setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
    }
}
