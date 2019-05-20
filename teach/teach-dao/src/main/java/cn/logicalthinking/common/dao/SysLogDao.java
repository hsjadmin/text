package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.SysLog;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-09-28
 * @version  1.0
 */

public interface SysLogDao {

	//新增
	public int addSysLog(SysLog sysLog) throws DaoException;
	
	//批量添加
	public int batchSysLog(List<SysLog> list) throws DaoException;

	//修改
	public int updateSysLog(SysLog sysLog) throws DaoException;

	//删除
	public int removeSysLog(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public SysLog selectSysLogById(String id) throws DaoException;

	//条件查询全部
	public List<SysLog> selectSysLogListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<SysLog> selectSysLogListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectSysLogCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
	//分页查询
	public PageInfo<SysLog> selectSysLogListByPageCus(Map<String, Object> map) throws DaoException;
}