package com.example.aidl;

import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;


import com.example.demo.aidl.IModuleHomeAidlService;
import com.example.demo.aidl.IModuleOneAidlService;
import com.example.demo.aidl.IModuleTwoAidlService;


/**
 * Created by huanshao on 2018/9/3.
 */

public class AidlModuleService {
    public static AidlModuleService sInstance;
    private static String TAG = "AidlModuleService";
    public IModuleHomeAidlService mModuleHomeService;
    private ServiceConnection mModuleHomeServiceConnection;
    public IModuleTwoAidlService mModuleTwoService;
    private ServiceConnection mModuleTwoServiceConnection;
    public IModuleOneAidlService mModuleOneService;
    private ServiceConnection mModuleOneServiceConnection;

    public static AidlModuleService getInstance() {
        if (sInstance == null) {
            synchronized (AidlModuleService.class) {
                if (sInstance == null) {
                    sInstance = new AidlModuleService();
                }
            }
        }
        return sInstance;
    }

    public static void init(Application application) {
        getInstance().bindModuleHomeService(application)
                .bindModuleOneService(application)
                .bindModuleTwoService(application);
    }

    public IModuleHomeAidlService getModuleHomeService() {
        return mModuleHomeService;
    }

    public IModuleOneAidlService getModuleOneService() {
        return mModuleOneService;
    }

    public IModuleTwoAidlService getModuleTwoService() {
        return mModuleTwoService;
    }

    private AidlModuleService bindModuleHomeService(Context context) {
        if (mModuleHomeServiceConnection == null) {
            mModuleHomeServiceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.d(TAG, "home service connected");
                    mModuleHomeService = IModuleHomeAidlService.Stub.asInterface(service);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.d(TAG, "home service disconnected");
                    mModuleHomeService = null;
                }
            };
            Intent intent = new Intent();
            intent.setPackage("com.example.demo.arouterdemo");
            intent.setAction("com.example.demo.aidl.IModuleHomeAidlService");
            context.bindService(intent, mModuleHomeServiceConnection, Service.BIND_AUTO_CREATE);
        }
        return this;
    }

    private AidlModuleService bindModuleTwoService(Context context) {
        if (mModuleTwoServiceConnection == null) {
            mModuleTwoServiceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    mModuleTwoService = IModuleTwoAidlService.Stub.asInterface(service);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    mModuleTwoService = null;
                }
            };
            Intent intent = new Intent();
            intent.setPackage("com.example.demo.arouterdemo");
            intent.setAction("com.example.demo.aidl.IModuleTwoAidlService");
            context.bindService(intent, mModuleTwoServiceConnection, Service.BIND_AUTO_CREATE);
        }
        return this;
    }

    private AidlModuleService bindModuleOneService(Context context) {
        if (mModuleOneServiceConnection == null) {
            mModuleOneServiceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    mModuleOneService = IModuleOneAidlService.Stub.asInterface(service);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    mModuleOneService = null;
                }
            };
            Intent intent = new Intent();
            intent.setPackage("com.example.demo.arouterdemo");
            intent.setAction("com.example.demo.aidl.IModuleOneAidlService");
            context.bindService(intent, mModuleOneServiceConnection, Service.BIND_AUTO_CREATE);
        }
        return this;
    }


}
