package cn.logicalthinking.common.util;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	/** 数字 包括小数点*/
	public final static Pattern NUMBERS = Pattern.compile("^\\d+(\\.\\d+)?$");
	/** IP v4 */
	public final static Pattern IPV4 = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
	/** 邮件 */
	public final static Pattern EMAIL = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
	/** 移动电话 */
	public final static Pattern MOBILE = Pattern.compile("(?:0|86|\\+86)?1[3456789]\\d{9}");
	public final static Pattern PHONE = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
	/** 身份证号码 */
	public final static Pattern CITIZEN_ID = Pattern.compile("[1-9]\\d{5}[1-2]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}(\\d|X|x)");
	/** URL */
	public final static Pattern URL = Pattern.compile("(https://|http://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
	/** 中国车牌号码 */
	public final static Pattern PLATE_NUMBER = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$");
	/** 日期是否符合正则表达式：包括年月日，闰年、平年和每月31天、30天和闰月的28天或者29天 */
	public static final Pattern DATE = Pattern  
            .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");
	
	
	/**
	 * 获取UUID
	 * @return
	 */
	public static String UUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
	/**
	 * 
	 * 通过正则表达式验证
	 * 
	 * @param pattern
	 *            正则
	 * 
	 * @param value
	 *            值
	 * 
	 * @return 是否匹配正则
	 * 
	 */
	public static boolean isMactchRegex(Pattern pattern, String value) {
		Matcher mat = pattern.matcher(value);
		return mat.matches();
	}

	/**
	 * 随机生成六位数验证码
	 * 
	 * @return
	 */
	public static int getRandomNum() {
		Random r = new Random();
		return r.nextInt(900000) + 100000;// (Math.random()*(999999-100000)+100000)
	}

	/**
	 * 判断字符串是否为数字包括小数点
	 * 
	 * @param strnum
	 * @return boolean
	 */
	public static boolean isNumber(String strnum) {
		return isMactchRegex(NUMBERS, strnum);
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return boolean
	 */
	public static boolean isEmail(String email) {
		return isMactchRegex(EMAIL, email);
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobileNumber
	 * @return boolean
	 */
	public static boolean isMobile(String mobileNumber) {
		return isMactchRegex(MOBILE, mobileNumber);
	}

	/**
	 * 验证日期  格式：yyyy-MM-dd
	 * @author ShiXian
	 * @return boolean
	 * @date 2017年10月10日
	 *
	 */
	public static boolean isDate(String strdt){
		return isMactchRegex(DATE, strdt);
	}
	
	/**
	 * 验证IP
	 * @author ShiXian
	 * @return boolean
	 * @date 2017年10月10日
	 *
	 */
	public static boolean isIp(String strip){
		return isMactchRegex(IPV4, strip);
	}
	
	/**
	 * 验证身份证号
	 * @author ShiXian
	 * @return boolean
	 * @date 2017年10月10日
	 *
	 */
	public static boolean isIDCard(String str){
		return isMactchRegex(CITIZEN_ID, str);
	}
	
	/**
	 * 验证网址
	 * @author ShiXian
	 * @return boolean
	 * @date 2017年10月10日
	 *
	 */
	public static boolean isURL(String str){
		return isMactchRegex(URL, str);
	}
	
	/**
	 * 验证车牌号
	 * @author ShiXian
	 * @return boolean
	 * @date 2017年10月10日
	 *
	 */
	public static boolean isPlateNumber(String str){
		return isMactchRegex(PLATE_NUMBER, str);
	}
	
	public static void main(String[] args) {
		System.out.println(Tools.isMobile("16600000000"));
	}
}
