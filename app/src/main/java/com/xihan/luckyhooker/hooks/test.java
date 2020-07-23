package com.xihan.luckyhooker.hooks;

import android.graphics.Color;
import android.widget.TextView;

import com.xihan.luckyhooker.Hook;

import java.io.File;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class test implements Hook {

    public static final String hookPackageName = "com.xihan.thishookdemo";

    @Override
    public void startHook(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("start Hook test:" + lpparam.packageName);
        //查找和hook方法，演示hook String方法
        XposedHelpers.findAndHookMethod(
                "com.xihan.thishookdemo.MainActivity"//要hook的应用类名
                , lpparam.classLoader,
                "onCreate"//要hook的方法
                //,String.class//函数名，可以注释掉,String就是String.class
                , new XC_MethodHook()//回调
                {@Override
                protected void afterHookedMethod(MethodHookParam pparam) throws Throwable
                {//拦截之后要做什么
                    TextView TV = (TextView) pparam.thisObject;

                    TV.setText(" :)");
                    TV.setTextColor(Color.RED);

                }});
    }
    @Override
    public void startHook(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
    }

    @Override
    public boolean canHook(String packageName) {
        return hookPackageName.equals(packageName);
    }
}

