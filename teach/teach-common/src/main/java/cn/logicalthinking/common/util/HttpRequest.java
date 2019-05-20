package cn.logicalthinking.common.util;


import cn.logicalthinking.common.base.constant.YunXinConstant;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2018/12/28,17:18
 */
@Component
public class HttpRequest {

    private static String appKey;

    private static String appSecret;


    /**
     *
     * @param url 地址
     * @param map 参数
     * @return
     * @note 云信注册/修改
     * @auhtor 卢祖飞
     * @date 2018/12/29,10:58
     * @version 1.0
     * @throws Exception
     */
    public static String yunXinRegister(String url, Map<String,Object> map) throws Exception{
        BasicHttpClientConnectionManager connManager;
        connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );
        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();
        HttpPost httpPost = new HttpPost(url);
        String nonce =  SmsCodeGen.getCode();
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuildr.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            nvps.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        }
            nvps.add(new BasicNameValuePair("token","123456"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);

        return EntityUtils.toString(response.getEntity(), "utf-8");
    }

    /**
    *@note 查询房间是否存在
    *@auhtor 卢祖飞
    *@date 2018/12/29,17:01
    *@version 1.0
    */
    public static String isExistRoom(String roomId) throws Exception{

        BasicHttpClientConnectionManager connManager;
        connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );
        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();
        String url = YunXinConstant.IS_EXISTENCE_ROOM;
        url = url.replaceAll("\\{id\\}",roomId);
        HttpGet httpGet = new HttpGet(url);
        String nonce =  SmsCodeGen.getCode();
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuildr.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpGet.addHeader("AppKey", appKey);
        httpGet.addHeader("Nonce", nonce);
        httpGet.addHeader("CurTime", curTime);
        httpGet.addHeader("CheckSum", checkSum);
        httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 执行请求
        HttpResponse response = httpClient.execute(httpGet);

        return EntityUtils.toString(response.getEntity(), "utf-8");
    }

    /**
     *
     * @param url 地址
     * @param map 参数
     * @return
     * @note 消息推送
     *@auhtor 卢祖飞
     * @date 2018/12/29,10:58
     *@version 1.0
     * @throws Exception
     */
    public static String sendMessagePush(String url,Map<String,Object> map) throws Exception {
        BasicHttpClientConnectionManager connManager;
        connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );
        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();

        HttpPost httpPost = new HttpPost(url);
        String nonce =  SmsCodeGen.getCode();
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuildr.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            nvps.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);

        return EntityUtils.toString(response.getEntity(), "utf-8");
    }

    @Value("${yunxin_appKey}")
    public void setAppKey(String app_Key) {
        appKey = app_Key;
    }
    @Value("${yunxin_appSecret}")
    public void setAppSecret(String app_Secret) {
        appSecret = app_Secret;
    }

    //测试
    public static void main(String[] args)  throws Exception{

        BasicHttpClientConnectionManager connManager;
        connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );
        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();
        //HttpPost httpPost = new HttpPost(YunXinConstant.UPDATE_USER_INFO);
        HttpPost httpPost = new HttpPost("https://api.netease.im/nimserver/user/getUinfos.action");
        String nonce =  SmsCodeGen.getCode();
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuildr.getCheckSum("26326c1782d0", nonce ,curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", "7052705f380531c9ae7d25f22808e418");
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("accids", "[15170192080]"));
        /*nvps.add(new BasicNameValuePair("icon","http://129.204.144.82:8080/upload/file/663b0b004b2b401b99bde4639639b47e.jpg"));
        nvps.add(new BasicNameValuePair("token","123456"));*/
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);

        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
    }

}
