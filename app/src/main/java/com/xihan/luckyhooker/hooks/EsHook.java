package com.xihan.luckyhooker.hooks;

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

public class EsHook implements Hook {

    public static final String hookPackageName = "com.estrongs.android.pop";

    @Override
    public void startHook(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("start Hook ES文件浏览器:" + lpparam.packageName);
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

        XposedHelpers.findAndHookMethod("es.yz", lpparam.classLoader, "f", XC_MethodReplacement.returnConstant(true));
        XposedHelpers.findAndHookMethod("es.yz", lpparam.classLoader, "h", XC_MethodReplacement.returnConstant(true));
        XposedHelpers.findAndHookMethod("es.yz", lpparam.classLoader, "i", XC_MethodReplacement.returnConstant(true));

        XposedHelpers.findAndHookMethod("com.estrongs.android.pop.n", lpparam.classLoader, "Z0", XC_MethodReplacement.returnConstant(true));
        XposedHelpers.findAndHookMethod("com.estrongs.android.pop.n", lpparam.classLoader, "X0", XC_MethodReplacement.returnConstant(true));




    }

    @Override
    public void startHook(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
    }

    @Override
    public boolean canHook(String packageName) {
        return hookPackageName.equals(packageName);
    }



}
