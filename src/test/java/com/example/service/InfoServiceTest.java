package com.example.service;

import com.vividsolutions.jts.util.Assert;
import org.elasticsearch.action.main.MainResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InfoServiceTest.class)
@ComponentScan(basePackages = "com.example")
public class InfoServiceTest {

    @Autowired
    InfoService infoService;

    @Test
    public void getClusterInfoTest() throws Exception{
        MainResponse mainResponse = infoService.getClusterInfo();
        Assert.equals("my-application", mainResponse.getClusterName().value());
    }

}
