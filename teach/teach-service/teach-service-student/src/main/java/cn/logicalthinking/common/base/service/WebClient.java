package cn.logicalthinking.common.base.service;


import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.dao.SysUserDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.entity.SysUser;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.PermissionException;
import cn.logicalthinking.common.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 黄世杰
 * @version 1.0
 * @Description webClient
 * @date 2018-09-26
 */
@Component
public class WebClient implements IClientData {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private Object object;

    private Map<String, Object> mapObj;

    private MultipartFile file;

    private static SysUserDao sysUserDao;

    private static StudentDao studentDao;

    private static TeacherDao teacherDao;

    public WebClient() {
    }

    public WebClient(HttpServletRequest request) {
        this.request = request;
    }

    public WebClient(HttpServletRequest request, Object object) {
        this.request = request;
        this.object = object;
    }

    public WebClient(HttpServletRequest request, Map<String, Object> mapObj) {
        this.request = request;
        this.mapObj = mapObj;
    }

    public WebClient(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public WebClient(HttpServletRequest request, MultipartFile file) {
        this.request = request;
        this.file = file;
    }

    public WebClient(HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
        this.request = request;
        this.response = response;
        this.file = file;
    }

    public WebClient(HttpServletRequest request, Object object, MultipartFile file) {
        this.request = request;
        this.object = object;
        this.file = file;
    }

    public String getIpAddr() {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public Map<String, Object> initMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        String page = request.getParameter("pageIndex");
        if (StringUtils.isNotBlank(page)) {
            map.put("pageNum", Integer.parseInt(page));
        } else {
            map.put("pageNum", 0);
        }

        String rows = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(rows)) {
            map.put("pageSize", Integer.parseInt(rows));
        } else {
            map.put("pageSize", 0);
        }

        String sort = request.getParameter("sort");
        String column = request.getParameter("column");
        if (StringUtils.isNotBlank(column) && StringUtils.isNotBlank(sort)) {
            map.put("orderBy", column + " " + sort);
        }
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        if (StringUtils.isNotBlank(startTime)) {
            map.put("startTime", startTime + " 00:00:00");
        }
        if (StringUtils.isNotBlank(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        return map;
    }


    public void setConditionMap(Map<String, Object> map, String[] conditionArr) {
        for (int i = 0; i < conditionArr.length; i++) {
            String value = request.getParameter(conditionArr[i]);
            if (StringUtils.isNotBlank(value)) {
                map.put(conditionArr[i], value);
            }
        }
    }


    @Override
    public HttpServletRequest getRequest() {
        return request;
    }


    public MultipartFile getFile() {
        return file;
    }

    @Override
    public String getParameter(String key) {
        return request.getParameter(key);
    }

    @Override
    public Object getObject() {
        return object;
    }

    /**
     * 获取后台用户
     */
    @Override
    public SysUser getUser() {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token))
            throw new PermissionException("登录超时，请重新登录");
        Claims claims = JwtUtils.parseJWT(token);
        if (claims == null)
            throw new PermissionException("登录超时，请重新登录");
        String userId = claims.getId();
        SysUser sysUser = sysUserDao.selectSysUserById(userId);
        if (sysUser == null)
            throw new PermissionException("该用户不存在");

        return sysUser;
    }

    @Override
    public HttpServletResponse getResponse() {
        return response;
    }

    public Map<String, Object> getListObject() {
        return mapObj;
    }

    @Resource
    public void setSysUserDao(SysUserDao sysUserDao) {
        WebClient.sysUserDao = sysUserDao;
    }

    @Resource
    public void setStudentDao(StudentDao studentDao) {
        WebClient.studentDao = studentDao;
    }

    @Resource
    public void setTeacher(TeacherDao teacherDao) {
        WebClient.teacherDao = teacherDao;
    }


    /**
     * 学生
     */
    @Override
    public Student getStudentUser() {
        String token = request.getHeader("token");

        if (StringUtils.isBlank(token))
            throw new PermissionException("登录超时，请重新登录");

        Student student = studentDao.selectStudentByOpenId(token);
        if (student == null)
            throw new PermissionException("该用户不存在");
        return student;
    }

    @Override
    public Student getStudentUsers() {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token))
           return null;
        Student student = studentDao.selectStudentByOpenId(token);
        if (student == null)
            return null;
        return student;
    }

    /**
     * 老师
     */
    @Override
    public Teacher getTeacherUser() {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token))
            throw new PermissionException("登录超时，请重新登录");
        Claims claims = JwtUtils.parseJWT(token);
        if (claims == null)
            throw new PermissionException("登录超时，请重新登录");
        String userId = claims.getId();
        Teacher clinicUser = teacherDao.selectTeacherById(Integer.parseInt(userId));
        if (clinicUser == null)
            throw new PermissionException("该用户不存在");
        return clinicUser;
    }

    @Override
    public String getTeacherUserId() {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token))
            throw new PermissionException("登录超时，请重新登录");
        Claims claims = JwtUtils.parseJWT(token);
        if (claims == null)
            throw new PermissionException("登录超时，请重新登录");
        String userId = claims.getId();
        return userId;
    }
}
