package com.example.demo.module.other;


import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.aidl.AidlModuleService;
import com.example.demo.aidl.IModuleHomeAidlService;
import com.example.demo.aidl.IModuleOneAidlService;
import com.example.demo.arouterdemo.two.R;
import com.example.demo.lib.base.RouterMap;

@Route(path = RouterMap.TWO_MAIN_ACTIVITY)
public class ModuleTwoMainActivity extends AppCompatActivity implements View.OnClickListener {

    private IModuleHomeAidlService mHomeService;
    private IModuleOneAidlService mOneService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_main);
        ((Button) findViewById(R.id.bt_aidl)).setOnClickListener(this);
        findViewById(R.id.bt_aidl_one).setOnClickListener(this);
        mHomeService = AidlModuleService.getInstance().getModuleHomeService();
        mOneService = AidlModuleService.getInstance().getModuleOneService();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_aidl) {
            if (mHomeService != null) {
                try {
                    String moduleOneData = mHomeService.getModuleHomeData();
                    Toast.makeText(this, "数据：" + moduleOneData, Toast.LENGTH_LONG).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } else if (v.getId() == R.id.bt_aidl_one) {
            if (mOneService != null) {
                try {
                    String moduleOneData = mOneService.getModuleOneData();
                    Toast.makeText(this, "数据：" + moduleOneData, Toast.LENGTH_LONG).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
