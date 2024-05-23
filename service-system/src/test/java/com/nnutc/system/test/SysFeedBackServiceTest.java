package com.nnutc.system.test;


import com.nnutc.model.entity.SysFeedback;
import com.nnutc.system.mapper.SysFeedbackMapper;
import com.nnutc.system.service.SysFeedbackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysFeedBackServiceTest {

    @Autowired
    private SysFeedbackMapper sysFeedbackMapper;
    @Test
    public void getFeedbackIsShow() {
        List<SysFeedback> sysFeedbacks = sysFeedbackMapper.selectList(null);
        System.out.println(sysFeedbacks);

    }
}
