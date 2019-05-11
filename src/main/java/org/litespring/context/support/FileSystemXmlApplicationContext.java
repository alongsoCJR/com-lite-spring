package org.litespring.context.support;

import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

/**
 * @author chenjianrong-lhq 2019年02月25日 21:22:35
 * @Description:
 * @ClassName: FileSystemXmlApplicationContext
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {


    public FileSystemXmlApplicationContext(String path) {
        super(path);
    }

    public FileSystemXmlApplicationContext(String path,ClassLoader classLoader) {
        super(path,classLoader);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }


}
