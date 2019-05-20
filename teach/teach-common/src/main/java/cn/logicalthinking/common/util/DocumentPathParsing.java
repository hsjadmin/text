package cn.logicalthinking.common.util;


/**
 * 文件路径解析工具类
 *
 * @author 黄世杰
 * @version 1.0
 * @Description
 * @2018-7-6
 */
public class DocumentPathParsing {


    public static String getFullPath() {
//		String paths=DocumentPathParsing.class.getResource("/").getPath();
//		try {
//			paths = java.net.URLDecoder.decode(paths, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		paths=paths.substring(paths.indexOf("/")+1,paths.indexOf("WEB-INF/classes/")-1);
//		if(LiunxUtil.isLiunx()){
//			paths=LiunxUtil.suPath(paths);
//		}

        String paths = UploadFileUtil.destFile;
        return paths;
    }
}
