package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.FileItem;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MarketingCourseDao;
import cn.logicalthinking.common.entity.MarketingCourse;
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
 * @Description  营销素材-课程 添加
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class AddMarketingCourseService extends AbstractService{
	
	@Resource
	private MarketingCourseDao marketingCourseDao;
	
	private IClientData data;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;

		SysUser user = data.getUser();

		String fileInfo = data.getParameter("fileInfo");

		FileItem fileItem = UploadFileUtil.getFileItem(fileInfo);

		MarketingCourse marketingCourse=(MarketingCourse)data.getObject();

        String content = URLDecoder.decode(URLDecoder.decode(marketingCourse.getContent(), "UTF-8"), "UTF-8");
		marketingCourse.setContent(content);

		//源文件路径
		String srcFileName= UploadFileUtil.destFile+fileItem.getFilePath();
		//新文件路径
		String newPath=UploadFileUtil.FilePath+ Tools.UUID()+UploadFileUtil.suFileName(fileItem.getFilePath());
		//复制文件
		FileUtil.copyFile(srcFileName,UploadFileUtil.destFile+newPath,true);



		marketingCourse.setUserName(user.getTrueName());

		marketingCourse.setCoverMap(newPath);

		marketingCourse.setCreateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
		
		marketingCourseDao.addMarketingCourse(marketingCourse);
		
		return  Result.jsonMsg(CODE.CODE_200_ADD.getKey(),CODE.CODE_200_ADD.getValue());
		
	}
	
	

}
