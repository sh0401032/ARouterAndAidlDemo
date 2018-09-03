package com.example.demo.module.other;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.demo.lib.base.ConstantMap;
import com.example.demo.lib.base.RouterMap;
import com.example.demo.lib.base.bean.SerialBean;
import com.example.demo.lib.base.util.Utils;

@Route(path = RouterMap.INJECT_ACTIVITY)
public class InjectActivity extends AppCompatActivity {

    @Autowired(name = ConstantMap.INJECT_AGE)
    int age;

    @Autowired(name = ConstantMap.INJECT_OBJECT)
    SerialBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        Utils.toast(this, "age=" + age + ",bean.age=" + bean.getAge() + ",bean.name=" + bean.getName());
    }
}
