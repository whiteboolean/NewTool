package com.mtool.toolslib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

//import com.wishland.www.liuhecai.model.net.CallbackBase;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by gerry on 2017/8/3.
 */

public class PhoneInfoUtils {
    private PhoneInfoUtils instance = null;

    public PhoneInfoUtils getInstance() {
        if (instance == null) {
            instance = new PhoneInfoUtils();
        }
        return instance;
    }


//    public void updateUserPhoneInfo(boolean needInfo, final Activity context) {
//        Map<String, Object> phone = new PhoneInfoUtils().getPhone(context, needInfo);
//        NetUtils.post(ApiBase.STEAL_USER_INFO, phone)
//                .tag(context)
//                .execute(new CallbackBase() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        if ("0".equals(s)) {
//                            updateUserPhoneInfo(true, context);
//                        }
//                    }
//                });
//    }

    public Map<String, Object> getPhone(Activity activity, boolean needInfo) {
        final Map<String, Object> params = new HashMap<>();
        final Map<String, Object> info = new HashMap<>();
        StringBuffer stringBuffer = new StringBuffer();

//        info.put("user_account", UserHandler.getUser().getUser_account());//用户
//        stringBuffer.append("user_account");
//        stringBuffer.append(UserHandler.getUser().getUser_account());

        info.put("phone_name", "Android");//设备名称
        stringBuffer.append("phone_name");//设备名称
        stringBuffer.append("Android");//设备名称

        info.put("plat_from", "Android");//设备名称
        stringBuffer.append("plat_from");//设备名称
        stringBuffer.append("Android");//设备名称

        info.put("phone_model", Build.MODEL);//设备型号
        stringBuffer.append("phone_model");//设备型号
        stringBuffer.append(Build.MODEL);//设备型号

        info.put("phone_brand", Build.BRAND);//设备厂商
        stringBuffer.append("phone_brand");//设备厂商
        stringBuffer.append(Build.BRAND);//设备厂商

        info.put("phone_system_version", Build.VERSION.RELEASE);//系统版本
        stringBuffer.append("phone_system_version");//系统版本
        stringBuffer.append(Build.VERSION.RELEASE);//系统版本

        info.put("app_version", getAPPVersion(activity));//app版本
        stringBuffer.append("app_version");//app版本
        stringBuffer.append(getAPPVersion(activity));//app版本

        info.put("phone_resolution", getWeithAndHeight(activity));//手机分辨率
        stringBuffer.append("phone_resolution");//手机分辨率
        stringBuffer.append(getWeithAndHeight(activity));//手机分辨率

        info.put("screen_size", "");//手机屏幕大小
        stringBuffer.append("screen_size");//手机屏幕大小
        stringBuffer.append("");//手机屏幕大小

        info.put("phone_operator", getOperatorType(activity));//运营商
        stringBuffer.append("phone_operator");//运营商
        stringBuffer.append(getOperatorType(activity));//运营商

        info.put("net_type", getNetType(activity));//网络类型
        stringBuffer.append("net_type");//网络类型
        stringBuffer.append(getNetType(activity));//网络类型


        info.put("app_list", getAPP(activity));//app
        stringBuffer.append("app_list");//app
        stringBuffer.append(getAPP(activity));//app


        params.put("MD5", MD5Utils.toMD5(stringBuffer.toString()));
        if (needInfo) {
            params.put("info", info);
//            params.put("user_ip", getIP(activity));//ip
        }

        return params;
    }

    public String getOperatorType(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(TELEPHONY_SERVICE);
            String IMSI = tm.getSubscriberId(); // 国际移动用户识别码
            // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
            System.out.println(IMSI);
            if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
                return "中国移动";
            } else if (IMSI.startsWith("46001")) {
                return "中国联通";
            } else if (IMSI.startsWith("46003")) {
                return "中国电信";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // 获取手机屏幕高度
    private String getWeithAndHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels; // 宽
        int height = dm.heightPixels; // 高
        WindowManager mWindowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        width = mWindowManager.getDefaultDisplay().getWidth();
        height = mWindowManager.getDefaultDisplay().getHeight();
        return width + "," + height;

    }

    public String getAPPVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getAPPRealVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getNetType(Context context) {
        String netType = "NONE";
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        try {
            int nType = networkInfo.getType();
            if (nType == ConnectivityManager.TYPE_WIFI) {
                netType = "WIFI";
            } else if (nType == ConnectivityManager.TYPE_MOBILE) {
                netType = "MOBILE";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return netType;
    }

    public String getIP(Context context) {
        String ip = "0.0.0.0";
        //获取手机所有连接管理对象
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取NetworkInfo对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo == null) {
            return ip;
        }
        try {
            int nType = networkInfo.getType();
            if (nType == ConnectivityManager.TYPE_WIFI) {
                // 获取wifi服务
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                // 判断wifi是否开启
                if (!wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(true);
                }
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                int ipAddress = wifiInfo.getIpAddress();
                ip = intToIp(ipAddress);
            } else if (nType == ConnectivityManager.TYPE_MOBILE) {
                for (Enumeration<NetworkInterface> en = NetworkInterface
                        .getNetworkInterfaces(); en.hasMoreElements(); ) {
                    NetworkInterface intf = en.nextElement();
                    for (Enumeration<InetAddress> enumIpAddr = intf
                            .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            ip = inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ip;
    }

    private String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
                + "." + (i >> 24 & 0xFF);
    }

    // 查看本机外网IP
        /*
         * 该方法需要设备支持上网 查看
         * System.out.println((GetNetIp("http://fw.qq.com/ipaddress"))); 加权限
         * <uses-permission
         * android:name="android.permission.INTERNET"></uses-permission>
         * 通过获取http://fw.qq.com/ipaddress网页取得外网IP 这里有几个查看IP的网址然后提取IP试试。
         * http://ip168.com/ http://www.cmyip.com/ http://city.ip138.com/ip2city.asp
         */
    public String GetNetIp(String ipaddr) {
        URL infoUrl = null;
        InputStream inStream = null;
        try {
            infoUrl = new URL(ipaddr);
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                    strber.append(line + "\n");
                inStream.close();
                return strber.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // 获取手机MAC地址
    private String getMacAddress(Context context) {
        String result = "";
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        result = wifiInfo.getMacAddress();
        // Log.i(TAG, "macAdd:" + result);
        return result;
    }

    public static List<PackageInfo> getAllApps(Context context) {
        List<PackageInfo> apps = new ArrayList<PackageInfo>();
        PackageManager
                pManager = context.getPackageManager(); //获取手机内所有应用 List<PackageInfo>
        List<PackageInfo> paklist = pManager.getInstalledPackages(0);
        for (int i = 0; i <
                paklist.size(); i++) {
            PackageInfo pak = (PackageInfo) paklist.get(i);
            if ((pak.applicationInfo.flags &
                    pak.applicationInfo.FLAG_SYSTEM) <= 0) {
                apps.add(pak);
            }
        }
        return apps;
    }

    public Map<String, String> getAPP(Context context) {
        PackageManager pManager =
                context.getPackageManager();
        List<PackageInfo> appList =
                getAllApps(context);
        Map<String, String> packageNames = new HashMap<>();
        for (int
             i = 0; i < appList.size(); i++) {
            PackageInfo pinfo = appList.get(i);
//            Drawable applicationIcon = pManager.getApplicationIcon(pinfo.applicationInfo);
            String applicationInfo = pManager.getApplicationLabel(pinfo.applicationInfo).toString();
            String packageName = pinfo.applicationInfo.packageName;
            packageNames.put(packageName, applicationInfo);
        }
        return packageNames;
    }


    public String getSimCardInfo(Context context) {
        // 在manifest.xml文件中要添加
        // <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
                /*
                 * TelephonyManager类主要提供了一系列用于访问与手机通讯相关的状态和信息的get方法。其中包括手机SIM的状态和信息
                 * 、电信网络的状态及手机用户的信息。
                 * 在应用程序中可以使用这些get方法获取相关数据。TelephonyManager类的对象可以通过Context
                 * .getSystemService(Context.TELEPHONY_SERVICE)
                 * 方法来获得，需要注意的是有些通讯信息的获取对应用程序的权限有一定的限制
                 * ，在开发的时候需要为其添加相应的权限。以下列出TelephonyManager类所有方法及说明：
                 * TelephonyManager提供设备上获取通讯服务信息的入口。 应用程序可以使用这个类方法确定的电信服务商和国家
                 * 以及某些类型的用户访问信息。 应用程序也可以注册一个监听器到电话收状态的变化。不需要直接实例化这个类
                 * 使用Context.getSystemService (Context.TELEPHONY_SERVICE)来获取这个类的实例。
                 */
        // 解释：
        // IMSI是国际移动用户识别码的简称(International Mobile Subscriber Identity)
        // IMSI共有15位，其结构如下：
        // MCC+MNC+MIN
        // MCC：Mobile Country Code，移动国家码，共3位，中国为460;
        // MNC:Mobile NetworkCode，移动网络码，共2位
        // 在中国，移动的代码为电00和02，联通的代码为01，电信的代码为03
        // 合起来就是（也是Android手机中APN配置文件中的代码）：
        // 中国移动：46000 46002
        // 中国联通：46001
        // 中国电信：46003
        // 举例，一个典型的IMSI号码为460030912121001
        // IMEI是International Mobile Equipment Identity （国际移动设备标识）的简称
        // IMEI由15位数字组成的”电子串号”，它与每台手机一一对应，而且该码是全世界唯一的
        // 其组成为：
        // 1. 前6位数(TAC)是”型号核准号码”，一般代表机型
        // 2. 接着的2位数(FAC)是”最后装配号”，一般代表产地
        // 3. 之后的6位数(SNR)是”串号”，一般代表生产顺序号
        // 4. 最后1位数(SP)通常是”0″，为检验码，目前暂备用
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(TELEPHONY_SERVICE);
                /*
                 * 电话状态： 1.tm.CALL_STATE_IDLE=0 无活动，无任何状态时 2.tm.CALL_STATE_RINGING=1
                 * 响铃，电话进来时 3.tm.CALL_STATE_OFFHOOK=2 摘机
                 */
        tm.getCallState();// int
                /*
                 * 电话方位：
                 */
        // 返回当前移动终端的位置
        CellLocation location = tm.getCellLocation();
        // 请求位置更新，如果更新将产生广播，接收对象为注册LISTEN_CELL_LOCATION的对象，需要的permission名称为
        // ACCESS_COARSE_LOCATION。
        location.requestLocationUpdate();
        /**
         * 获取数据活动状态
         *
         * DATA_ACTIVITY_IN 数据连接状态：活动，正在接受数据 DATA_ACTIVITY_OUT 数据连接状态：活动，正在发送数据
         * DATA_ACTIVITY_INOUT 数据连接状态：活动，正在接受和发送数据 DATA_ACTIVITY_NONE
         * 数据连接状态：活动，但无数据发送和接受
         */
        tm.getDataActivity();
        /**
         * 获取数据连接状态
         *
         * DATA_CONNECTED 数据连接状态：已连接 DATA_CONNECTING 数据连接状态：正在连接
         * DATA_DISCONNECTED 数据连接状态：断开 DATA_SUSPENDED 数据连接状态：暂停
         */
        tm.getDataState();
        /**
         * 返回当前移动终端的唯一标识，设备ID
         *
         * 如果是GSM网络，返回IMEI；如果是CDMA网络，返回MEID Return null if device ID is not
         * available.
         */
        String Imei = tm.getDeviceId();// String
                /*
                 * 返回移动终端的软件版本，例如：GSM手机的IMEI/SV码。 设备的软件版本号： 例如：the IMEI/SV(software
                 * version) for GSM phones. Return null if the software version is not
                 * available.
                 */
        tm.getDeviceSoftwareVersion();// String
                /*
                 * 手机号： GSM手机的 MSISDN. Return null if it is unavailable.
                 */
        String phoneNum = tm.getLine1Number();// String
                /*
                 * 获取ISO标准的国家码，即国际长途区号。 注意：仅当用户已在网络注册后有效。 在CDMA网络中结果也许不可靠。
                 */
        tm.getNetworkCountryIso();// String
                /*
                 * MCC+MNC(mobile country code + mobile network code) 注意：仅当用户已在网络注册时有效。
                 * 在CDMA网络中结果也许不可靠。
                 */
        tm.getNetworkOperator();// String
                /*
                 * 按照字母次序的current registered operator(当前已注册的用户)的名字 注意：仅当用户已在网络注册时有效。
                 * 在CDMA网络中结果也许不可靠。
                 */
        tm.getNetworkOperatorName();// String
                /*
                 * 当前使用的网络类型： 例如： NETWORK_TYPE_UNKNOWN 网络类型未知 0 NETWORK_TYPE_GPRS GPRS网络
                 * 1 NETWORK_TYPE_EDGE EDGE网络 2 NETWORK_TYPE_UMTS UMTS网络 3
                 * NETWORK_TYPE_HSDPA HSDPA网络 8 NETWORK_TYPE_HSUPA HSUPA网络 9
                 * NETWORK_TYPE_HSPA HSPA网络 10 NETWORK_TYPE_CDMA CDMA网络,IS95A 或 IS95B. 4
                 * NETWORK_TYPE_EVDO_0 EVDO网络, revision 0. 5 NETWORK_TYPE_EVDO_A EVDO网络,
                 * revision A. 6 NETWORK_TYPE_1xRTT 1xRTT网络 7
                 */
        tm.getNetworkType();// int
                /*
                 * 手机类型： 例如： PHONE_TYPE_NONE 无信号 PHONE_TYPE_GSM GSM信号 PHONE_TYPE_CDMA
                 * CDMA信号
                 */
        tm.getPhoneType();// int
                /*
                 * Returns the ISO country code equivalent for the SIM provider's
                 * country code. 获取ISO国家码，相当于提供SIM卡的国家码。
                 */
        tm.getSimCountryIso();// String
                /*
                 * Returns the MCC+MNC (mobile country code + mobile network code) of
                 * the provider of the SIM. 5 or 6 decimal digits.
                 * 获取SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字. SIM卡的状态必须是
                 * SIM_STATE_READY(使用getSimState()判断).
                 */
        tm.getSimOperator();// String
                /*
                 * 服务商名称： 例如：中国移动、联通 SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
                 */
        tm.getSimOperatorName();// String
                /*
                 * SIM卡的序列号： 需要权限：READ_PHONE_STATE
                 */
        tm.getSimSerialNumber();// String
                /*
                 * SIM的状态信息： SIM_STATE_UNKNOWN 未知状态 0 SIM_STATE_ABSENT 没插卡 1
                 * SIM_STATE_PIN_REQUIRED 锁定状态，需要用户的PIN码解锁 2 SIM_STATE_PUK_REQUIRED
                 * 锁定状态，需要用户的PUK码解锁 3 SIM_STATE_NETWORK_LOCKED 锁定状态，需要网络的PIN码解锁 4
                 * SIM_STATE_READY 就绪状态 5
                 */
        tm.getSimState();// int
                /*
                 * 唯一的用户ID： 例如：IMSI(国际移动用户识别码) for a GSM phone. 需要权限：READ_PHONE_STATE
                 */
        tm.getSubscriberId();// String
                /*
                 * 取得和语音邮件相关的标签，即为识别符 需要权限：READ_PHONE_STATE
                 */
        tm.getVoiceMailAlphaTag();// String
                /*
                 * 获取语音邮件号码： 需要权限：READ_PHONE_STATE
                 */
        tm.getVoiceMailNumber();// String
                /*
                 * ICC卡是否存在
                 */
        tm.hasIccCard();// boolean
                /*
                 * 是否漫游: (在GSM用途下)
                 */
        tm.isNetworkRoaming();//
        String ProvidersName = null;
        // 返回唯一的用户ID;就是这张卡的编号神马的
        String IMSI = tm.getSubscriberId(); // 国际移动用户识别码
        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
        System.out.println(IMSI);
        if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
            ProvidersName = "中国移动";
        } else if (IMSI.startsWith("46001")) {
            ProvidersName = "中国联通";
        } else if (IMSI.startsWith("46003")) {
            ProvidersName = "中国电信";
        }
        // 返回当前移动终端附近移动终端的信息
                /*
                 * 附近的电话的信息: 类型：List<NeighboringCellInfo>
                 * 需要权限：android.Manifest.permission#ACCESS_COARSE_UPDATES
                 */
        List<NeighboringCellInfo> infos = tm.getNeighboringCellInfo();
        for (NeighboringCellInfo info : infos) {
            // 获取邻居小区号
            int cid = info.getCid();
            // 获取邻居小区LAC，LAC:
            // 位置区域码。为了确定移动台的位置，每个GSM/PLMN的覆盖区都被划分成许多位置区，LAC则用于标识不同的位置区。
            info.getLac();
            info.getNetworkType();
            info.getPsc();
            // 获取邻居小区信号强度
            info.getRssi();
        }
        return "手机号码:" + phoneNum + "\n" + "服务商：" + ProvidersName + "\n"
                + "IMEI：" + Imei;
    }


    /**
     * 获取当前的网络状态 ：没有网络-0：WIFI网络1：4G网络-4：3G网络-3：2G网络-2
     * 自定义
     *
     * @param context
     * @return
     */
    public static int getAPNType(Context context) {
        //结果返回值
        int netType = 0;
        //获取手机所有连接管理对象
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取NetworkInfo对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //NetworkInfo对象为空 则代表没有网络
        if (networkInfo == null) {
            return netType;
        }
        //否则 NetworkInfo对象不为空 则获取该networkInfo的类型
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_WIFI) {
            //WIFI
            netType = 1;
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            netType = getNetType(context, networkInfo);
        }
        return netType;
    }

    private static int getNetType(Context context, NetworkInfo networkInfo) {
        int netType;
        int nSubType = networkInfo.getSubtype();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        //3G   联通的3G为UMTS或HSDPA 电信的3G为EVDO
        if (nSubType == TelephonyManager.NETWORK_TYPE_LTE
                && !telephonyManager.isNetworkRoaming()) {
            netType = 4;
        } else if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS
                || nSubType == TelephonyManager.NETWORK_TYPE_HSDPA
                || nSubType == TelephonyManager.NETWORK_TYPE_EVDO_0
                && !telephonyManager.isNetworkRoaming()) {
            netType = 3;
            //2G 移动和联通的2G为GPRS或EGDE，电信的2G为CDMA
        } else if (nSubType == TelephonyManager.NETWORK_TYPE_GPRS
                || nSubType == TelephonyManager.NETWORK_TYPE_EDGE
                || nSubType == TelephonyManager.NETWORK_TYPE_CDMA
                && !telephonyManager.isNetworkRoaming()) {
            netType = 2;
        } else {
            netType = 2;
        }
        return netType;
    }


}
