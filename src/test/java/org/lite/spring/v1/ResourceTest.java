package org.lite.spring.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

import java.io.InputStream;

/**
 * @author chenjianrong-lhq 2019年02月24日 20:19:40
 * @Description:
 * @ClassName: ResourceTest
 */
public class ResourceTest {
    @Test
    public  void testClassPathResource() throws Exception{
        Resource resource=new ClassPathResource("petstore-v1.xml");
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
        Resource resource=new FileSystemResource("src/test/resource/petstore-v1.xml");
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
