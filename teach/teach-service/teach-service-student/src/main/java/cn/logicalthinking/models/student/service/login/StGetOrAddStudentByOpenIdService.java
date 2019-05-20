package cn.logicalthinking.models.student.service.login;

import cn.logicalthinking.common.base.constant.YunXinConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 完善信息
 * @date 2018-12-19
 */
@Service
public class StGetOrAddStudentByOpenIdService extends AbstractService {

    @Resource
    private StudentDao studentDao;

    @Resource
    private TeacherDao teacherDao;

    @Value("${PROJECT_URL}")
    private String projectUrl;

    @Override
    protected void before(IClientData data) {
        Student student = (Student) data.getObject();
        ParamValidation.isNotNull(student.getOpenId(), "openid不能为空");
        ParamValidation.isNotNull(student.getName(), "姓名不能为空");
        ParamValidation.isNotNull(student.getPhone(), "电话不能为空");
        ParamValidation.isNotNull(student.getGrade(), "就读年级不能为空");

    }

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Student student = (Student) data.getObject();

        String openId = student.getOpenId();

        Student temp = studentDao.selectStudentByOpenId(openId);

        if (temp != null) {
            throw new BusinessException("信息已完善");
        }

        String phone = student.getPhone();
        Map<String, Object> mapCheck = new HashMap<>(1);
        mapCheck.put("phone", phone);
        if (studentDao.selectStudentCount(mapCheck) != 0) {
            throw new BusinessException("该手机号已被注册了，请更换手机号");
        }
        if (teacherDao.selectTeacherCount(mapCheck) != 0) {
            throw new BusinessException("该手机号已被教师端小程序注册了，请到教师端登录");
        }

        // 不存在头像链接
        if (StringUtils.isBlank(student.getPicture())) {
            student.setPicture(UploadFileUtil.head_image);
        } else {
            // 下载图片
            String newPath = UploadFileUtil.FilePath + Tools.UUID() + ".png";
            HttpDownloader httpDownloader = new HttpDownloader(student.getPicture(), UploadFileUtil.destFile + newPath);
            httpDownloader.download(new HttpDownloader.Callback() {
                @Override
                public void onProgress(long progress) {
                    System.out.println(progress);
                }

                @Override
                public void onFinish() {
                    System.out.println("下载完成");
                    // 设置下载成功的图片
                    student.setPicture(newPath);
                }

                @Override
                public void onError(IOException ex) {
                    // 下载失败设置默认图片
                    student.setPicture(UploadFileUtil.head_image);
                    ex.printStackTrace();
                }
            });
        }

        // 设置昵称
        if (StringUtils.isBlank(student.getName())) {
            student.setName("学生用户" + Tools.UUID().substring(0, 5));
        }
        student.setName(filterEmoji(student.getName(), ""));
        // 设置用户名
        student.setUserName("P_" + student.getPhone());

        student.setOpenId(openId);

        // 创建云信账号
        Map<String, Object> map = new HashMap<>();
        map.put(YunXinConstant.ACCID, student.getPhone());
        map.put(YunXinConstant.ICON, projectUrl + "teach-file" + student.getPicture());
        map.put(YunXinConstant.NAME, student.getName());
        String yunXinRegister = HttpRequest.yunXinRegister(YunXinConstant.REGISTER_URL, map);
        System.out.println(yunXinRegister);

        if (studentDao.addStudent(student) == 0) {
            throw new BusinessException("完善失败");
        }
        return Result.jsonMsg(CODE.CODE_200_SELECT.getKey(), "完善信息成功");
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
}
