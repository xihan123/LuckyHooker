package com.xihan.luckyhooker;

import android.os.Build;

import com.xihan.luckyhooker.hooks.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 * Created by wuxianlin on 2016/1/2.
 */

public class HookMain implements IXposedHookZygoteInit, IXposedHookLoadPackage, IXposedHookInitPackageResources {

    private Set<Hook> hooks = new HashSet<Hook>();

    public static XSharedPreferences prefs;
    public static final String PACKAGE_NAME = HookMain.class.getPackage().getName();
    public static String MODULE_PATH = null;
    private static final File prefsFileProt = new File("/data/user_de/0/" + PACKAGE_NAME + "/shared_prefs/" + PACKAGE_NAME + "_preferences.xml");

    public HookMain(){
        hooks.add(new test());
        hooks.add(new Lanternhook());
        hooks.add(new DydHook());
        hooks.add(new JstzHook());
        hooks.add(new YzHook());


        hooks.add(new XcHook());
        hooks.add(new DidaHook());
        hooks.add(new MoJiCSHOOK());
        hooks.add(new XmindHook());
        // hooks.add(new SoulHook());
        hooks.add(new KuwoHook());
        hooks.add(new YouKuHook());
        // hooks.add(new QqdHook());
        hooks.add(new PicsArtHook());
        //     hooks.add(new TxspHook());
        // hooks.add(new InshotHook());
        hooks.add(new PhotoHook());
        hooks.add(new QddsHook());
        hooks.add(new TlpzbHook());
        hooks.add(new SqnbHook());
        hooks.add(new NowHook());
        //  hooks.add(new IqiyiHook());
        hooks.add(new EsHook());
        hooks.add(new CoolHook()) ;
        //     hooks.add(new GsHook());
        hooks.add(new TxspHook());

        //     hooks.add(new UpdateHook());
    }

    @Override
    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            prefs = new XSharedPreferences(prefsFileProt);
        } else {
            prefs = new XSharedPreferences(PACKAGE_NAME);
        }
    }

    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
        prefs.reload();
        for (Hook hook : hooks) {
            try {
                String packageName = resparam.packageName;
                if (hook.canHook(packageName) &&
                        prefs.getBoolean(packageName, true))
                    hook.startHook(resparam);
            } catch (Throwable t) {
                XposedBridge.log(t.toString());
            }
        }
    }

    @Override
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        prefs.reload();
        for (Hook hook : hooks) {
            try {
                String packageName = lpparam.packageName;
                if (hook.canHook(packageName) &&
                        prefs.getBoolean(packageName, true))
                    hook.startHook(lpparam);
            } catch (Throwable t) {
                XposedBridge.log(t.toString());
            }
        }
    }
}

