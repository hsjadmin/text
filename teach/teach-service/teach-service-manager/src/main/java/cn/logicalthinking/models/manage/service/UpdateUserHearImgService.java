package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysUserDao;
import cn.logicalthinking.common.entity.SysUser;
import cn.logicalthinking.common.util.FileUtil;
import cn.logicalthinking.common.util.Tools;
import cn.logicalthinking.common.util.UploadFileUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2019/1/12,10:06
 */
@Service
public class UpdateUserHearImgService extends AbstractService {

    @Resource
    private SysUserDao sysUserDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        SysUser user = data.getUser();

        String picturePath = data.getParameter("picturePath");

        String newPath = "";

        if(picturePath != null){
            //源文件路径
            String srcFileName = UploadFileUtil.destFile + picturePath;
            //新文件路径
            newPath = UploadFileUtil.FilePath + Tools.UUID() + UploadFileUtil.suFileName(picturePath);
            //复制文件
            FileUtil.copyFile(srcFileName, UploadFileUtil.destFile + newPath, true);
        }

        user.setPicturePath(newPath);

        sysUserDao.updateSysUser(user);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
    }
}
