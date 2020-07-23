package com.xihan.luckyhooker.hooks;

import android.content.Context;
import android.os.Build;

import com.xihan.luckyhooker.Hook;

import java.util.List;
import java.util.Properties;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class IqiyiHook implements Hook {

    public static final String hookPackageName = "com.qiyi.video";

    @Override
    public void startHook(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("start Hook 爱奇艺:" + lpparam.packageName);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            XposedHelpers.findAndHookMethod("android.support.multidex.MultiDexApplication", lpparam.classLoader, "attachBaseContext", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    doHook(lpparam);
                }
            });
        } else
            doHook(lpparam);
    }

    private void doHook(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedHelpers.findAndHookMethod("com.iqiyi.video.qyplayersdk.player.state.StateManager", lpparam.classLoader, "updateVideoType",int.class, XC_MethodReplacement.returnConstant(null));
        XposedHelpers.findAndHookMethod(Properties.class,"getProperty",String.class ,lpparam.classLoader,XC_MethodReplacement.returnConstant("59e36a5e70e4c4efc6fcbc4db7ea59c1"));
        XposedBridge.hookAllMethods(Properties.class, "getProperty", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                if ("qiyi.export.key".equals(param.args[0])) {
                    param.setResult("59e36a5e70e4c4efc6fcbc4db7ea59c1");
                    //param.setResult("20485102b09bfb5842bf370463bed900");
                    //param.setResult("200852026c791ac910651df45b27da50");
                }}});
    }

    @Override
    public void startHook(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
    }

    @Override
    public boolean canHook(String packageName) {
        return hookPackageName.equals(packageName);
    }


}
