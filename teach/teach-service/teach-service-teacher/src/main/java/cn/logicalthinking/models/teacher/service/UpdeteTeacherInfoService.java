package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.constant.YunXinConstant;
import cn.logicalthinking.common.base.entity.FileItem;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @note 修改老师信息
 * @auhtor 卢祖飞
 * @date 2018/12/26,11:22
 */
@Service
public class UpdeteTeacherInfoService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    @Value("${PROJECT_URL}")
    private String url;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Teacher teacherUser = data.getTeacherUser();

        Teacher teacher = (Teacher) data.getObject();

        String hearImg = data.getParameter("hearImg");

        FileItem fileItem = UploadFileUtil.getFileItem(hearImg);

        String newPath = "";

        if(fileItem != null){
            //源文件路径
            String srcFileName = UploadFileUtil.destFile + fileItem.getFilePath();
            //新文件路径
            newPath = UploadFileUtil.FilePath + Tools.UUID() + UploadFileUtil.suFileName(fileItem.getFilePath());
            //复制文件
            FileUtil.copyFile(srcFileName, UploadFileUtil.destFile + newPath, true);

            teacher.setPicture(newPath);
            try {
                Map<String, Object> map = new HashMap<>();
                map.put(YunXinConstant.ACCID,teacherUser.getPhone());
                map.put(YunXinConstant.ICON,url+"teach-file"+newPath);
                String yunXinRegister = HttpRequest.yunXinRegister(YunXinConstant.UPDATE_USER_INFO,map);
                System.out.println(yunXinRegister);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        teacher.setId(teacherUser.getId());

        teacher.setUpdateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));

        teacherDao.updateTeacher(teacher);

        return Result.jsonData(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
    }
}
