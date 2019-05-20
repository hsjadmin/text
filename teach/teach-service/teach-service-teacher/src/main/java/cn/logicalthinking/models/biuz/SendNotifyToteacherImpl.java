package cn.logicalthinking.models.biuz;

import cn.logicalthinking.common.base.constant.MessageTemplate;
import cn.logicalthinking.common.base.enums.SmsEnum;
import cn.logicalthinking.common.dao.MessageTeacherDao;
import cn.logicalthinking.common.entity.MessageTeacher;
import cn.logicalthinking.common.quartz.ISendNotifyToTeacher;
import cn.logicalthinking.common.util.TencentSMS;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@Scope("prototype")
public class SendNotifyToteacherImpl extends ISendNotifyToTeacher {

    @Resource
    private MessageTeacherDao messageTeacherDao;

    @Resource

    public void send(Map<String, String> param) throws Exception {

        String phone = param.get("phone");
        String courseName = param.get("courseName");
        String courseTime = param.get("courseTime");
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
        MessageTeacher mt = new MessageTeacher();
        mt.setTeacherId(Integer.parseInt(teacherId));
        mt.setTitle(MessageTemplate.MESSAGE_NOTIFY_TITLE);
        mt.setComment(title);
        mt.setStatus(0);
        messageTeacherDao.addMessageTeacher(mt);


    }

}
