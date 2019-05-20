package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.FileItem;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MarketingQuestionDao;
import cn.logicalthinking.common.entity.MarketingQuestion;
import cn.logicalthinking.common.entity.SysUser;
import cn.logicalthinking.common.util.DateUtil;
import cn.logicalthinking.common.util.FileUtil;
import cn.logicalthinking.common.util.Tools;
import cn.logicalthinking.common.util.UploadFileUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.Date;

/**
 * @author 卢祖飞
 * @version 1.0
 * @Description 营销素材-难题 添加
 * @date 2018-12-19
 */
@Service
public class AddMarketingQuestionService extends AbstractService {

    @Resource
    private MarketingQuestionDao marketingQuestionDao;

    private IClientData data;


    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        SysUser user = data.getUser();

        String fileInfo = data.getParameter("fileInfo");

        FileItem fileItem = UploadFileUtil.getFileItem(fileInfo);


        //源文件路径
        String srcFileName = UploadFileUtil.destFile + fileItem.getFilePath();
        //新文件路径
        String newPath = UploadFileUtil.FilePath + Tools.UUID() + UploadFileUtil.suFileName(fileItem.getFilePath());
        //复制文件
        FileUtil.copyFile(srcFileName, UploadFileUtil.destFile + newPath, true);


        MarketingQuestion marketingQuestion = (MarketingQuestion) data.getObject();
        String content = URLDecoder.decode(URLDecoder.decode(marketingQuestion.getContent(), "UTF-8"), "UTF-8");
        marketingQuestion.setContent(content);

        marketingQuestion.setUserName(user.getTrueName());

        marketingQuestion.setCreateTime(DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME));

        marketingQuestion.setCoverMap(newPath);

        marketingQuestionDao.addMarketingQuestion(marketingQuestion);

        return Result.jsonMsg(CODE.CODE_200_ADD.getKey(), CODE.CODE_200_ADD.getValue());

    }


}
