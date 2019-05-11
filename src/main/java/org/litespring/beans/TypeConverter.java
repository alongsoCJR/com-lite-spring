package org.litespring.beans;

import org.litespring.factory.TypeMismatchException;

/**
 * @author chenjianrong-lhq 2019年03月03日 22:26:03
 * @Description:
 * @ClassName: TypeConverter
 */

public interface TypeConverter {


    <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;


}
