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

public class CoolHook implements Hook {
    public static final String hookPackageName = "com.coolapk.market";

    @Override
    public void startHook(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("start Hook 酷安:" + lpparam.packageName);
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
        XposedHelpers.findAndHookMethod("com.coolapk.market.view.splash.SplashActivity$Companion", lpparam.classLoader, "shouldShowAd", Context.class,XC_MethodReplacement.returnConstant(false));
    }

    @Override
    public void startHook(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
    }

    @Override
    public boolean canHook(String packageName) {
        return hookPackageName.equals(packageName);
    }

}
