package com.example.demo.lib.base.service;


import com.alibaba.android.arouter.launcher.ARouter;
import com.example.demo.lib.base.RouterMap;

public class ModuleRouterService {

    public static boolean isLogin() {
        IModuleOneService chatModuleService = (IModuleOneService) ARouter.getInstance().build(RouterMap.Login_MODULE_SERVICE).navigation();
        return chatModuleService != null && chatModuleService.isLogin();
    }

    public static void setLogin(boolean login) {
        IModuleOneService chatModuleService = (IModuleOneService) ARouter.getInstance().build(RouterMap.Login_MODULE_SERVICE).navigation();
        if (chatModuleService != null) {
            chatModuleService.setLogin(login);
        }
    }
}
