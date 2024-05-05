package com.nnutc.system.test;

import com.nnutc.system.service.SysRoleService;
import com.nnutc.system.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class SysUserServiceTest {
    @Autowired
    private SysUserService sysUserService;
    @Test
    public void getRolesByUserId() {

    }
}
