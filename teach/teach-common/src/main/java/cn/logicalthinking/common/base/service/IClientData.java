package cn.logicalthinking.common.base.service;

import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.entity.SysUser;
import cn.logicalthinking.common.entity.Teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description 封装好的传输包
 * @author 黄世杰
 * @date   2018-12-19
 * @version  1.0
 */
public interface IClientData {
	/**
	 * @Description  获取到一个传输对象Request
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getRequest();
	
	public HttpServletResponse getResponse();
	
	
	/**
	 * @Description 从request获取参数  根据key获取值
	 * @version  1.0
	 * @param key
	 * @return  返回key对应的值
	 */
	public String getParameter(String key);
	/**
	 * @Description  获取客户端的ip
	 * @version  1.0
	 * @return
	 */
	public abstract String getIpAddr();
	
	/**
	 * @Description  获取接收的单个对象
	 * @version  1.0
	 * @return
	 */
	public Object getObject();
	
	public SysUser getUser();

	/**
	 * @Description  获取接收多个个对象
	 * @version  1.0
	 * @return
	 */
	public Map<String,Object> getListObject();
	
	
	public Map<String,Object> initMap();
	
	public void setConditionMap(Map<String, Object> map, String[] conditionArr);



	/**
	 * @Description 获取到患者对象
	 * @author 黄世杰
	 * @下午5:35:16
	 * @version  1.0
	 * @return
	 */
	public Student getStudentUser();

	public Student getStudentUsers();

	/**
	 *
	 * @Description 获取到医护人员对象
	 * @author 黄世杰
	 * @下午5:36:04
	 * @version  1.0
	 * @return
	 */
	public Teacher getTeacherUser();

	public String getTeacherUserId();

}
