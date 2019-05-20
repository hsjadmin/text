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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @note 完善信息
 * @auhtor 卢祖飞
 * @date 2018/12/25,11:51
 */
@Service
public class TeacherPerfectInfoService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    @Value("${PROJECT_URL}")
    private String url;

    @Resource(name = "teacherUserLoginService")
    private TeacherUserLoginService teacherUserLoginService;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Teacher teacher = (Teacher) data.getObject();

        String fileInfo = data.getParameter("fileInfo");

        isNull(teacher, fileInfo);

        Teacher teacher1 = teacherDao.selectTeacherById(teacher.getId());

        ParamValidation.isNotNull(teacher1,"用户不存在!");

        if("2".equals(teacher1.getExamine())){
            return teacherUserLoginService.teacherLogin(teacher);
        }
        FileItem fileItem = UploadFileUtil.getFileItem(fileInfo);

        //源文件路径
        String srcFileName = UploadFileUtil.destFile + fileItem.getFilePath();
        //新文件路径
        String newPath = UploadFileUtil.FilePath + Tools.UUID() + UploadFileUtil.suFileName(fileItem.getFilePath());
        //复制文件
        FileUtil.copyFile(srcFileName, UploadFileUtil.destFile + newPath, true);
        teacher.setCardImg(newPath);
        teacher.setExamine("1");
        teacherDao.updateTeacher(teacher);
        String yunXinRegister = null;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put(YunXinConstant.ACCID,teacher1.getPhone());
            map.put(YunXinConstant.ICON,url+"teach-file"+teacher1.getPicture());
            map.put(YunXinConstant.NAME,teacher.getName());
            yunXinRegister = HttpRequest.yunXinRegister(YunXinConstant.REGISTER_URL,map);
            System.out.println(yunXinRegister);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.jsonData(CODE.CODE_303.getKey(), yunXinRegister);
    }

    //非空验证
    private void isNull(Teacher teacher, String fileInfo) {
        ParamValidation.isNotNull(teacher.getName(), "姓名不能为空");
        ParamValidation.isNotNull(teacher.getGrade(), "授课年级不能为空");
        ParamValidation.isNotNull(teacher.getSubject(), "授课科目不能为空");
        ParamValidation.isNotNull(teacher.getExperience(), "授课年龄不能为空");
        ParamValidation.isNotNull(teacher.getIdCard(), "身份证不能为空");
        ParamValidation.isNotNull(fileInfo, "教师资格证不能为空");
    }
}
