package com.moduleone.demo.module.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;


import com.example.aidl.AidlModuleService;
import com.example.demo.aidl.IModuleOneAidlService;
import com.example.demo.aidl.IModuleTwoAidlService;
import com.example.demo.arouterdemo.home.R;
import com.example.demo.lib.base.ConstantMap;
import com.example.demo.lib.base.RouterMap;
import com.example.demo.lib.base.bean.SerialBean;
import com.example.demo.lib.base.service.IModuleOneService;
import com.example.demo.lib.base.service.ModuleRouterService;
import com.example.demo.lib.base.util.Utils;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

@Route(path = RouterMap.HOME_FRAGMENT)
public class HomeFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_home, container, false);
        view.findViewById(R.id.bt_no_result).setOnClickListener(mClickListener);
        view.findViewById(R.id.bt_result_client).setOnClickListener(mClickListener);
        view.findViewById(R.id.bt_event_bus).setOnClickListener(mClickListener);
        view.findViewById(R.id.bt_intercept).setOnClickListener(mClickListener);
        view.findViewById(R.id.bt_inject).setOnClickListener(mClickListener);
        view.findViewById(R.id.bt_aidl).setOnClickListener(mClickListener);
        view.findViewById(R.id.bt_aidl_two).setOnClickListener(mClickListener);
        view.findViewById(R.id.bt_data).setOnClickListener(mClickListener);
        view.findViewById(R.id.bt_scheme_two).setOnClickListener(mClickListener);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private HomeClickListener mClickListener = new HomeClickListener();

    private class HomeClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.bt_no_result) {
                ARouter.getInstance().build(RouterMap.TWO_MAIN_ACTIVITY).navigation();
            } else if (id == R.id.bt_result_client) {
                getActivity().startActivity(new Intent(getActivity(), ResultClientActivity.class));
            } else if (id == R.id.bt_event_bus) {
                ARouter.getInstance().build(RouterMap.EVENT_BUS_ACTIVITY)
                        .withInt(ConstantMap.EVENT_BUS_DATA, 1000)
                        .navigation();
            } else if (id == R.id.bt_intercept) {
                ARouter.getInstance().build(RouterMap.INTER_TARGET_ACTIVITY)
                        .withBoolean(ConstantMap.IS_LOGIN, ModuleRouterService.isLogin())
                        .navigation();
            } else if (id == R.id.bt_inject) {
                SerialBean bean = new SerialBean();
                bean.setName("SerialBean");
                bean.setAge(18);
                ARouter.getInstance().build(RouterMap.INJECT_ACTIVITY)
                        .withInt(ConstantMap.INJECT_AGE, 18)
                        .withObject(ConstantMap.INJECT_OBJECT, bean)
                        .navigation();
            } else if (id == R.id.bt_data) {
                IModuleOneService moduleOneService = (IModuleOneService) ARouter.getInstance().build(RouterMap.Login_MODULE_SERVICE).navigation();
                if (moduleOneService != null) {
                    boolean login = moduleOneService.isLogin();
                    Toast.makeText(getActivity(), "是否登录：" + login, Toast.LENGTH_SHORT).show();
                }
            } else if (id == R.id.bt_aidl) {
                IModuleOneAidlService moduleOneService = AidlModuleService.getInstance().getModuleOneService();
                if (moduleOneService != null) {
                    try {
                        String moduleOneData = moduleOneService.getModuleOneData();
                        Toast.makeText(getActivity(), "数据：" + moduleOneData, Toast.LENGTH_LONG).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            } else if (id == R.id.bt_aidl_two) {
                IModuleTwoAidlService moduleTwoService = AidlModuleService.getInstance().getModuleTwoService();
                /*if (moduleTwoService != null) {
                    try {
                        moduleTwoService.startActivity("main");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }*/
            } else if (id == R.id.bt_scheme_two) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("router://two:8888/main"));
                getActivity().startActivity(intent);
            }
        }
    }

    @Subscriber(tag = ConstantMap.EVENT_BUS_KEY)
    public void onEvent(String s) {
        Utils.toast(getContext(), s);
    }
}
