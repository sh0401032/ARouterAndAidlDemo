package com.example.aidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.demo.aidl.IModuleTwoAidlService;
import com.example.demo.module.other.ModuleTwoMainActivity;

/**
 * Created by huanshao on 2018/9/3.
 */

public class ModuleTwoService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IModuleTwoAidlService.Stub iBinder = new IModuleTwoAidlService.Stub() {
        @Override
        public String getModuleTwoData() throws RemoteException {
            return "ModuleTwo Data!";
        }

        @Override
        public void getModuleMethod() throws RemoteException {

        }

        @Override
        public void startActivity(String json) throws RemoteException {
            if ("main".equals(json)) {
//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(), ModuleTwoMainActivity.class);
//                //ModuleTwoService.this.startActivity(intent);
//                getApplicationContext().startActivity(intent);
            }
        }
    };

}
