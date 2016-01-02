package com.example.xiao.timer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.RemoteException;

import java.lang.ref.WeakReference;

/**
 * Created by xiao on 2015/12/21.
 */
public class MusicService extends Service {

    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusListener;

    private IBinder mBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new ServiceStub(this);
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {

            }
        };

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 0;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void play() {
        if (mAudioManager.requestAudioFocus(mAudioFocusListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN) != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            return;
        }




    }
    private static final class ServiceStub extends ITimerService.Stub {
        WeakReference<MusicService> mService;

        public ServiceStub(MusicService service) {
            this.mService = new WeakReference<>(service);
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }

        @Override
        public void play() throws RemoteException {
            MusicService service = mService.get();
            if (service != null) {
                service.play();
            }
        }
    }
}
