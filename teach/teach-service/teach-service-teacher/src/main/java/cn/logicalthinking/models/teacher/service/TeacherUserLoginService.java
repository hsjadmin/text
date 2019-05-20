package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.constant.RedisConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.cache.RedisManager;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.*;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @note 教师登录
 * @auhtor 卢祖飞
 * @date 2018/12/25,9:45
 */
@Service
public class TeacherUserLoginService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private RedisManager redisManager;

    @Resource
    private StudentDao studentDao;

    private Long time = 604800000L;//token失效时间为1周

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        //获取传入教师电话
        String teacherPhone = data.getParameter("teacherPhone");
        //获取验证码
        String code = data.getParameter("code");

        isNull(teacherPhone, code);

        teacherPhone = teacherPhone.trim();

        code = code.trim();

        //手机验证码效验
        checkPhone(teacherPhone, code);
        //验证用户是否注册，不存在则注册
        Teacher teacher = isRegister(teacherPhone);

        //验证审核状态
        if ("0".equals(teacher.getExamine())) {//待完善资料
            return Result.jsonData(CODE.CODE_302.getKey(), teacher);
        } else if ("1".equals(teacher.getExamine()) || "3".equals(teacher.getExamine())) {//待审核或未通过
            return Result.jsonData(CODE.CODE_303.getKey(), teacher);
        }
        return teacherLogin(teacher);
    }

    //用户存在则登录
    public Result teacherLogin(Teacher teacher) {
        //生成token
        String token = generateToken(teacher);
        if ("1".equals(teacher.getStatus())) {
            throw new ValidataException("用户被禁用");
        }
        if ("0".equals(teacher.getExamine())) {//用户未完善资料
            Result result = Result.jsonData(CODE.CODE_302.getKey(), teacher);
            result.put("token", token);
            return result;
        } else if ("1".equals(teacher.getExamine()) || "3".equals(teacher.getExamine())) {//审核中/未通过
            Result result = Result.jsonData(CODE.CODE_303.getKey(), teacher);
            result.put("token", token);
            return result;
        }
        return Result.jsonMsg("200", "登录成功", "token", token);
    }

    //若用户不存在则注册
    private Teacher teacherRegister(String phone) {
        //接收微信头像
        String hearImg = data.getParameter("hearImg");
        Teacher teacher = new Teacher();
        teacher.setPhone(phone);
        teacher.setCreateTime(DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME));
        teacher.setStatus(0);
        teacher.setExamine("0");
        String userName = getTop() + teacher.getPhone();
        teacher.setUserName(userName);
        teacher.setIsShow(0);
        teacher.setCharges(new BigDecimal("70"));
        //teacher.setLiveRoom(roomId());
        if (StringUtils.isBlank(hearImg)) {
            teacher.setPicture("/image/8.jpg");
        } else {
            String newPath = UploadFileUtil.FilePath + Tools.UUID() + ".jpg";
            HttpDownloader httpDownloader = new HttpDownloader(hearImg, UploadFileUtil.destFile + newPath);
            httpDownloader.download(new HttpDownloader.Callback() {
                @Override
                public void onProgress(long progress) {
                    System.out.println(progress);
                }

                @Override
                public void onFinish() {
                    System.out.println("下载完成");
                    // 设置下载成功的图片
                    teacher.setPicture(newPath);
                }

                @Override
                public void onError(IOException ex) {
                    // 下载失败设置默认图片
                    teacher.setPicture("/image/8.jpg");
                    ex.printStackTrace();
                }
            });
        }
        teacherDao.addTeacher(teacher);

        return teacher;
    }

    private void checkPhone(String teacherPhone, String code) {
        String codeSend = redisManager.get(RedisConstant.TEACHER_REGISTER_LOGIN_SMS_CODE + teacherPhone);
        ParamValidation.isNotNull(codeSend, "验证码失效,请重试!");
        if (!codeSend.equals(code)) {
            throw new ValidataException("验证码错误!");
        }
    }

    //非空验证
    private void isNull(String phone, String code) {
        ParamValidation.isNotNull(phone, "手机号码不能为空");
        ParamValidation.isPhoneNum(phone, "手机格式不正确");
        ParamValidation.isNotNull(code, "验证码不能为空");
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        int i = studentDao.selectStudentCount(map);
        if (i > 0) {
            throw new ValidataException("该手机号已被学生注册,请前往学生端登录");
        }
    }

    //验证是否注册过
    private Teacher isRegister(String phone) {
        Teacher teacher = teacherDao.isRegister(phone);
        if (teacher != null) {
            return teacher;
        }
        teacher = teacherRegister(phone);

        return teacher;
    }

    //生成token
    private String generateToken(Teacher teacher) {
        JSONObject userJson = new JSONObject();
        userJson.put("userId", teacher.getId());
        userJson.put("userName", teacher.getName());
        userJson.put("phone", teacher.getPhone());
        return JwtUtils.createJWT(teacher.getId().toString(), teacher.getUserName(), userJson.toString(), time);
    }

    //随机生成英文字母
    public String getTop() {
        char c = (char) (Math.random() * 26 + 'A');
        return String.valueOf(c);
    }

    //生成随机房间号
    /*public String roomId() {
        String code = SmsCodeGen.getCode();
        Map<String, Object> map = new HashMap<>();
        map.put("liveRoom", code);
        List<Teacher> teachers = teacherDao.selectTeacherListAll(map);
        if (teachers.size() > 0) {
            code = SmsCodeGen.getCode();
        }
        return code;
    }*/
}
