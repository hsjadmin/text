package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.LGS;
import cn.logicalthinking.common.entity.Level;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @version 1.0
 * @date 2018-12-19
 */

public interface TeacherDao {

    //新增
    int addTeacher(Teacher teacher) throws DaoException;

    //批量添加
    int batchTeacher(List<Teacher> list) throws DaoException;
    
    //修改
    int updateTeacher(Teacher teacher) throws DaoException;

    //删除
    int removeTeacher(Map<String, Object> map) throws DaoException;

    //查询单个
    Teacher selectTeacherById(Integer id) throws DaoException;

    //条件查询全部
    List<Teacher> selectTeacherListAll(Map<String, Object> map) throws DaoException;

    //分页查询
    PageInfo<Teacher> selectTeacherListByPage(Map<String, Object> map) throws DaoException;

    //查询总数
    int selectTeacherCount(Map<String, Object> map) throws DaoException;


    /*********************************************自定义扩展sql***********************************************/
    //查询当前手机是否注册过
    public Teacher isRegister(String phone) throws DaoException;

    //查询教师我的主页信息
    public Teacher getTeacherInfo(Integer id) throws DaoException;

    public PageInfo<Teacher> selectTeacherListByPageOrderByFully(Map<String, Object> map) throws DaoException;

    public PageInfo<Teacher> selectTeacherListByPageOrderByStar(Map<String, Object> map) throws DaoException;

    //查询教师余额
    public Teacher getTeacherBalance(Integer id) throws DaoException;

    //提现
    public int updateBalance(Map<String, Object> map) throws DaoException;

    //收入
    public int addBalance(Map<String, Object> map) throws DaoException;

    //查询级别年纪科目
    List<LGS> selectLGS() throws DaoException;

    //查询级别年纪科目
    List<Level> selectLevel() throws DaoException;


    //修改余额
    public int updateBalanceSafeLy(Map<String, Object> map) throws DaoException;

    // 根据老师手机号修改答疑状态
    int updateOnlineStatusByPhone(Map<String, Object> map) throws DaoException;
}