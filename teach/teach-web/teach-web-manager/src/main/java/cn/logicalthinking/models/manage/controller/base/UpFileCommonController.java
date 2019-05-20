package cn.logicalthinking.models.manage.controller.base;

import cn.logicalthinking.common.base.entity.FileItem;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.util.UploadFileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/common")
@Api(tags = "公共文件上传")
public class UpFileCommonController extends BaseController {

    private static Logger logger = Logger.getRootLogger();
	/**
     * @return
     * @throws Exception
     * @Description 单个文件上传
     * @author 黄世杰
     * @下午6:23:37
     * @version 1.0
     */
    @RequestMapping(value = "/upLoadFile")
    @ApiOperation(value = "多个文件上传", response = Result.class, httpMethod = "POST")
    public Result AdvertisingListList(HttpServletRequest request) throws Exception {

        List<FileItem> uploadFileByList = UploadFileUtil.uploadFileByList(request);

        return Result.jsonData("200", uploadFileByList);
    }

    /**
     * @return
     * @throws Exception
     * @Description 单个文件上传
     * @author 黄世杰
     * @下午6:23:37
     * @version 1.0
     */
    @RequestMapping(value = "/upLoadFileOne")
    @ApiOperation(value = "单个文件上传", response = Result.class, httpMethod = "POST")
    public Result upLoadFileOne(HttpServletRequest request, MultipartFile file) throws Exception {

        FileItem fileIiem = UploadFileUtil.upFileByOne(file);

        return Result.jsonData("200", fileIiem);
    }
}