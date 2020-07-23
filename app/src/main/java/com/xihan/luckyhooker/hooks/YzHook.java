package com.xihan.luckyhooker.hooks;

import com.xihan.luckyhooker.Hook;


import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class YzHook implements Hook {

    public static final String hookPackageName = "com.xihan.odexpatcher";

    @Override
    public void startHook(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("start Hook 验证模块是否激活:" + lpparam.packageName);
        //查找和hook方法，演示hook

        XposedHelpers.findAndHookMethod("com.xihan.odexpatcher.activitys.MainActivity", lpparam.classLoader, "isModuleActive",XC_MethodReplacement.returnConstant(true));
        }

    @Override
    public void startHook(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
    }

    @Override
    public boolean canHook(String packageName) {
        return hookPackageName.equals(packageName);
    }
}

