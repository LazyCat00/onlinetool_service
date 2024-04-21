package com.nnutc.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.nnutc.model.entity.Users;
import com.nnutc.system.service.UsersService;
import com.nnutc.system.mapper.UsersMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【users】的数据库操作Service实现
* @createDate 2024-04-21 12:29:13
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

}




