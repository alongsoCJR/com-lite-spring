package com.focus.spring.v1;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;

/**
 * @Author chenjianrong-lhq
 * @Description ClassPathResource和FileSystemResource测试类，加载xml文件资源，转化成字节流
 * @Date 2019-06-01 11:59
 **/
public class ResourceTest {

    @Test
    public  void testClassPathResource() throws Exception{
        Resource resource=new ClassPathResource("spring-config-v1.xml");
        InputStream is=null;
        try{
            is=resource.getInputStream();
            Assert.assertNotNull(is);
        }finally {
            if(is!=null){
                is.close();
            }
        }
    }

    @Test
    public  void testFileSystemResource() throws Exception{
        Resource resource=new FileSystemResource("src/main/resources/spring-config-v1.xml");
        InputStream is=null;
        try{
            is=resource.getInputStream();
            Assert.assertNotNull(is);
        }finally {
            if(is!=null){
                is.close();
            }
        }
    }
}
