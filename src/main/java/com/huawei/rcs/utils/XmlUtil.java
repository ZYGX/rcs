package com.huawei.rcs.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import java.io.StringReader;
import java.io.StringWriter;

@Slf4j
public class XmlUtil {

    private static final String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    /**
     * jaxb将对象转成xml字符
     *
     * @param obj java对象
     * @return
     */
    public static String toXml(Object obj) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller m = context.createMarshaller();
            // 不进行转义字符的处理
            m.setProperty(CharacterEscapeHandler.class.getName(), (CharacterEscapeHandler) (ch, start, length, isAttVal, writer) -> writer.write(ch, start, length));

            //去掉standalone="yes"
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            m.marshal(obj, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            log.error("JAXBException", e);
            return null;
        }

        return xmlHeader + xmlString;
    }

    /**
     * jaxb将对象转成xml字符
     *
     * @param obj               java对象
     * @param ignoreEmptyString 是否忽略空字符串
     * @return
     */
    public static String toXml(Object obj, boolean ignoreEmptyString) {
        if (ignoreEmptyString) {
            String objStr = JSON.toJSONString(obj, (ValueFilter) (object, name, value) -> {
                if ("".equals(value)) {
                    return null;
                }
                return value;
            });
            return toXml(JSON.parseObject(objStr, obj.getClass()));
        } else {
            return toXml(obj);
        }
    }

    /**
     * jaxb 将xml字符串转成java对象
     *
     * @param xmlStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBean(String xmlStr, Class clazz) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            T t = (T) unmarshaller.unmarshal(new StringReader(xmlStr.trim()));
            return t;
        } catch (JAXBException e) {
            log.error("JAXBException", e);
        }
        return null;
    }


    /**
     * xstream将xml字符串转成java对象
     *
     * @param xmlStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertToBean(String xmlStr, Class clazz) {
        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(clazz);
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[]{clazz});
        return (T) xstream.fromXML(xmlStr.trim());
    }

    /**
     * xstream将java对象转成xml字符串
     *
     * @param obj
     * @return
     */
    public static String convertToXml(Object obj) {
        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(obj.getClass());
        return xstream.toXML(obj);
    }

    /**
     * xstream将java对象转成xml字符串
     *
     * @param obj
     * @return
     */
    public static String convertToXml(Object obj,String localPart,String qname) {

        StaxDriver drive = new StaxDriver();
        QNameMap qnameMap = new QNameMap();
        QName qName = new QName(qname, localPart,"msg");
        qnameMap.registerMapping(qName, obj.getClass());
        drive.setQnameMap(qnameMap);
        XStream xstream = new XStream(drive);

        xstream.processAnnotations(obj.getClass());
        return xmlHeader+xstream.toXML(obj).substring(22);
    }

}
