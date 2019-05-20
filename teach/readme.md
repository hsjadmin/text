- 项目结构
    - teach
        - teach-common
        - teach-dao
        - teach-file
        - teach-pojo
        - teach-service
            - teach-service-manager
            - teach-service-student
            - teach-service-teacher
        - teach-web
            - teach-web-manager
            - teach-web-student
            - teach-web-teacher
            
- 部署需要更改的配置

1.  teach-common》resources》config》  
    db.properties:数据库连接配置信息  
    file-path:文件上传保存信息，及定时清理cron  
2.  teach-common》resources》  
    quartz.properties: 定时任务配置，持久化配置
3.  teach-web-manager》webapp》conf》  
    config.json：富文本上传配置，访问api地址及保存路径
 
 
 
-   打包部署    
http://note.youdao.com/noteshare?id=b6baf605521addb81e138837c51bd623&sub=EE96069BD2634F93915CFE6911C086BF



-   功能说明

1.  分享功能说明：
    教师分享课程给学生，学生直接通过小程序二维码进入可以查看课程详情，通过传入的courseId，
    生成key，学生打开学生端并携带key，即可查看被隐藏的课程，学生端拦截器进行过滤。