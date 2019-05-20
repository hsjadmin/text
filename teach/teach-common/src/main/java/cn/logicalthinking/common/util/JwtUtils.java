package cn.logicalthinking.common.util;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * jwt实现方式token加密
 * @Description
 * @author 黄世杰 
 * @2018-8-24
 * @version  1.0
 */
public class JwtUtils {
	
	private static String base64Security="logicalthinking";
	private static String audience="audience";
	
	public static void main(String[] args) {
		String userId="1";
		Long time=604800000L;
		String userName="";
		JSONObject userInfo = new JSONObject();
		userInfo.put("userName", "黄大侠");
		userInfo.put("userId", userId);
		userInfo.put("roleName", "管理员");
		String anth="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNTEwZjFiOTUzMDM0ODczODZiYzE2MTAwZjkzM2JjZCIsInN1YiI6IntcInBob25lXCI6XCJjbi5sb2dpY2FsdGhpbmtpbmcuY29tbW9uLmVudGl0eS5TeXNfdXNlclwiLFwidXNlcklkXCI6XCIxNTEwZjFiOTUzMDM0ODczODZiYzE2MTAwZjkzM2JjZFwiLFwidXNlck5hbWVcIjpcImhzalwifSIsImlhdCI6MTUzNTA4OTk4OCwiaXNzIjoiMTUxMGYxYjk1MzAzNDg3Mzg2YmMxNjEwMGY5MzNiY2QiLCJhdWQiOiJhdWRpZW5jZSIsImV4cCI6MTUzNTY5NDc4OCwibmJmIjoxNTM1MDg5OTg4fQ.gCvFpHkbX_pNmeny-u82dr46vOmtTiz7q7jvabPsaaM";
		String createJWT = createJWT(userId,userName, userInfo.toString(), time);
		Claims claims = parseJWT(createJWT);
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + DateUtil.getDateStr(claims.getExpiration(),DateUtil.DATE_TIME));

	}
	
	/**
	 * @Description  jwt解密token
	 * @author 黄世杰
	 * @上午10:14:51
	 * @version  1.0
	 * @param jsonWebToken
	 * @return
	 */
	public static Claims parseJWT(String jsonWebToken) {
	    try {
              
	    		Claims claims = Jwts.parser()
              .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
              .parseClaimsJws(jsonWebToken).getBody();
               return claims;
               
	     } catch (Exception ex) {
        	 return null;
	     }
	}
	
	/**
	 * @Description 创建token
	 * @author 黄世杰
	 * @上午10:12:48
	 * @version  1.0
	 * @param userId  用户id
	 * @param userInfo  用户信息的json数据
	 * @param time  过期时间  单位为毫秒
	 * @return
	 */
	public static String createJWT(String userId,String userName,String userInfo,long time) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		//生成签名密钥 就是一个base64加密后的字符串？
		byte[] apiKeySecretBytes =DatatypeConverter.parseBase64Binary (base64Security);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());
		//添加构成JWT的参数
		JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
		.setId(userId)//用户id
		.setSubject(userInfo) //主题，个人的一些信息
		.setIssuedAt(now) //创建时间
		.setIssuer(userName) //发送谁
		.setAudience(audience) //个人签名
		.signWith(signatureAlgorithm, signingKey); //估计是第三段密钥
		//添加Token过期时间
		if (time >= 0) {
			//过期时间
			long expMillis = nowMillis + time;
			//现在是什么时间
			Date exp = new Date(expMillis);
			//系统时间之前的token都是不可以被承认的
			builder.setExpiration(exp).setNotBefore(now);
		}
		//生成JWT
		return builder.compact();
	}
}
