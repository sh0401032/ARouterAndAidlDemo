package com.example.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.demo.aidl.IModuleOneAidlService;

/**
 * Created by huanshao on 2018/9/3.
 */

public class ModuleOneService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IModuleOneAidlService.Stub iBinder = new IModuleOneAidlService.Stub() {
        @Override
        public String getModuleOneData() throws RemoteException {
            return "ModuleOne Data!";
        }

        @Override
        public void getModuleMethod() throws RemoteException {

        }
    };
}
