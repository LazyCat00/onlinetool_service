package com.nnutc.system.test;

import com.nnutc.system.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class SysRoleServiceTest {
    @Autowired
    private SysRoleService sysRoleService;
    @Test
    public void getRolesByUserId() {
        Map<String, Object> roles = sysRoleService.getRolesByUserId(String.valueOf(1));
        System.out.println(roles);
    }
}
