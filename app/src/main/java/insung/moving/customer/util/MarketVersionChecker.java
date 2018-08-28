package insung.moving.customer.util;

import android.util.Log;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by PC on 2018-02-02.
 */

public class MarketVersionChecker {


    public static String getMarketVersion(String packageName) {
        try {
            Document doc =
                    Jsoup.connect("https://play.google.com/store/apps/details?id=" + packageName).get();
            Elements Version = doc.select(".htlgb").eq(4);
            for (Element mElement : Version) {
                return mElement.text().trim();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

