package com.example.bmob.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.JsonToken;
import android.widget.Toast;

import com.example.bmob.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import cn.bmob.push.PushConstants;

/**
 * Created by bear on 2016/5/14.
 */
public class PushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message="";
        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
            String msg=intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING);
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            JSONTokener jsonTokener=new JSONTokener(msg);
            try {
                JSONObject object= (JSONObject) jsonTokener.nextValue();
                message=object.getString("alert");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            NotificationManager manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification=new Notification(R.mipmap.ic_launcher,"TestBmob",System.currentTimeMillis());
            notification.setLatestEventInfo(context,"BmobText",message,null);
            manager.notify(R.mipmap.ic_launcher,notification);
        }

    }
}
