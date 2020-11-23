package com.example.external.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class PhoneInfoUtil {

    public static final String getOsInfo() {
        return Build.VERSION.RELEASE;
    }


    //获得独一无二的Psuedo ID
    public static String getUniquePsuedoID() {
        String serial = null;

        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +

                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +

                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +

                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +

                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +

                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +

                Build.USER.length() % 10; //13 位

        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
            //API>=9 使用serial号
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = "serial"; // 随便一个初始化
        }
        //使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }


    public static String getVersionName(Context context)//获取版本号(内部识别号)
    {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }


    public static String size(long size) {
        if (size / (1024 * 1024) > 0) {
            float tmpSize = (float) (size) / (float) (1024 * 1024);
            DecimalFormat df = new DecimalFormat("#.##");
            return "" + df.format(tmpSize) + "MB";
        } else if (size / 1024 > 0) {
            return "" + (size / (1024)) + "KB";
        } else
            return "" + size + "B";
    }

    /*
     * 得到当前app系统版本号
     * */
    public static String getCurrentVersion(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo;
        String version = "";
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(),
                    0);
            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static int getCurrentVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int versioncode = packInfo.versionCode;
            return versioncode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取手机IMEI
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) {
                    continue;
                }
                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }
                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static void saveBitmap(Context context) throws IOException {
        // 创建目录
        //获取内部存储状态

       /* String state = Environment.getExternalStorageState();
        //如果状态不是mounted，无法读写
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        String sdCardDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File appDir = new File(sdCardDir, "CaChe");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "5i5ysole" + ".txt";
        File file = new File(appDir, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        //保存android唯一表示符
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(getUUID());
            fw.flush();
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public static String readKey(Context context) {

        // 创建目录
        //获取内部存储状态
        if (UserUtils.getInstance().getUuid(context) == null || UserUtils.getInstance().getUuid(context).equals("")) {
            UserUtils.getInstance().saveUuid(context, getUUID());
        }
        return UserUtils.getInstance().getUuid(context);
        /*String state = Environment.getExternalStorageState();
        //如果状态不是mounted，无法读写
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        String sdCardDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File appDir = new File(sdCardDir, "CaChe");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "sole" + ".txt";
        File file = new File(appDir, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = null;
        StringBuilder content = null;
        try {
            FileReader fr = new FileReader(file);
            content = new StringBuilder();
            reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return content != null ? content.toString() : null;*/
    }

    /**
     * 获取手机IMSI
     */
    public static String getIMSI(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imsi = "";
            //判断系统是否是在6.0以上
            if (Build.VERSION.SDK_INT >= 23) {
                //判断是否有读写权限，如果等于PERMISSION_GRANTED就代表有
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE}
                            , 11);
                } else {
                    //获取IMEI号
                    imsi = telephonyManager.getSubscriberId();
                    //在次做个验证，也不是什么时候都能获取到的啊
                    if (imsi == null) {
                        imsi = "";
                    }

                }
            } else {
                //获取IMEI号
                imsi = telephonyManager.getSubscriberId();
                //在次做个验证，也不是什么时候都能获取到的啊
                if (imsi == null) {
                    imsi = "";
                }
            }

            return imsi;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }


    /**
     * 获取当前手机系统语言。（待定）
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }


    /**
     * 判断手机是否root，不弹出root请求框<br/>
     */
    public static boolean isRoot() {
        String binPath = "/system/bin/su";
        String xBinPath = "/system/xbin/su";
        if (new File(binPath).exists() && isExecutable(binPath))
            return true;
        if (new File(xBinPath).exists() && isExecutable(xBinPath))
            return true;
        return false;
    }

    private static boolean isExecutable(String filePath) {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("ls -l " + filePath);
            // 获取返回内容
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String str = in.readLine();
            if (str != null && str.length() >= 4) {
                char flag = str.charAt(3);
                if (flag == 's' || flag == 'x')
                    return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (p != null) {
                p.destroy();
            }
        }
        return false;
    }


    /**
     * 根据IP地址获取MAC地址
     *
     * @return
     */
    public static String getLocalMacAddressFromIp() {
        String strMacAddr = null;
        try {
            //获得IpD地址
            InetAddress ip = getLocalInetAddress();
            byte[] b = NetworkInterface.getByInetAddress(ip).getHardwareAddress();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                if (i != 0) {
                    buffer.append(':');
                }
                String str = Integer.toHexString(b[i] & 0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
            }
            strMacAddr = buffer.toString().toUpperCase();
        } catch (Exception e) {
        }
        return strMacAddr;
    }

    /**
     * 获取移动设备本地IP
     *
     * @return
     */
    private static InetAddress getLocalInetAddress() {
        InetAddress ip = null;
        try {
            //列举
            Enumeration<NetworkInterface> en_netInterface = NetworkInterface.getNetworkInterfaces();
            while (en_netInterface.hasMoreElements()) {//是否还有元素
                NetworkInterface ni = (NetworkInterface) en_netInterface.nextElement();//得到下一个元素
                Enumeration<InetAddress> en_ip = ni.getInetAddresses();//得到一个ip地址的列举
                while (en_ip.hasMoreElements()) {
                    ip = en_ip.nextElement();
                    if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1)
                        break;
                    else
                        ip = null;
                }
                if (ip != null) {
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }


    /*
     * 获取bssid
     * */
    public static String getBssid(Context context) {
        String bssid = "";
        //判断系统是否是在6.0以上
        if (Build.VERSION.SDK_INT >= 23) {
            //判断是否有读写权限，如果等于PERMISSION_GRANTED就代表有
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}
                        , 10);
            } else {
                WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = wifi.getConnectionInfo();
                bssid = info.getBSSID();
            }
        } else {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            bssid = info.getBSSID();
        }
        return bssid;
    }

    /*
     * 获取ssid
     * */
    public static String getSsid(Context context) {
        String ssid = "";
        //判断系统是否是在6.0以上
        if (Build.VERSION.SDK_INT >= 23) {
            //判断是否有读写权限，如果等于PERMISSION_GRANTED就代表有
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}
                        , 10);
            } else {
                WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = wifi.getConnectionInfo();
                ssid = info.getSSID();
            }
        } else {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            ssid = info.getSSID();
        }
        return ssid;
    }

    /**
     * 获取手机CPU型号,智能识别部分机型
     *
     * @param currentActivity
     * @return
     */
    public static String GetCPU(Activity currentActivity) {
        String cpu = "";
        try {
            String str1 = "/proc/cpuinfo";
            String[] cpuInfo = {"", ""};
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            String line = null;
            while ((line = localBufferedReader.readLine()) != null) {
                if (line.toLowerCase().indexOf("hardware") != -1) {
                    cpuInfo[0] = line;
                    break;
                }
            }
            cpuInfo[1] = Build.HARDWARE;
            localBufferedReader.close();
            cpu = cpuInfo[0] + "&" + cpuInfo[1];
        } catch (Exception e) {
            Log.e("isGetCpuError", "GetCPU: " + e.getMessage());
        }
        return cpu;
    }

    public String getVersion(Context context) {
        String versionName;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            versionName = "1.0.0";
        }
        return versionName;
    }

}
