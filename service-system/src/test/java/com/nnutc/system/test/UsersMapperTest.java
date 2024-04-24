package com.nnutc.system.test;

import com.nnutc.system.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UsersMapperTest {
    @Autowired
    private UsersMapper usersMapper;

    //1 查询表所有记录
    @Test
    public void findAll() {
        List<Users> list = usersMapper.selectList(null);
        for (Users sysRole:list) {
            System.out.println(sysRole);
        }
    }
}
