package org.litespring.core.io;

import org.litespring.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author chenjianrong-lhq 2019年02月24日 20:26:40
 * @Description:
 * @ClassName: ClassPathResource
 */
public class ClassPathResource implements Resource {
    private String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path,null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    public InputStream getInputStream() throws IOException {
        InputStream is=this.classLoader.getResourceAsStream(this.path);

        if(is==null){
            throw new FileNotFoundException(this.path+" cannot be opened!");
        }
        return is;
    }

    public String getDescription() {
        return this.path;
    }
}
