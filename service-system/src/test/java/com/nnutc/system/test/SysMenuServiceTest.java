package com.nnutc.system.test;

import com.nnutc.system.service.SysMenuService;
import com.nnutc.system.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class SysMenuServiceTest {

    @Autowired
    private SysMenuService sysMenuService;
    @Test
    public void getRolesByUserId() {
        List<String> userPermission = sysMenuService.getUserPermission(String.valueOf(1));
        System.out.println(userPermission);
    }
}
