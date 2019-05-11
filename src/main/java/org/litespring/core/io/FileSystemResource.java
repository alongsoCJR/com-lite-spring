package org.litespring.core.io;

import org.litespring.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author chenjianrong-lhq 2019年02月24日 20:32:17
 * @Description:
 * @ClassName: FileSystemResource
 */
public class FileSystemResource implements Resource {
    private String path;
    private File file;

    public FileSystemResource(String path) {
        Assert.notNull(path,"Path must not null!");
        this.path=path;
        this.file=new File(path);
    }

    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public String getDescription() {
        return this.path;
    }
}
