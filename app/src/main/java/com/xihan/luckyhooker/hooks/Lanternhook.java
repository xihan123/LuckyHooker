package com.xihan.luckyhooker.hooks;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.xihan.luckyhooker.Hook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


public class Lanternhook implements Hook {

        public static final String hookPackageName = "org.getlantern.lantern";

    @Override
    public void startHook(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("start Hook lantern:" + lpparam.packageName);
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
        XposedHelpers.findAndHookMethod("org.lantern.app.model.ProUser", lpparam.classLoader, "isProUser", XC_MethodReplacement.returnConstant(true));

    }


        @Override
        public void startHook(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
        }

        @Override
        public boolean canHook(String packageName) {
            return hookPackageName.equals(packageName);
        }
    }

