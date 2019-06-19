package com.example.admin.ztest;


import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;

import com.secneo.sdk.Helper;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;




public class MApplication extends Application {
    private FPVDemoApplication fpvDemoApplication;

    @Override
    protected void attachBaseContext(Context paramContext) {
        super.attachBaseContext(paramContext);
        Helper.install(MApplication.this);
        if (fpvDemoApplication == null) {
            fpvDemoApplication = new FPVDemoApplication();
            fpvDemoApplication.setContext(this);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fpvDemoApplication.onCreate();


        Context context = getApplicationContext();
        //获取当前包名
        String packageName = context.getPackageName();
        //获取当前进程名
        String processName = getProcessName(Process.myPid());
        //设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        //初始化Bugly
        //context ，APPID    千万注意第三个参数 当你用手机连接调试的时候 设置true 可以在logcat看到将要上传的信息
        //当你打包发布的时候 一定要修改为false 这样日志才可以上传服务器，在后台可以看到统计的信息
        CrashReport.initCrashReport(getApplicationContext(), "587edb0a5d", true);

    }

    /**
     * * 获取进程号对应的进程名
     * <p>
     * // @param pid 进程号
     *
     * @return 进程名
     */
    private String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
