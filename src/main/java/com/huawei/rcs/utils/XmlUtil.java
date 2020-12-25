package com.huawei.rcs.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import lombok.extern.slf4j.Slf4j;

import javax.xml.namespace.QName;

@Slf4j
public class XmlUtil {

    private static final String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

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
