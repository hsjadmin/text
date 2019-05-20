package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.FileItem;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MarketingBannerDao;
import cn.logicalthinking.common.entity.MarketingBanner;
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
 * @Description 营销素材-学生端首页 添加
 * @date 2018-12-19
 */
@Service
public class AddMarketingBannerService extends AbstractService {

    @Resource
    private MarketingBannerDao marketingBannerDao;

    private IClientData data;


    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        SysUser adminUser = data.getUser();

        String fileInfo = data.getParameter("fileInfo");

        FileItem fileItem = UploadFileUtil.getFileItem(fileInfo);

        MarketingBanner marketingBanner = (MarketingBanner) data.getObject();

        String content = URLDecoder.decode(URLDecoder.decode(marketingBanner.getContent(), "UTF-8"), "UTF-8");
        URLDecoder.decode(content, "UTF-8");
        marketingBanner.setContent(content);


        // 1 视频
        //源文件路径
        String srcFileName = UploadFileUtil.destFile + fileItem.getFilePath();
        //新文件路径
        String newPath = UploadFileUtil.FilePath + Tools.UUID() + UploadFileUtil.suFileName(fileItem.getFilePath());
        //复制文件
        FileUtil.copyFile(srcFileName, UploadFileUtil.destFile + newPath, true);

        marketingBanner.setUserName(adminUser.getTrueName());

        if (marketingBanner.getMaterialType() == 0)
            marketingBanner.setCoverMap(newPath);
        else {
            marketingBanner.setDatum(newPath);

            String coverMap = data.getParameter("coverMap");
            // 设置默认图片
            FileItem coverMapItem = UploadFileUtil.getFileItem(coverMap);
            if (coverMapItem != null) {
                // 2 图片
                //源文件路径
                String srcFileName2 = UploadFileUtil.destFile + coverMapItem.getFilePath();
                //新文件路径
                String newPath2 = UploadFileUtil.FilePath + Tools.UUID() + UploadFileUtil.suFileName(coverMapItem.getFilePath());
                //复制文件
                FileUtil.copyFile(srcFileName2, UploadFileUtil.destFile + newPath2, true);
                marketingBanner.setCoverMap(newPath2);
            } else {
                marketingBanner.setCoverMap(UploadFileUtil.DEFAULT_COVER);
            }

        }
        marketingBanner.setNumber(0);

        marketingBanner.setCreateTime(DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME));

        marketingBannerDao.addMarketingBanner(marketingBanner);

        return Result.jsonMsg(CODE.CODE_200_ADD.getKey(), CODE.CODE_200_ADD.getValue());
    }

}
