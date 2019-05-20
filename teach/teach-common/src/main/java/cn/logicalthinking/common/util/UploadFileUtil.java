package cn.logicalthinking.common.util;

import cn.logicalthinking.common.base.entity.FileItem;
import cn.logicalthinking.common.exception.SysException;
import cn.logicalthinking.common.exception.ValidataException;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @version 1.0
 * @Description 上传文件工具类
 * @2018-9-27
 */
@Component
public class UploadFileUtil {

    //项目前缀路径
//	public static String destFile=DocumentPathParsing.getFullPath();
    public static String destFile;

    //上传文件临时路径
    public static final String uploadPath = "/upload/temporary/";

    //上传文件真正路径
    public static final String FilePath = "/upload/file/";

    //默认头像路径
    public static final String head_portrait = "/image/8.jpg";

    //默认头像路径
    public static final String head_image = "/image/wx_head_img.png";

    //默认封面路径
    public static final String DEFAULT_COVER = "/image/default-cover.jpg";

    //去除文件名带特效字符  ;切割
    public static final String FilePath11 = "%;$;#;@;!;*;^;&;_;+;=;<;>;,;";


    /**
     * @param file
     * @return FileItem  储存文件文件对象
     * @throws SysException
     * @Description 单个文件上传
     * @author 黄世杰
     * @下午5:43:25
     * @version 1.0
     */
    public static FileItem upFileByOne(MultipartFile file) throws SysException {
        FileItem fileItem = null;
        try {
            if (file == null)
                return null;

            //文件为空  停止上传
            if (file.isEmpty())
                return null;

            String filePath = DocumentPathParsing.getFullPath() + uploadPath;

            //获取文件MIME类型
            String fileType = file.getContentType();
            //获取表单中文件组件的名字
            String inputName = file.getName();
            //获取文件的字节大小，单位byte
            long size = file.getSize();
            //获取上传文件的原名
            String fileName = file.getOriginalFilename();

            //处理文件名的特殊字符
            fileName = suFileName(fileName);

            //获取到文件后缀
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            //新名
            String newFileName = Tools.UUID() + suffix;

            fileItem = new FileItem();
            fileItem.setInputName(inputName);
            fileItem.setOldFileName(fileName);
            fileItem.setNewFileName(newFileName);
            fileItem.setFileType(fileType);
            fileItem.setFilePath(uploadPath + newFileName);
            /*fileItem.setMultipartFile(file);*/
            fileItem.setFilePathAll(filePath + newFileName);
            fileItem.setSize(size);
            fileItem.setFileSuffixName(suffix);

            //文件夹不存在就创建
            File file2 = new File(filePath);
            if (file2.exists())
                file2.mkdirs();
            File pathFile = new File(filePath + newFileName);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }

            //写入文件
            file.transferTo(new File(filePath + newFileName));

        } catch (IllegalStateException e1) {
            e1.printStackTrace();
            throw new SysException("文件上传失败");
        } catch (IOException e1) {
            e1.printStackTrace();
            throw new SysException("文件上传失败");
        }
        return fileItem;
    }

    /**
     * @param request
     * @return List<FileItem> 多个文件上传对象
     * @throws SysException
     * @Description 多个文件上传
     * @author 黄世杰
     * @下午6:06:04
     * @version 1.0
     */
    public static List<FileItem> uploadFileByList(HttpServletRequest request) throws SysException {

        List<FileItem> fileItems = new ArrayList<FileItem>();
        try {

            // 创建一个通用的多部分解析器
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());

            // 判断 request 是否有文件上传,即多部分请求
            if (!multipartResolver.isMultipart(request))
                return null;

            // 取得MultipartHttpServletRequest 
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            //获取到所有的input
            Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
            //循环每个input
            for (Map.Entry<String, MultipartFile> map : fileMap.entrySet()) {
                String inputName = map.getKey();
                //根据每个inputName获取到每个input里的多个文件集合
                List<MultipartFile> files = multiRequest.getFiles(inputName);
                //循环获取到每个文件
                for (MultipartFile file : files) {
                    //判断文件为空则跳出当前循环
                    if (file == null)
                        continue;
                    //依次写入到文件
                    FileItem fileItem = upFileByOne(file);

                    fileItems.add(fileItem);
                }
            }

        } catch (Exception e) {

            //异常循环删除文件
            rmoveListItem(fileItems);

            e.printStackTrace();

            throw new SysException("文件上传失败");
        }
        return fileItems;
    }


    /**
     * @param fileItems
     * @Description 异常循环删除文件
     * @author 黄世杰
     * @下午6:15:01
     * @version 1.0
     */
    private static void rmoveListItem(List<FileItem> fileItems) {
        if (fileItems.size() > 0)
            return;
        for (FileItem fileItem : fileItems) {
            //循环删除文件
            File file = new File(fileItem.getFilePathAll());
            file.delete();
        }
    }

    /**
     * @param fileName
     * @return
     * @Description 获取文件后缀名
     * @author 黄世杰
     * @上午10:48:56
     * @version 1.0
     */
    public static String suFileName(String fileName) {
        if (StringUtils.isBlank(fileName))
            return "";
        if (fileName.lastIndexOf(".") != -1) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return "";
    }

    /**
     * @note 解析单个文件
     * @auhtor 卢祖飞
     * @date 2018/12/20,15:39
     * @version 1.0
     */
    public static FileItem getFileItem(String fileInfo) {
        FileItem fileItems = new FileItem();
        try {
            fileItems = JSON.parseObject(fileInfo, FileItem.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ValidataException("fileInfo解析失败");
        }
        return fileItems;
    }

    /**
     * @note 解析多个文件
     * @auhtor 卢祖飞
     * @date 2018/12/20,15:39
     * @version 1.0
     */
    public static List<FileItem> getFileItemList(String fileInfo) {
        List<FileItem> fileItems = new ArrayList<FileItem>();
        try {
            fileItems = JSON.parseArray(fileInfo, FileItem.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ValidataException("fileInfo解析失败");
        }
        return fileItems;
    }

    @Value("${fp.destFile}")
    public void setDestFile(String destFile) {
        UploadFileUtil.destFile = destFile;
    }
}