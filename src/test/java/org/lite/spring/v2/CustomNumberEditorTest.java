package org.lite.spring.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.propertyeditors.CustomNumberEditor;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v1.impl.PetStoreServiceImpl;

/**
 * @author chenjianrong-lhq 2019年03月03日 21:22:05
 * @Description:
 * @ClassName: CustomNumberEditorTest
 */
public class CustomNumberEditorTest {

    @Test
    public void testCustomNumberEditor() {

        CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);

        editor.setAsText("3");

        Object value = editor.getValue();

        Assert.assertTrue(value instanceof Integer);

        Assert.assertEquals(3, ((Integer) value).intValue());

        editor.setAsText("");
        Assert.assertTrue(editor.getValue() == null);

        try {
            editor.setAsText("3.1");

        } catch (Exception e) {
            return;
        }
        Assert.fail();

    }
}
