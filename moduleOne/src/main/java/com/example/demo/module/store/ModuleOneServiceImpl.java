package com.example.demo.module.store;


import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.demo.lib.base.RouterMap;
import com.example.demo.lib.base.service.IModuleOneService;

@Route(path = RouterMap.Login_MODULE_SERVICE)
public class ModuleOneServiceImpl implements IModuleOneService {

    private static final String FILE = "account";
    private static final String IS_LOGIN = "is_login";


    private Context mContext;

    @Override
    public void init(Context context) {
        mContext = context;
    }

    @Override
    public boolean isLogin() {
        return mContext.getSharedPreferences(FILE, Context.MODE_PRIVATE).getBoolean(IS_LOGIN, false);
    }

    @Override
    public void setLogin(boolean login) {
        mContext.getSharedPreferences(FILE, Context.MODE_PRIVATE).edit().putBoolean(IS_LOGIN, login).apply();
    }
}
