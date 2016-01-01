package com.example.xiao.timer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import java.util.LinkedList;
import java.util.WeakHashMap;

/**
 * Created by xiao on 2015/12/21.
 */
public class MusicPlayer {
    private static WeakHashMap<Context, ServiceBinder> sConnectionMap = new WeakHashMap<>();

    public static ServiceToken bindToService(Context context, ServiceConnection callback) {
        context.startService(new Intent(context, MusicService.class));
        ServiceBinder binder = new ServiceBinder(callback);
        if (context.bindService(new Intent(context,MusicService.class),binder,0)) {
            sConnectionMap.put(context, binder);
            return new ServiceToken();
        }
        return null;
    }

    public static final class ServiceToken {

    }

    public static final class ServiceBinder implements ServiceConnection {
        private final ServiceConnection mCallback;

        public ServiceBinder(ServiceConnection callback) {
            this.mCallback = callback;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mCallback.onServiceConnected(name, service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mCallback.onServiceDisconnected(name);
        }
    }
}
