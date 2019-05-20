package cn.logicalthinking.common.quartz;

import cn.logicalthinking.common.util.FileUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;

public class JobCleanFile implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

//        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
//        String path = jobDataMap.getString("path");
//        File dir = new File(path);
//        if (dir.isDirectory()) {
//            FileUtil.deleteDir(dir);
//        }

    }
}
