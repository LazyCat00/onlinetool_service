package com.nnutc.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nnutc.model.entity.SysUser;
import com.nnutc.model.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Param;

/**
* @author admin
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2024-04-22 09:13:11
* @Entity com.nnutc.model.entity.SysUser
*/
public interface SysUserMapper extends BaseMapper<SysUser> {

    IPage<SysUser> selectPage(Page<SysUser> pageParam, @Param("vo") SysUserQueryVo sysUserQueryVo);
}




