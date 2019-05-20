package cn.logicalthinking.models.student.service.question;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.SmsEnum;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import cn.logicalthinking.common.dao.MessageTeacherDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.ApplicationParameter;
import cn.logicalthinking.common.entity.MessageTeacher;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.util.DateUtil;
import cn.logicalthinking.common.util.ParamValidation;
import cn.logicalthinking.common.util.TencentSMS;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @note 发送疑难提醒短信
 * @auhtor 卢祖飞
 * @date 2019/1/7,10:13
 */
@Service
public class StSendReminderService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private MessageTeacherDao messageTeacherDao;

    @Resource
    private ApplicationParameterDao applicationParameterDao;


    private IClientData data;


    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        String teacherId = data.getParameter("teacherId");

        Student studentUser = data.getStudentUser();

        ApplicationParameter applicationParameter = applicationParameterDao.selectApplicationParameterByName("answerSwitch");

        Teacher teacher = teacherDao.selectTeacherById(Integer.parseInt(teacherId));

        ParamValidation.isNotNull(teacher,"老师不存在");


        //添加教师消息
        MessageTeacher messageTeacher = new MessageTeacher();
        messageTeacher.setTeacherId(teacher.getId());
        messageTeacher.setTitle("疑难提醒");
        messageTeacher.setComment(studentUser.getName()+"同学遇到问题,呼唤您上线答疑了!");
        messageTeacher.setCreateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
        messageTeacher.setStatus(0);

        messageTeacherDao.addMessageTeacher(messageTeacher);


        if ("开".equals(applicationParameter.getValue()) || "开" == applicationParameter.getValue()){
            String phone = teacher.getPhone();

            String[] parm = {};

            SmsSingleSenderResult smsSingleSenderResult = TencentSMS.sengSMS(phone, SmsEnum.FOR_ANSWER, parm);

            if("OK".equalsIgnoreCase(smsSingleSenderResult.errMsg)){
                return Result.jsonMsg("200","短信发送成功");
            }
            return Result.jsonMsg("500","请勿重复发送");
        }
            return Result.jsonMsg("200","不发送短信");
    }
}
