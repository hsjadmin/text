package cn.logicalthinking.common.util;

import org.apache.log4j.Logger;

import java.io.*;


/**
 * 序列化工具类
 * 
 * @author ShiXian
 * @version 1.0
 * @date 2017年12月21日
 *
 */
public class SerializeUtil {

	private static Logger logger = Logger.getLogger(SerializeUtil.class);

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return Object
	 * @date 2017年12月21日
	 *
	 */
	public static Object deserialize(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		ByteArrayInputStream bais = null;
		ObjectInputStream bis = null;

		try {
			bais = new ByteArrayInputStream(bytes);
			bis = new ObjectInputStream(new BufferedInputStream(bais));
			return bis.readObject();

		} catch (ClassNotFoundException e) {
			logger.error("Failed to deserialize object type", e);
		} catch (Exception e) {
			logger.error("Failed to deserialize", e);
		} finally {
			try {
				if (bais!=null)
					bais.close();
				if (bis!=null)
					bis.close();
			} catch (IOException e) {
				logger.error("Failed to close serialize", e);
			}
		}
		return null;
	}

	/**
	 * 序列化
	 * 
	 * @return byte[]
	 * @param obj
	 * @date 2017年12月21日
	 *
	 */
	public static byte[] serialize(Object obj) {
		if (obj == null) {
			return null;
		}
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
		} catch (IOException e) {
			logger.error("Failed to serialize", e);
		} finally {
			try {
				if (baos!=null)
					baos.close();
				if (oos!=null)
					oos.close();
			} catch (IOException e) {
				logger.error("Failed to close serialize", e);
			}
		}
		return baos.toByteArray();
	}
}
