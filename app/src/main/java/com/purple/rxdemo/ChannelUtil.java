package com.purple.rxdemo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ChannelUtil {

    private static final String TAG = "ChannelUtil";

    private static final String CHANNEL_KEY = "cxp_channel_";
    private static final String CHANNEL_PATH = "META-INF/";

    public static String getChannel(Context context) {
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        String channel = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String entryName = entry.getName();
                Log.e("channel遍历文件", "文件名：" + entryName);
                if (entryName.startsWith(CHANNEL_PATH + CHANNEL_KEY)) {
                    channel = entryName;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "异常了");
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if ("".equals(channel)) {
            Log.d(TAG, "没有渠道信息");
        } else {
            channel = channel.substring(channel.indexOf(CHANNEL_KEY) + CHANNEL_KEY.length());
            Log.d(TAG, "渠道信息：" + channel);
        }
        return channel;
    }

}
