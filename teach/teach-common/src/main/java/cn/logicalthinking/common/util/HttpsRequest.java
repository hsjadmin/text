package cn.logicalthinking.common.util;

import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;

/**
 * 发送HTTPS请求
 */
public class HttpsRequest {

    /**
     * HTTPS 请求
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式 POST或GET
     * @param message       参数
     * @return
     * @throws Exception
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String message) throws Exception {
        JSONObject json = null;
        TrustManager[] tm = {new MyX509TrustManager()};
        SSLContext ssl = SSLContext.getInstance("SSL", "SunJSSE");
        ssl.init(null, tm, new SecureRandom());

        SSLSocketFactory ssf = ssl.getSocketFactory();

        URL urlGet = new URL(requestUrl);

        HttpsURLConnection http = (HttpsURLConnection) urlGet.openConnection();

        http.setSSLSocketFactory(ssf);
        http.setDoOutput(true);
        http.setDoInput(true);
        http.setUseCaches(false);
        http.setRequestMethod(requestMethod);

        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
        System.setProperty("sun.net.client.defaultReadTimeout", "30000");

        if (message != null) {
            OutputStream outputStream = http.getOutputStream();
            outputStream.write(message.getBytes("UTF-8"));
            outputStream.close();
        }

        InputStream is = http.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        String str = null;
        StringBuffer sb = new StringBuffer();

        while ((str = reader.readLine()) != null) {
            sb.append(str);
        }

        //释放资源
        isr.close();
        reader.close();
        is.close();
        is = null;
        http.disconnect();
        json = JSONObject.parseObject(sb.toString());
        return json;
    }

    /**
     * @param requestUrl
     * @param requestMethod
     * @param message
     * @return
     * @throws IOException
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String message) throws IOException {
        JSONObject json = null;
        URL urlGet = new URL(requestUrl);
        HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

        http.setDoOutput(true);
        http.setDoInput(true);
        http.setUseCaches(false);
        http.setRequestMethod(requestMethod.toUpperCase());

        if (message != null) {
            OutputStream outputStream = http.getOutputStream();
            outputStream.write(message.getBytes("UTF-8"));
            outputStream.close();
        }

        InputStream is = http.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        String str = null;
        StringBuffer sb = new StringBuffer();

        while ((str = reader.readLine()) != null) {
            sb.append(str);
        }

        //释放资源
        isr.close();
        reader.close();
        is.close();
        is = null;
        http.disconnect();
        json = JSONObject.parseObject(sb.toString());
        return json;
    }


    public static StringBuffer httpRequestRaw(String requestUrl, String requestMethod, String message) throws IOException {
        URL urlGet = new URL(requestUrl);
        HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

//        http.set
        http.setDoOutput(true);
        http.setDoInput(true);
        http.setUseCaches(false);
        http.setRequestMethod(requestMethod.toUpperCase());
//        http.setRequestProperty("User-Agent", "Workflow/508 CFNetwork/897.15 Darwin/17.5.0");
        http.setRequestProperty("Host", "aweme.snssdk.com");

        if (message != null) {
            OutputStream outputStream = http.getOutputStream();
            outputStream.write(message.getBytes("UTF-8"));
            outputStream.close();
        }

        InputStream is = http.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        String str = null;
        StringBuffer sb = new StringBuffer();

        while ((str = reader.readLine()) != null) {
            sb.append(str);
        }

        //释放资源
        isr.close();
        reader.close();
        is.close();
        is = null;
        http.disconnect();
        return sb;
    }

    public static String setMap(Map<String, Object> map) {
        String message = "";
        if (map != null) {
            for (Entry<String, Object> entry : map.entrySet()) {
                message += entry.getKey() + "=" + entry.getValue() + "&";
            }
        }
        return message;
    }

    public static String httpRequestStr(String requestUrl, String requestMethod, String message) throws IOException {
        StringBuffer sb = new StringBuffer();
        URL urlGet = new URL(requestUrl);
        HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

        http.setDoOutput(true);
        http.setDoInput(true);
        http.setUseCaches(false);
        http.setRequestMethod(requestMethod);

        if (message != null) {
            OutputStream outputStream = http.getOutputStream();
            outputStream.write(message.getBytes("UTF-8"));
            outputStream.close();
        }

        InputStream is = http.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        String str = null;
        while ((str = reader.readLine()) != null) {
            sb.append(str);
        }
        //释放资源
        isr.close();
        reader.close();
        is.close();
        is = null;
        http.disconnect();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
//        StringBuffer get = httpRequestRaw("https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fd70000bh3t4411h1vv6g24rmkg", "get", "");
//        String res = "<a href=\"http://v3-dy.ixigua.com/8964c869805b3b881b6cc1216e9c53e7/5c482fe9/video/m/220eabfb65ff4954cb489cd8fad9226ea8a1155615100008a7f998a5a38/?rc=Z2lpOWZkNzw4ZGk0NTxkNUApQHRAb0U6MzUzOTgzNDk1OTY0PDNAKXUpQGczdylAZmxkamV6aGhkZjs0QDMuXmEvYjZfNDEzY2AwLTJzLW8jbyM2MjM2NC0uLS0tLi4vLS4vaTpiLW8jOmAtbyNtbCtiK2p0OiMvLl4\">Found</a>.";

        Process exec = Runtime.getRuntime().exec("curl -X GET \"https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fd70000bh3t4411h1vv6g24rmkg\"");
        InputStream inputStream = exec.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        String line = br.readLine();

        String res = line;
        System.out.println(res);
        Matcher matcher = Patterns.WEB_URL.matcher(res);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}


class MyX509TrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
