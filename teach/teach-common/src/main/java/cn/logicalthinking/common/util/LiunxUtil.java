package cn.logicalthinking.common.util;

/**
 * linux路径判断
 * @author 黄世杰
 * @date 2017-11-15
 * @下午7:44:34
 * 有事留言QQ邮箱:1907377985@qq.com
 * @com.scia.uitls
 * @LiunxUtil.java
 */
public class LiunxUtil {
	
	/**
	 * 判断是否是linux系统
	 * @return
	 */
	public static boolean isLiunx(){
    	String osname = System.getProperty ("os.name");
		if( osname.indexOf("linux") != -1 || osname.indexOf("Linux") != -1 ){
			return true;
		}
		return false;
	}
	
	/**
	 * 加前杠
	 * @param path
	 * @return
	 */
	public static String suPath(String path){
		return "/"+path;
	}
	
	/**
	 * 加后杠
	 * @param path
	 * @return
	 */
	public static String prPath(String path){
		return path+"/";
	}
}
