package org.litespring.beans.factory.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.aop.config.ConfigBeanDefinitionParser;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanDefinitionRegistry;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.context.annotation.ClassPathBeanDefinitionScanner;
import org.litespring.core.io.Resource;
import org.litespring.factory.BeanDefinitionException;
import org.litespring.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author chenjianrong-lhq 2019年02月24日 12:03:11
 * @Description:
 * @ClassName: XmlBeanDefinitionReader
 */
public class XmlBeanDefinitionReader {

    public static final String ID_ATTRiBUTE = "id";

    public static final String CLASS_ATTRiBUTE = "class";

    public static final String SCOPE_ATTRiBUTE = "scope";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";

    public static final String TYPE_ATTRIBUTE = "type";

    public static final String BEANS_NAMESPACE_URI = "http://www.springframework.org/schema/beans";

    public static final String CONTEXT_NAMESPACE_URI = "http://www.springframework.org/schema/context";

    private static final String AOP_NAMESPACE_URI = "http://www.springframework.org/schema/aop";

    private static final String BASE_PACKAGE_ATTRIBUTE = "base-package";


    BeanDefinitionRegistry registry;

    protected final Log logger = LogFactory.getLog(getClass());

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinations(Resource resource) {
        InputStream is = null;
        try {
            is = resource.getInputStream();
            SAXReader reder = new SAXReader();
            Document doc = reder.read(is);

            Element root = doc.getRootElement();
            Iterator<Element> iter = root.elementIterator();
            while (iter.hasNext()) {
                Element ele = iter.next();
                String namespaceUri = ele.getNamespaceURI();
                if (this.isDefaultNamespace(namespaceUri)) {
                    //普通的bean
                    parseDefaultElement(ele);
                } else if (this.isContextNamespace(namespaceUri)) {
                    //例如<context:component-scan>
                    parseComponentElement(ele);
                } else if (this.isAOPNamespace(namespaceUri)) {
                    //例如<aop:config>
                    parseAOPElement(ele);
                }

            }
        } catch (Exception e) {
            throw new BeanDefinitionException("IOException prase XML document!");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void parseAOPElement(Element ele) {
        ConfigBeanDefinitionParser configBeanDefinitionParser = new ConfigBeanDefinitionParser();
        configBeanDefinitionParser.parse(ele, this.registry);
    }


    private void parseComponentElement(Element ele) {
        String basePackages = ele.attributeValue(BASE_PACKAGE_ATTRIBUTE);
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        //Runtime异常在编译时不能捕获
        scanner.doScan(basePackages);

    }

    private void parseDefaultElement(Element ele) {
        String id = ele.attributeValue(ID_ATTRiBUTE);
        String beanClassName = ele.attributeValue(CLASS_ATTRiBUTE);
        BeanDefinition db = new GenericBeanDefinition(id, beanClassName);
        if (ele.attributeValue(SCOPE_ATTRiBUTE) != null) {
            db.setScope(ele.attributeValue(SCOPE_ATTRiBUTE));
        }

        this.parseConstructorArgElements(ele, db);
        this.parsePropertyElement(ele, db);
        this.registry.registerBeanDefinition(id, db);
    }

    public boolean isDefaultNamespace(String namespaceUri) {
        return (!StringUtils.hasLength(namespaceUri) || BEANS_NAMESPACE_URI.equals(namespaceUri));
    }

    public boolean isContextNamespace(String namespaceUri) {
        return (!StringUtils.hasLength(namespaceUri) || CONTEXT_NAMESPACE_URI.equals(namespaceUri));
    }

    private boolean isAOPNamespace(String namespaceUri) {
        return (!StringUtils.hasLength(namespaceUri) || AOP_NAMESPACE_URI.equals(namespaceUri));
    }

    private void parseConstructorArgElements(Element ele, BeanDefinition db) {
        Iterator<Element> iter = ele.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        while (iter.hasNext()) {
            Element propElem = iter.next();
            parseConstructorArgElement(propElem, db);
        }

    }

    private void parseConstructorArgElement(Element propElem, BeanDefinition db) {

        String typeAttr = propElem.attributeValue(TYPE_ATTRIBUTE);
        String nameAttr = propElem.attributeValue(NAME_ATTRIBUTE);

        Object value = this.parsePropertyValue(propElem, db, null);

        ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);

        if (StringUtils.hasLength(typeAttr)) {
            valueHolder.setType(typeAttr);
        }

        if (StringUtils.hasLength(nameAttr)) {
            valueHolder.setName(nameAttr);
        }

        db.getConstructorArgument().addArgumentValue(valueHolder);

    }

    public void parsePropertyElement(Element beanElem, BeanDefinition beanDefinition) {
        System.out.println("org.litespring.beans.factory.xml.XmlBeanDefinitionReader.parsePropertyElement");
        Iterator<Element> iter = beanElem.elementIterator(PROPERTY_ELEMENT);
        while (iter.hasNext()) {
            Element propElem = iter.next();
            String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);

            if (!StringUtils.hasLength(propertyName)) {
                logger.error("Tag 'property' must have a'name' attribute!");
                return;
            }
            Object propertyValue = this.parsePropertyValue(propElem, beanDefinition, propertyName);

            PropertyValue pv = new PropertyValue(propertyName, propertyValue);

            beanDefinition.getPropertyValues().add(pv);
        }
    }

    public Object parsePropertyValue(Element element, BeanDefinition beanDefinition, String property) {
        String elementName = property != null ?
                "<property> element for property '" + property + "'" :
                "<constructor-arg> element";

        boolean hasRefAttribute = (element.attributeValue(REF_ATTRIBUTE) != null);
        boolean hasValueAttribute = (element.attributeValue(VALUE_ATTRIBUTE) != null);

        if (hasRefAttribute) {
            String refName = element.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' atttibute!");
            }
            RuntimeBeanReference ref = new RuntimeBeanReference(element.attributeValue(REF_ATTRIBUTE));
            return ref;
        } else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(element.attributeValue(VALUE_ATTRIBUTE));
            return valueHolder;
        } else {
            throw new RuntimeException(elementName + " must specify a ref or value!");
        }
    }
}
