package com.xihan.luckyhooker.hooks;

import android.app.AndroidAppHelper;
import android.content.Context;
import android.os.Build;

import com.xihan.luckyhooker.Hook;

import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

public class QngjxHook implements Hook {
    public static final String hookPackageName = "imoblife.toolbox.full";

    @Override
    public void startHook(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("start Hook 全能工具箱:" + lpparam.packageName);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            findAndHookMethod("android.support.multidex.MultiDexApplication", lpparam.classLoader, "attachBaseContext", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    doHook(lpparam);
                }
            });
        } else
            doHook(lpparam);
    }

    private void doHook(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        findAndHookMethod(
                "base.util.u"//要hook的应用类名
                , lpparam.classLoader,
                "c"//要hook的方法

                ,new XC_MethodHook()//回调
                {@Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable
                {//拦截之后要做什么
                    super.afterHookedMethod(param);
                    param.setResult(true);////强制返回boolean为true
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
