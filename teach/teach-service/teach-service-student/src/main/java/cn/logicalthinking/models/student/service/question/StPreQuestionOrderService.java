package cn.logicalthinking.models.student.service.question;

import cn.logicalthinking.common.base.entity.FileItem;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.enums.OrderQuestionStatus;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderQuestionDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.OrderQuestion;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.FileUtil;
import cn.logicalthinking.common.util.ParamValidation;
import cn.logicalthinking.common.util.Tools;
import cn.logicalthinking.common.util.UploadFileUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author xhx
 * @version 1.0
 * @Description 创建答疑订单
 * @date 2019-1-2
 */
@Service
public class StPreQuestionOrderService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private OrderQuestionDao orderQuestionDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Student studentUser = data.getStudentUser();

        String teacherId = data.getParameter("teacherId");
        String fileInfo = data.getParameter("fileInfo");

        ParamValidation.isNotNull(teacherId, "缺失老师id");
        ParamValidation.isNotNull(fileInfo, "请上传图片");

        Teacher teacher = teacherDao.selectTeacherById(Integer.parseInt(teacherId));

        if (teacher == null) {
            throw new BusinessException("老师数据异常,请重试");
        }
        if (teacher.getStartingPrice() == null) {
            teacher.setStartingPrice(new BigDecimal("0"));
//            throw new BusinessException("未设置起步价");
        }
        if (teacher.getChargeSettings() == null) {
            teacher.setChargeSettings(new BigDecimal("0"));
//            throw new BusinessException("未设置超时收费价");
        }

        String newPath = null;
        try {
            newPath = getImg(fileInfo);
        } catch (Exception e) {
            throw new BusinessException("图片上传失败，请重新选择");
        }

        OrderQuestion orderQuestion = preOrder(studentUser, teacher, newPath);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), orderQuestion);
    }

    private OrderQuestion preOrder(Student studentUser, Teacher teacher, String newPath) {
        OrderQuestion orderQuestion = new OrderQuestion();
        orderQuestion.setStudentId(studentUser.getId());
        orderQuestion.setStudentName(studentUser.getName());
        orderQuestion.setGrade(studentUser.getGrade());
        orderQuestion.setTeacherId(teacher.getId());
        orderQuestion.setTeacherName(teacher.getName());
        orderQuestion.setQuestionImg(newPath);

        // 等待解答
        orderQuestion.setStatus(OrderQuestionStatus.CHECKING.getKey());

        orderQuestion.setPrice(teacher.getStartingPrice());

        if (orderQuestionDao.addOrderQuestion(orderQuestion) == 0) {
            throw new BusinessException("上传失败");
        }
        return orderQuestion;
    }

    private String getImg(String fileInfo) {
        FileItem fileItem = UploadFileUtil.getFileItem(fileInfo);
        //源文件路径
        String srcFileName = UploadFileUtil.destFile + fileItem.getFilePath();
        //新文件路径
        String newPath = UploadFileUtil.FilePath + Tools.UUID() + UploadFileUtil.suFileName(fileItem.getFilePath());
        //复制文件
        FileUtil.copyFile(srcFileName, UploadFileUtil.destFile + newPath, true);
        return newPath;
    }

}
