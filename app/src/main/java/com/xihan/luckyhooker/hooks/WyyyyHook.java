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

public class WyyyyHook implements Hook {

    public static final String hookPackageName = "com.netease.cloudmusic";

    @Override
    public void startHook(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("start Hook 酷我音乐:" + lpparam.packageName);
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
        XposedHelpers.findAndHookMethod("com.netease.cloudmusic.meta.MusicInfo", lpparam.classLoader, "isAlbumFeeMusic", XC_MethodReplacement.returnConstant(1));
        XposedHelpers.findAndHookMethod("com.netease.cloudmusic.meta.MusicInfo", lpparam.classLoader, "isFeeSong", XC_MethodReplacement.returnConstant(1));
        XposedHelpers.findAndHookMethod("com.netease.cloudmusic.meta.MusicInfo", lpparam.classLoader, "isFreePlayMusic", XC_MethodReplacement.returnConstant(1));
        XposedHelpers.findAndHookMethod("com.netease.cloudmusic.meta.MusicInfo", lpparam.classLoader, "isMusicFee", XC_MethodReplacement.returnConstant(1));
        XposedHelpers.findAndHookMethod("com.netease.cloudmusic.meta.MusicInfo", lpparam.classLoader, "isPayedMusic", XC_MethodReplacement.returnConstant(1));
        XposedHelpers.findAndHookMethod("com.netease.cloudmusic.meta.MusicInfo", lpparam.classLoader, "isPermanentPayed", XC_MethodReplacement.returnConstant(1));
        XposedHelpers.findAndHookMethod("com.netease.cloudmusic.meta.MusicInfo", lpparam.classLoader, "isVipMusic", XC_MethodReplacement.returnConstant(1));
    }

    @Override
    public void startHook(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
    }

    @Override
    public boolean canHook(String packageName) {
        return hookPackageName.equals(packageName);
    }

}
