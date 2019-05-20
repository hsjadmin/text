package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.CourseOutline;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface CourseOutlineDao {

	//新增
	public int addCourseOutline(CourseOutline courseOutline) throws DaoException;
	
	//批量添加
	public int batchCourseOutline(List<CourseOutline> list) throws DaoException;

	//修改
	public int updateCourseOutline(CourseOutline courseOutline) throws DaoException;

	//删除
	public int removeCourseOutline(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public CourseOutline selectCourseOutlineById(Integer id) throws DaoException;

	//条件查询全部
	public List<CourseOutline> selectCourseOutlineListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<CourseOutline> selectCourseOutlineListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectCourseOutlineCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
	public PageInfo<CourseOutline> selectCourseOutlineBycId(Map<String, Object> map) throws DaoException;

	public List<CourseOutline> getOutLine(Map<String, Object> map) throws DaoException;

	//根据频道id修改直播视频路径
	public int updateUrl(Map<String, Object> map) throws DaoException;


	int deleteCourseOutlineByctId(String ctId) throws DaoException;

	public CourseOutline selectCourseOutlineBychannelNo(String channelNo) throws DaoException;
}