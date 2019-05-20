package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.constant.YunXinConstant;
import cn.logicalthinking.common.base.entity.FileItem;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xhx
 * @version 1.0
 * @Description 学生表 修改
 * @date 2018-12-19
 */
@Service
public class StUpdateStudentService extends AbstractService {

    @Resource
    private StudentDao studentDao;

    @Value("${PROJECT_URL}")
    private String projectUrl;

    private IClientData data;

    @Override
    protected void before(IClientData data) {

        Student student = (Student) data.getObject();
        ParamValidation.isNotNull(student.getName(), "姓名不能为空");
        ParamValidation.isNotNull(student.getGrade(), "就读年级不能为空");

    }

    /**
     * emoji表情替换
     *
     * @param source  原字符串
     * @param slipStr emoji表情替换成的字符串
     * @return 过滤后的字符串🍎
     */
    public static String filterEmoji(String source, String slipStr) {
        if (StringUtils.isNotBlank(source)) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
        } else {
            return source;
        }
    }

    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Student studentUser = data.getStudentUser();

        Student student = (Student) data.getObject();
        student.setPhone(null);
        student.setId(studentUser.getId());

        student.setName(filterEmoji(student.getName(), ""));

        HashMap<String, Object> map = new HashMap<>();
        map.put("userName", student.getUserName());
        if (studentDao.selectStudentCount(map) != 0) {
            if (!Objects.equals(studentUser.getUserName(), student.getUserName())) {
                throw new BusinessException("用户名已存在");
            }
        }

        // 修改头像
        String fileInfo = data.getParameter("fileInfo");
        if (StringUtils.isNotBlank(fileInfo)) {
            FileItem fileItem = UploadFileUtil.getFileItem(fileInfo);
            //源文件路径
            String srcFileName = UploadFileUtil.destFile + fileItem.getFilePath();
            //新文件路径
            String newPath = UploadFileUtil.FilePath + Tools.UUID() + UploadFileUtil.suFileName(fileItem.getFilePath());
            //复制文件
            FileUtil.copyFile(srcFileName, UploadFileUtil.destFile + newPath, true);

            student.setPicture(newPath);

        }

        // 修改云信账号信息
        Map<String, Object> map1 = new HashMap<>();
        map1.put(YunXinConstant.ACCID, studentUser.getPhone());
        map1.put(YunXinConstant.ICON, projectUrl + "teach-file" + student.getPicture());
        map1.put(YunXinConstant.NAME, student.getName());
        String yunXinRegister = HttpRequest.yunXinRegister(YunXinConstant.UPDATE_USER_INFO, map1);
        System.out.println(map1);
        System.out.println(yunXinRegister);


        if (studentDao.updateStudent(student) == 0) {
            throw new BusinessException("修改失败");
        }

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(), "信息修改成功");

    }

}
