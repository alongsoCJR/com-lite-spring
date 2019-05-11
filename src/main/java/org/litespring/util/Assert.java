package org.litespring.util;

/**
 * @author chenjianrong-lhq 2019年02月24日 20:45:43
 * @Description:
 * @ClassName: Assert
 */
public class Assert {
    public static void notNull(Object obj, String message) {
        if(obj==null){
            throw new IllegalArgumentException(message);
        }
    }
}
