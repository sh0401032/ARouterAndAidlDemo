package com.moduleone.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.demo.aidl.IModuleHomeAidlService;

/**
 * Created by huanshao on 2018/9/3.
 */

public class ModuleHomeService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IModuleHomeAidlService.Stub iBinder = new IModuleHomeAidlService.Stub() {

        @Override
        public String getModuleHomeData() throws RemoteException {
            return "ModuleHome Data!";
        }

        @Override
        public void getModuleHomeMethod() throws RemoteException {

        }
    };

}
