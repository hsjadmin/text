package cn.logicalthinking.models.student.service.buiz;

import cn.logicalthinking.common.base.constant.MessageTemplate;
import cn.logicalthinking.common.base.enums.SmsEnum;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import cn.logicalthinking.common.dao.MessageStudentDao;
import cn.logicalthinking.common.dao.MessageTeacherDao;
import cn.logicalthinking.common.entity.MessageStudent;
import cn.logicalthinking.common.entity.MessageTeacher;
import cn.logicalthinking.common.quartz.ISendNotifyToStudent;
import cn.logicalthinking.common.util.TencentSMS;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@Scope("prototype")
public class SendNotifyToStudentImpl extends ISendNotifyToStudent {

    @Resource
    private MessageStudentDao messageStudentDao;

    @Resource
    private MessageTeacherDao messageTeacherDao;

    @Resource
    private ApplicationParameterDao applicationParameterDao;

    public void send(Map<String, String> param) throws Exception {

        String phone = param.get("phone");
        String courseName = param.get("courseName");
        String courseTime = param.get("courseTime");
        String studentId = param.get("studentId");
        String teacherId = param.get("teacherId");

        // 发短信
        if (StringUtils.isNotBlank(phone)) {
            String[] args = {
                    courseName,
                    courseTime
            };

            TencentSMS.sengSMS(phone, SmsEnum.FOR_REMIND, args);
        }

        // 添加提醒
        String title = MessageTemplate.MESSAGE_NOTIFY_CONTENT.replace("@courseName", courseName).replace("@time", courseTime);
        if (StringUtils.isNotBlank(studentId)) {
            MessageStudent ms = new MessageStudent();
            ms.setStudentId(Integer.parseInt(studentId));
            ms.setTitle(MessageTemplate.MESSAGE_NOTIFY_TITLE);
            ms.setComment(title);
            ms.setStatus(0);
            messageStudentDao.addMessageStudent(ms);
        }
        if (StringUtils.isNotBlank(teacherId)) {
            MessageTeacher mt = new MessageTeacher();
            mt.setTeacherId(Integer.parseInt(teacherId));
            mt.setTitle(MessageTemplate.MESSAGE_NOTIFY_TITLE);
            mt.setComment(title);
            mt.setStatus(0);
            messageTeacherDao.addMessageTeacher(mt);
        }


    }

}
