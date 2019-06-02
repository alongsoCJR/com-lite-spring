package org.lite.spring.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.Resource;
import org.litespring.core.io.support.PackageResourceLoader;

import java.io.IOException;

/**
 * second Test
 * 根据包名找到包下所有的字节码文件，以FileSystemResource数组的形式返回
 */
public class PackageResourceLoaderTest {

    @Test
    public void testGetResourceLoader() throws IOException {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("org.litespring.dao.v4");
        Assert.assertEquals(2, resources.length);
    }
}
