package cn.logicalthinking.common.pay;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author XHX
 * @date 2018/11/6
 */
public class MyWXPayConfig extends WXPayConfig {

    private byte[] certData;

    public MyWXPayConfig() throws Exception {
//        String certPath = "/path/to/apiclient_cert.p12";
//        File file = new File(certPath);
//        InputStream certStream = new FileInputStream(file);
//        this.certData = new byte[(int) file.length()];
//        certStream.read(this.certData);
//        certStream.close();

        this.certData = new byte[0];
    }

    @Override
    public String getAppID() {
        return WxProp4Student.MP_ID;
    }

    @Override
    public String getMchID() {
        return WxProp4Student.MCH_ID;
    }

    @Override
    public String getKey() {
        return WxProp4Student.MCH_API_KEY;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
//                System.out.println(domain);
//                System.out.println(elapsedTimeMillis);
                if (ex != null) {

                    ex.printStackTrace();
                }
            }

            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
    }
}
