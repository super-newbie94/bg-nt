package com.bgnt.sys.dao.mapper.interfaces;

import com.bgnt.sys.dao.bo.SysUser;
import com.bgnt.sys.dao.bo.SysUserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    int countByExample(SysUserCriteria example);

    int deleteByExample(SysUserCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserCriteria example);

    SysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserCriteria example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserCriteria example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}