package cn.logicalthinking.common.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;

/**
 * 日志记录工具类
 * @Description
 * @author 黄世杰 
 * @2019-3-27
 * @version  1.0
 */
public class LogUtil {
	
    public static final Logger log = Logger.getLogger(LogUtil.class);
    
    /**
     * 默认日志记录
     */
    public static int logType_default=0;
    
    /**
     * 支付日志记录
     */
    public static int logType_pay=1;
    
    /**
	 * 日志记录：
	 * 		1.打印控制台
	 * 		2.日志记录到文件
	 * @Description
	 * @author 黄世杰
	 * @上午9:47:03
	 * @version  1.0
	 * @param msg
	 * @throws IOException
	 */
	public static void recordLog(String msg)  {
		msg= DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME)+"》》》》》》》》"+msg;
		print(msg);
		printLog(msg);
//		try {
//			writeFile(msg,logType_default);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
	}
	
	/**
	 * 日志记录：
	 * 		1.打印控制台
	 * 		2.日志记录到文件
	 * @Description
	 * @author 黄世杰
	 * @上午9:47:03
	 * @version  1.0
	 * @param msg
	 * @throws IOException
	 */
	public static void recordLog(String key,String msg)  {
		msg= DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME)+"》》》》"+key+"》》》》"+msg;
		print(msg);
		printLog(msg);
//		try {
//			writeFile(msg,logType_default);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
	}
	
	/**
	 * 日志记录：
	 * 		1.打印控制台
	 * 		2.日志记录到文件
	 * @Description
	 * @author 黄世杰
	 * @上午9:47:03
	 * @version  1.0
	 * @param msg
	 * @throws IOException
	 */
	public static void recordPayLog(String key,Object msg)  {
		msg= DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME)+"》》》》"+key+"》》》》"+msg;
		print(msg);
		printLog(msg);
//		try {
//			writeFile(msg,logType_pay);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		
	}
	
	
	/**
	 * 日志记录：
	 * 		1.打印控制台
	 * 		2.日志记录到文件
	 * @Description
	 * @author 黄世杰
	 * @上午9:47:03
	 * @version  1.0
	 * @param msg
	 * @throws IOException
	 */
	public static void recordPayLog(String msg)  {
		msg= DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME)+"》》》》》》》》"+msg;
		print(msg);
		printLog(msg);
//		try {
//			writeFile(msg,logType_pay);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		
	}
	
	private static void print(Object msg){
		System.out.println(msg);
	}
	
	private static void printLog(Object message){
		log.error(message);
		log.debug(message);
	}
	
	
	/**
	 * 写入文件
	 * @Description
	 * @author 黄世杰
	 * @上午9:52:24
	 * @version  1.0
	 * @param msg
	 * @throws IOException
	 */
//	private static void writeFile(Object msg,int logType) throws IOException{
//		String date = DateUtil.getDateStr(new Date(),DateUtil.DATE);
//		String destFile=FilePath.img_destFile+"/voucher_shop_log/"+date;
//		if(logType_default==logType)
//			destFile=destFile+"/log/";
//		if(logType==logType_pay)
//			destFile=destFile+"/pay/";
//		else
//			destFile=destFile+"/log/";
//		System.out.println("destFile:"+destFile);
//		File file=new File(destFile);
//		if(!file.exists())
//			 file.mkdirs();
//		destFile=destFile+date+".txt";
//		File fileParent = file.getParentFile(); 
//		if(!fileParent.exists()){ 
//		 fileParent.mkdirs(); 
//		} 
//		
//		File ff=new File(destFile);
//		if (!ff.exists())
//			ff.createNewFile();
//		BufferedWriter out = null;
//		FileOutputStream fileOutputStream=null;
//		OutputStreamWriter outputStreamWriter = null;
//		try {
//			fileOutputStream= new FileOutputStream(ff, true);
//			outputStreamWriter = new OutputStreamWriter(fileOutputStream);
//			out = new BufferedWriter(outputStreamWriter);
//			out.write(msg + "\r\n");
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				out.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				fileOutputStream.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				outputStreamWriter.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}
