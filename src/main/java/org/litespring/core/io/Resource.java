package org.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author chenjianrong-lhq 2019年02月24日 20:26:12
 * @Description:
 * @ClassName: Resource
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

    String getDescription();
}
