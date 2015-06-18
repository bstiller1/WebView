package com.blakestiller.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebResourceResponse;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends Activity {
    WebView browser;
    public static String getHtml(String address) throws  IOException, Exception {
        /* HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet, localContext); */
        URL url = new URL(address);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        String result = "";

           /* String line = null;
            while ((line = response.readLine()) != null) {
                result += line + "\n";
            } */
        String response = IOUtils.toString(in, "UTF-8");
        return response;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        browser = (WebView) findViewById(R.id.webView);
            //String url = "http://blakestiller.com/hfpc/index.html";
            //browser.setWebViewClient(new WebViewClient());
            WebSettings settings = browser.getSettings();
            settings.setJavaScriptEnabled(true);
            browser.setWebChromeClient(new WebChromeClient());
            //browser.loadUrl("http://blakestiller.com/hfpc/");
            browser.loadDataWithBaseURL("http://blakestiller.com/hfpc/assets/", getHtml("http://blakestiller.com/hfpc/index.html"), "text/html", "UTF-8", "http://blakestiller.com/hfpc/");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
