package com.xihan.luckyhooker.hooks;

import com.xihan.luckyhooker.Hook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XcHook implements Hook {

    public static final String hookPackageName = "com.memoria.photos.gallery";

    @Override
    public void startHook(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("start Hook 相册:" + lpparam.packageName);

        XposedHelpers.findAndHookMethod(
                "com.memoria.photos.gallery.helpers.b"
                , lpparam.classLoader,
                "Lb"

                , new XC_MethodHook()//回调
                {@Override
                protected void afterHookedMethod(MethodHookParam 参数) throws Throwable {
                    super.afterHookedMethod(参数);
                    参数.setResult(true);
                }});
        XposedHelpers.findAndHookMethod(
                "com.memoria.photos.gallery.helpers.b"
                , lpparam.classLoader,
                "Ob"//

                , new XC_MethodHook()
                {@Override
                protected void afterHookedMethod(MethodHookParam 参数) throws Throwable {
                    super.afterHookedMethod(参数);
                    参数.setResult(true);
                }});

        XposedHelpers.findAndHookMethod(
                "com.memoria.photos.gallery.helpers.b"
                , lpparam.classLoader,
                "Pb"//

                , new XC_MethodHook()
                {@Override
                protected void afterHookedMethod(MethodHookParam 参数) throws Throwable {
                    super.afterHookedMethod(参数);
                    参数.setResult(true);
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