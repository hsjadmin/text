package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.CourseComment;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface CourseCommentDao {

	//新增
	public int addCourseComment(CourseComment courseComment) throws DaoException;
	
	//批量添加
	public int batchCourseComment(List<CourseComment> list) throws DaoException;

	//修改
	public int updateCourseComment(CourseComment courseComment) throws DaoException;

	//删除
	public int removeCourseComment(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public CourseComment selectCourseCommentById(String id) throws DaoException;

	//条件查询全部
	public List<CourseComment> selectCourseCommentListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<CourseComment> selectCourseCommentListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectCourseCommentCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/

	//分页查询
	public PageInfo<CourseComment> selectCourseCommentByPage(Map<String, Object> map) throws DaoException;
}