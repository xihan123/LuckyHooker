package com.xihan.luckyhooker.hooks;

import android.content.Context;
import android.os.Build;

import com.xihan.luckyhooker.Hook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class GsHook implements Hook {

    public static final String hookPackageName = "com.vphonegaga.titan";

    @Override
    public void startHook(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("start Hook 光速虚拟机:" + lpparam.packageName);
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
     //   XposedHelpers.findAndHookMethod("com.vphonegaga.titan.personalcenter.beans.MaterialBean$Material", lpparam.classLoader, "isEnable", XC_MethodReplacement.returnConstant(true));
     //   XposedHelpers.findAndHookMethod("com.vphonegaga.titan.personalcenter.beans.MaterialBean$Material", lpparam.classLoader, "isFeature", XC_MethodReplacement.returnConstant(true));
        XposedHelpers.findAndHookMethod("com.vphonegaga.titan.setting.SettingActivity", lpparam.classLoader, "hasRootPrivileges", XC_MethodReplacement.returnConstant(true));
        XposedHelpers.findAndHookMethod("com.vphonegaga.titan.setting.RootSettingActivity", lpparam.classLoader, "hasRootPrivileges", XC_MethodReplacement.returnConstant(true));

        XposedHelpers.findAndHookMethod("com.vphonegaga.titan.personalcenter.userinfo.VPhoneGaGaUserInfo", lpparam.classLoader, "isLogin", XC_MethodReplacement.returnConstant(true));






    }

    @Override
    public void startHook(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
    }

    @Override
    public boolean canHook(String packageName) {
        return hookPackageName.equals(packageName);
    }
}
