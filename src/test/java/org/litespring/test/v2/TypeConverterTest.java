package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.TypeConverter;
import org.litespring.beans.factory.support.SimpleTypeConverter;
import org.litespring.factory.TypeMismatchException;

/**
 * @author chenjianrong-lhq 2019年03月03日 22:18:30
 * @Description:
 * @ClassName: TypeConverterTest
 */
public class TypeConverterTest {

   @Test
    public void test(){
       TypeConverter typeConverter=new SimpleTypeConverter();
       try {
           Integer i=typeConverter.convertIfNecessary("3",Integer.class);
           Assert.assertEquals(3,i.intValue());

       } catch (TypeMismatchException e) {
           e.printStackTrace();
       }


       try {
           Integer i=typeConverter.convertIfNecessary("3.1",Integer.class);

       } catch (TypeMismatchException e) {
            return;
       }

       Assert.fail();
   }
}
