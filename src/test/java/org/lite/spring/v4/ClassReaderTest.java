package org.lite.spring.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.annotation.AnnotationAttributes;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.type.classreding.AnnotationMetadataReadingVisitor;
import org.litespring.core.type.classreding.ClassMetadataReadingVisitor;
import org.springframework.asm.ClassReader;

import java.io.IOException;

/**
 * third Test
 * 实现两个Visitor,根据second Test获取到的Resource,读取Class文件信息
 * 如：isAbstract(),isInterface(),isFinal(),getSuperClassName(),getClassName(),getInterfaceNames().length
 * 注意：测试类的主要目的不在上面的信息，而在于注释的信息，文件中是否包含Component注释
 * 如果包含，则去读取注释的信息，将Component注释的信息读取出来，
 * 我们需要这个信息(这里指的就是value的值)
 * 另外：这里使用了ASM框架--Java 字节码操控框架
 */
public class ClassReaderTest {

    @Test
    public void testGetClassMetaData() throws IOException {
        ClassPathResource resource = new ClassPathResource("org/litespring/service/v1/impl/PetStoreServiceImpl.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();

        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        Assert.assertFalse(visitor.isAbstract());
        Assert.assertFalse(visitor.isInterface());
        Assert.assertFalse(visitor.isFinal());
        Assert.assertEquals("org.litespring.service.v1.impl.PetStoreServiceImpl", visitor.getClassName());
        Assert.assertEquals("java.lang.Object", visitor.getSuperClassName());
        Assert.assertEquals(1, visitor.getInterfaceNames().length);
    }

    @Test
    public void testGetAnnonation() throws Exception{
        ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/impl/PetStoreServiceImpl.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();

        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        String annotation = "org.litespring.stereotype.Component";
        Assert.assertTrue(visitor.hasAnnotation(annotation));

        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);

        Assert.assertEquals("petStore", attributes.get("value"));

    }
}
