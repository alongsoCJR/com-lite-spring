package org.lite.spring.v4;
import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.annotation.AnnotationAttributes;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.core.type.classreding.MetadataReader;
import org.litespring.core.type.classreding.SimpleMetadataReader;
import org.litespring.stereotype.Component;

import java.io.IOException;

/**
 * fourth Test
 * SimpleMetadataReader对Third Test中两个vistor的封装，不再将AnnotationMetadataReadingVisitor暴露给客户
 * 同时，代码也抽取了classMetadata和AnnotationMetadata公共部分，对外面而言，如果是一个带有注释的类，
 * 提供AnnotationMetadata信息，就已经足够了。
 */
public class MetaDataReaderTest {

    @Test
    public void testGetMetadata() throws IOException {
        ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/impl/PetStoreServiceImpl.class");

        MetadataReader reader = new SimpleMetadataReader(resource);
        //注意：不需要单独使用ClassMetadata
        //ClassMetadata clzMetadata = reader.getClassMetadata();
        AnnotationMetadata amd = reader.getAnnotationMetadata();

        String annotation = Component.class.getName();

        Assert.assertTrue(amd.hasAnnotation(annotation));
        AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
        Assert.assertEquals("petStore", attributes.get("value"));

        Assert.assertFalse(amd.isAbstract());
        Assert.assertFalse(amd.isFinal());
        Assert.assertEquals("org.litespring.service.v4.impl.PetStoreServiceImpl", amd.getClassName());
        Assert.assertTrue(amd.hasSuperClass());
        Assert.assertEquals("java.lang.Object", amd.getSuperClassName());
        Assert.assertEquals(1, amd.getInterfaceNames().length);
    }
}
