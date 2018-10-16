package insung.moving.customerV2.util;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


public class Util {
    public static short htonl(short x) {
        return (short) ((x << 8) |
                ((x >> 8) & 0xff));
    }

    public static char htonl(char x) {
        return (char) ((x << 8) |
                ((x >> 8) & 0xff));
    }

    public static int htonl(int x) {
        return (int) ((htonl((short) x) << 16) |
                (htonl((short) (x >> 16)) & 0xffff));
    }

    public static long htonl(long x) {
        return (long) (((long) htonl((int) (x)) << 32) |
                ((long) htonl((int) (x >> 32)) & 0xffffffffL));
    }

    public static float htonl(float x) {
        return Float.intBitsToFloat(htonl(Float.floatToRawIntBits(x)));
    }

    public static double htonl(double x) {
        return Double.longBitsToDouble(htonl(Double.doubleToRawLongBits(x)));
    }

    public static void IntToByte(byte Data[], int nDataSize, int addData) {
        Data[nDataSize++] = (byte) (addData & 0xFF);
        Data[nDataSize++] = (byte) ((addData & 0xFF00) >> 8);
        Data[nDataSize++] = (byte) ((addData & 0xFF0000) >> 16);
        Data[nDataSize++] = (byte) ((addData & 0xFF000000) >> 24);
    }

    public static void StringToByte(byte Data[], int nDataSize, String addData) {
        try {
            byte Temp[] = addData.getBytes("ksc5601");

            for (int i = 0; i < Temp.length; i++) {
                Data[nDataSize++] = Temp[i];
            }
        } catch (Exception e) {
        }
    }

    public static void DisplayString(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    public static String StatusChange(String Status) {
        if (Status.compareTo("11") == 0) return "배차";
        else if (Status.compareTo("12") == 0) return "운행";
        else if (Status.compareTo("13") == 0) return "픽업";
        else if (Status.compareTo("30") == 0) return "완료";
        else if (Status.compareTo("배차") == 0) return "11";
        else if (Status.compareTo("운행") == 0) return "12";
        else if (Status.compareTo("픽업") == 0) return "13";
        else if (Status.compareTo("완료") == 0) return "30";

        return "";
    }

    public static int getFontSize(int nidx) {
        int dCmpFontSize = 22;

        switch (nidx) {
            case 0:
                dCmpFontSize = 17;
                break;

            case 1:
                dCmpFontSize = 18;
                break;

            case 2:
                dCmpFontSize = 19;
                break;

            case 3:
                dCmpFontSize = 20;
                break;

            case 4:
                dCmpFontSize = 21;
                break;

            case 5:
                dCmpFontSize = 22;
                break;

            case 6:
                dCmpFontSize = 23;
                break;

            case 7:
                dCmpFontSize = 24;
                break;

            case 8:
                dCmpFontSize = 25;
                break;

            case 9:
                dCmpFontSize = 26;
                break;

            case 10:
                dCmpFontSize = 27;
                break;

            default:
                dCmpFontSize = 24;
                break;
        }

        return dCmpFontSize;
    }

    public static byte[] compress(byte[] src, int nSize) throws IOException {
        Deflater deflater = new Deflater();

        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(src, 0, nSize);
        deflater.finish();

        ByteArrayOutputStream bao = new ByteArrayOutputStream(nSize * 2);
        byte[] buf = new byte[1024];
        while (!deflater.finished()) {
            int compByte = deflater.deflate(buf);
            bao.write(buf, 0, compByte);
        }

        bao.close();
        deflater.end();

        return bao.toByteArray();
    }

    public static byte[] decompress(byte[] data, int nSize) throws IOException, DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        while (!inflater.finished()) {
            int compByte = inflater.inflate(buf);
            bao.write(buf, 0, compByte);
        }

        bao.close();
        inflater.end();

        return bao.toByteArray();
    }

    //오더에 거리를 m로 환산
    public static int GetEnableDistance(String sDis) {
        int distance = 999999;

        if (sDis.compareTo("--") == 0)
            distance = 0;
        else
            distance = (int) (Float.parseFloat(sDis) * 1000);

        return distance;
    }

    //소켓 처리 단위 100m단위
    public static String getDistance(int nidx) {
        String dCmpDis = "999999";

        switch (nidx) {
            case 0:
                dCmpDis = "999999";
                break;

            case 1:
                dCmpDis = "1";
                break;

            case 2:
                dCmpDis = "2";
                break;

            case 3:
                dCmpDis = "3";
                break;

            case 4:
                dCmpDis = "4";
                break;

            case 5:
                dCmpDis = "5";
                break;

            case 6:
                dCmpDis = "7";
                break;

            case 7:
                dCmpDis = "10";
                break;

            case 8:
                dCmpDis = "15";
                break;

            case 9:
                dCmpDis = "20";
                break;

            case 10:
                dCmpDis = "25";
                break;

            case 11:
                dCmpDis = "30";
                break;

            case 12:
                dCmpDis = "35";
                break;

            case 13:
                dCmpDis = "40";
                break;

            case 14:
                dCmpDis = "45";
                break;

            case 15:
                dCmpDis = "50";
                break;

            case 16:
                dCmpDis = "60";
                break;

            case 17:
                dCmpDis = "70";
                break;

            case 18:
                dCmpDis = "80";
                break;

            case 19:
                dCmpDis = "90";
                break;

            case 20:
                dCmpDis = "100";
                break;

            case 21:
                dCmpDis = "150";
                break;

            case 22:
                dCmpDis = "200";
                break;

            default:
                dCmpDis = "999999";
                break;
        }

        return dCmpDis;
    }

    public static int GetSidoCode(int nidx) {
        int nCode = 0;

        switch (nidx) {
            case 0:
                nCode = 11;
                break;

            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                nCode = nidx + 25;
                break;

            default:
                nCode = nidx + 35;
                break;
        }

        return nCode;
    }

    public static int GetDistance(int nIndex) {
        int value = 0;

        switch (nIndex) {
            case 0:
                value = 0;
                break;

            case 1:
                value = 1;
                break;

            case 2:
                value = 3;
                break;

            case 3:
                value = 5;
                break;

            case 4:
                value = 7;
                break;

            case 5:
                value = 10;
                break;

            case 6:
                value = 15;
                break;

            case 7:
                value = 20;
                break;

            case 8:
                value = 25;
                break;

            case 9:
                value = 30;
                break;

            default:
                value = 0;
                break;
        }

        return (value * 1000);
    }

    public static int GetOrderCount(int nIndex) {
        int value = 0;

        switch (nIndex) {
            case 0:
                value = 0;
                break;

            case 1:
                value = 1;
                break;

            case 2:
                value = 3;
                break;

            case 3:
                value = 5;
                break;

            case 4:
                value = 10;
                break;

            case 5:
                value = 15;
                break;

            case 6:
                value = 20;
                break;

            case 7:
                value = 25;
                break;

            case 8:
                value = 30;
                break;

            case 9:
                value = 50;
                break;

            default:
                value = 0;
                break;
        }

        return value;
    }

    public static String getYear(int Year) {
        Calendar calendar = Calendar.getInstance();

        String sYear = "";

        if (Year >= 0) {
            sYear = Year + "";
        } else {
            sYear = calendar.get(Calendar.YEAR) + "";
        }

        return sYear;
    }

    public static String getMonth(int Month) {
        Calendar calendar = Calendar.getInstance();

        String sMonth = "";

        if (Month >= 0) {
            sMonth = (Month + 1) + "";
        } else {
            sMonth = (calendar.get(Calendar.MONTH) + 1) + "";
        }

        if (sMonth.length() == 1) sMonth = "0" + sMonth;

        return sMonth;
    }

    public static String getDay(int Day) {
        Calendar calendar = Calendar.getInstance();

        String sDay = "";

        if (Day >= 0) {
            sDay = Day + "";
        } else {
            sDay = calendar.get(Calendar.DAY_OF_MONTH) + "";
        }

        if (sDay.length() == 1) sDay = "0" + sDay;

        return sDay;
    }

    public static String getHour(int Hour) {
        Calendar calendar = Calendar.getInstance();

        String sHour = "";

        if (Hour >= 0) {
            sHour = Hour + "";
        } else {
            sHour = calendar.get(Calendar.HOUR_OF_DAY) + "";
        }

        if (sHour.length() == 1) sHour = "0" + sHour;

        return sHour;
    }

    public static String getMinute(int Minute) {
        Calendar calendar = Calendar.getInstance();

        String sMinute = "";

        if (Minute >= 0) {
            sMinute = Minute + "";
        } else {
            sMinute = calendar.get(Calendar.MINUTE) + "";
        }

        if (sMinute.length() == 1) sMinute = "0" + sMinute;

        return sMinute;
    }

    public static String getSecond(int Second) {
        Calendar calendar = Calendar.getInstance();

        String sMinute = "";

        if (Second >= 0) {
            sMinute = Second + "";
        } else {
            sMinute = calendar.get(Calendar.SECOND) + "";
        }

        if (sMinute.length() == 1) sMinute = "0" + sMinute;

        return sMinute;
    }

    public static String MoneyFormat(String money) {
        try {
            DecimalFormat df = new DecimalFormat("#,###,###,###,###,###,###,###,##0");
            BigDecimal bd = new BigDecimal(money);
            return df.format(bd.doubleValue());
        } catch (Exception e) {
            return "0";
        }
    }

    public static String PhoneFormat(String phone) {
        if (phone.length() <= 4) return phone;

        switch (phone.length()) {
            case 10:
                return String.format("%s-%s-%s", phone.substring(0, 3), phone.substring(3, 6), phone.substring(6, 10)).toString();

            case 9:
                return String.format("%s-%s-%s", phone.substring(0, 2), phone.substring(2, 5), phone.substring(5, 9)).toString();

            case 8:
                return String.format("%s-%s", phone.substring(0, 4), phone.substring(4, 8)).toString();

            case 7:
                return String.format("%s-%s", phone.substring(0, 3), phone.substring(3, 7)).toString();

            case 6:
                return String.format("%s-%s", phone.substring(0, 2), phone.substring(2, 6)).toString();

            case 5:
                return String.format("%s-%s", phone.substring(0, 1), phone.substring(1, 5)).toString();

            default:
                return String.format("%s-%s-%s", phone.substring(0, phone.length() - 8),
                        phone.substring(phone.length() - 8, phone.length() - 4),
                        phone.substring(phone.length() - 4, phone.length())).toString();
        }
    }

    public static String DateFormat(String date) {
        if (date.length() < 8) {
            return date;
        }

        return String.format("%s-%s-%s", date.substring(0, 4), date.substring(4, 6), date.substring(6, 8)).toString();
    }

    public static void NotifyMessage(Context context, String sMsg) {
        new AlertDialog.Builder(context)
                .setMessage(sMsg)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create().show();
    }

    public static void NotifyMessage(Context context, String sTitle, String sMsg) {
        new AlertDialog.Builder(context)
                .setTitle(sTitle)
                .setMessage(sMsg)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create().show();
    }

    public static int ParseInt(String sNum, int DefaultNum) {
        int Number = 0;

        try {
            Number = Integer.parseInt(sNum);
        } catch (Exception e) {
            Number = DefaultNum;
        }

        return Number;
    }

    public static float ParseFloat(String sNum, float DefaultNum) {
        float Number = 0;
        try {
            Number = Float.parseFloat(sNum);
        } catch (Exception e) {
            Number = DefaultNum;
        }

        return Number;
    }

    public static double ParseDouble(String sNum, double DefaultNum) {
        double Number = 0;
        try {
            Number = Double.parseDouble(sNum);
        } catch (Exception e) {
            Number = DefaultNum;
        }

        return Number;
    }

    public static int toGoogleLocation(int data) {
        int a = (int) (data / 360000.0);
        double b = (((double) data / 360000.0) - (double) a) / 10.0 * 6;
        double c = (a + b) * 100.0;

        int aa = (int) (c / 100.0);
        double d = (c - (double) (aa * 100)) / 60.0;
        double bb = aa + d;

        return (int) (bb * 1E6);
    }

    public static Intent ShowRousen() {
        ComponentName compName = new ComponentName("com.rousen.app", "com.rousen.app.rousen");
        Intent i = new Intent(Intent.ACTION_MAIN);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        i.setComponent(compName);

        return i;
    }

    public static boolean isInstall(Context con, String PackageName) {
        List<PackageInfo> appinfo = con.getPackageManager().getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);

        for (int i = 0; i < appinfo.size(); i++) {
            PackageInfo pi = appinfo.get(i);
            String appname = pi.packageName;
            if (appname.compareTo(PackageName) == 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean createFolder(String sPath) {
        File file = new File(sPath);

        if (file.exists() == false)
            return file.mkdirs();

        return true;
    }

    public static boolean DeleteFile(String sPath) {
        File file = new File(sPath);

        if (file.exists() == true)
            return file.delete();

        return true;
    }

    public static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

//    public static void customToast(Context context, String msg) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View layout = null;
//        layout = inflater.inflate(R.layout.custom_toast, null);
//        TextView text = (TextView) layout.findViewById(R.id.message);
//        text.setText(msg);
//
//        Toast toast = new Toast(context);
//        toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 50);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setView(layout);
//        toast.show();
//    }

    public static void playMediaSound(Context context, int id) {
        try {
            MediaPlayer mp = MediaPlayer.create(context, id);
            mp.setVolume((float) 1f, (float) 1f);
            mp.start();
        } catch (Exception e) {
            Log.d("ERROR", "Play Sound Error");
        }
    }

    public static void playSoundPool(Context context, int id) {
        try {
            SoundPool sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            int index = sp.load(context, id, 1);
            sp.play(index, 1, 1, 0, 0, 1);
        } catch (Exception e) {
            Log.d("ERROR", "Play Sound Error");
        }
    }

    public static String SetMinDateTime(String str) {
        String temp = str;
        try {
            String year = temp.substring(0, 4);
            String month = temp.substring(4, 6);
            String day = temp.substring(6, 8);
            String hour = temp.substring(8, 10);
            String min = temp.substring(10, 12);
            return str = year + "-" + month + "-" + day + " " + hour + ":" + min;
        } catch (Exception e) {
            return str;
        }
    }

    public static boolean UrlCall(String url_addr) {
        boolean bResult = true;

        try {
            URL url = new URL(url_addr);
            URLConnection connection = url.openConnection();

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String buf = null;
            buf = br.readLine();
            if (buf == null || !buf.equals("1")) {
                bResult = false;
            }

            br.close();
            isr.close();
            is.close();
        } catch (MalformedURLException mue) {
            bResult = false;
        } catch (IOException ioe) {
            bResult = false;
        }

        return bResult;
    }

    // StatusBar Size 구하는 메서드 ( onCreate에서 실행 불가능 )
//    public static void getStatusBarSize(Activity at) { 
//        Rect rectgle= new Rect();
//        Window window= at.getWindow();
//        window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
//        int StatusBarHeight = rectgle.top;
//        int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
//        int TitleBarHeight = contentViewTop - StatusBarHeight;
//    }

    // onCreate()에서 StatusBar 구하는 메서드 ( Density 이용 )
    public static int getStatusBarSizeOnCreate(Context con) {
        final float DEFAULT_HDIP_DENSITY_SCALE = 1.5f;
        float scale = con.getResources().getDisplayMetrics().density;

        final int LOW_DPI_STATUS_BAR_HEIGHT = (int) (19 / scale * DEFAULT_HDIP_DENSITY_SCALE);
        final int MEDIUM_DPI_STATUS_BAR_HEIGHT = (int) (25 / scale * DEFAULT_HDIP_DENSITY_SCALE);
        final int HIGH_DPI_STATUS_BAR_HEIGHT = (int) (38 / scale * DEFAULT_HDIP_DENSITY_SCALE);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) con.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);

        switch (displayMetrics.densityDpi) {
            case DisplayMetrics.DENSITY_XHIGH:
                return HIGH_DPI_STATUS_BAR_HEIGHT;
            case DisplayMetrics.DENSITY_HIGH:
                return HIGH_DPI_STATUS_BAR_HEIGHT;
            case DisplayMetrics.DENSITY_MEDIUM:
                return MEDIUM_DPI_STATUS_BAR_HEIGHT;
            case DisplayMetrics.DENSITY_LOW:
                return LOW_DPI_STATUS_BAR_HEIGHT;
            default:
                return MEDIUM_DPI_STATUS_BAR_HEIGHT;
        }
    }
//
//    public static void makeCall(Context con, String sCallNumber) {
//        if (sCallNumber == null) {
//            return;
//        }
//
//        sCallNumber = sCallNumber.trim();
//
//        if (sCallNumber.length() >= 7) {
//            Uri number = Uri.parse("tel:" + sCallNumber);
//            Intent intent = new Intent(Intent.ACTION_CALL, number);
//            con.startActivity(intent);
//        }
//    }

    public static int makeMD5(String param) {
        int cs = 0;
        StringBuffer md5 = new StringBuffer();

        try {
            byte[] digest = java.security.MessageDigest.getInstance("MD5").digest(param.getBytes());
            for (int i = 0; i < digest.length; i++) {
                md5.append(Integer.toString((digest[i] & 0xf0) >> 4, 16));
                md5.append(Integer.toString(digest[i] & 0x0f, 16));
                cs += digest[i];
            }
        } catch (java.security.NoSuchAlgorithmException ne) {
            ne.printStackTrace();
        }

        return (cs & 0xffff);
    }


    public static String makeMD5ToString(String param) {
        StringBuffer md5 = new StringBuffer();

        try {
            byte[] digest = java.security.MessageDigest.getInstance("MD5").digest(param.getBytes());
            for (int i = 0; i < digest.length; i++) {
                md5.append(Integer.toString((digest[i] & 0xf0) >> 4, 16));
                md5.append(Integer.toString(digest[i] & 0x0f, 16));
            }
        } catch (java.security.NoSuchAlgorithmException ne) {
            ne.printStackTrace();
        }

        return md5.toString();
    }

    public static int getSignature(Context context) {
        PackageManager pm = context.getPackageManager();

        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] sig = info.signatures;
            String str = new String(sig[0].toChars());
            int checksum = makeMD5(str);
            return checksum;
        } catch (NameNotFoundException e) {
        }

        return 0;
    }

    public static String DeleteDecimal(Context context, String number) {
        String temp = number;

        try {
            if (temp.charAt(temp.length() - 1) == '0'
                    && temp.charAt(temp.length() - 2) == '.'
                    ) {
                temp = temp.substring(0, temp.length() - 2);
            }
        } catch (Exception e) {
            temp = "0";
        }

        return temp;
    }

    public static String SetDate(String str) {
        String temp = str;
        String year = temp.substring(0, 4);
        String month = temp.substring(4, 6);
        String day = temp.substring(6, 8);

        return str = year + "-" + month + "-" + day;
    }

    public static String SetDateTime(String str) {
        String temp = str;
        String year = temp.substring(0, 4);
        String month = temp.substring(4, 6);
        String day = temp.substring(6, 8);
        String hour = temp.substring(8, 10);
        String min = temp.substring(10, 12);
        String sec = temp.substring(12, 14);

        return str = year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
    }

    public static String SetCommar(String str) {
        try {
            DecimalFormat df = new DecimalFormat("#,###,###,###,###,###,###,###,##0");
            BigDecimal bd = new BigDecimal(str);

            return df.format(bd.doubleValue());
        } catch (Exception e) {
            return "0";
        }
    }

    public static String SetPayment(String str) {
        if (str.compareTo("1") == 0) return "선불";
        else if (str.compareTo("2") == 0) return "착불";
        else if (str.compareTo("3") == 0) return "신용";
        else if (str.compareTo("4") == 0) return "송금";
        else if (str.compareTo("5") == 0) return "미수";
        else if (str.compareTo("6") == 0) return "카드";
        else return null;
    }
}