package traceback.common;


import java.io.ByteArrayInputStream;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMXMLBuilderFactory;
import org.apache.axiom.om.OMXMLParserWrapper;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.databinding.utils.BeanUtil;
import org.apache.axis2.util.StreamWrapper;


public class AxiomUtil
{

    // 把user对象转化成相应的omElment对象
    public static <T> OMElement castElment(T u)
    {
        XMLStreamReader reader = BeanUtil.getPullParser(u);
        StreamWrapper parser = new StreamWrapper(reader);
        OMXMLParserWrapper stAXOMBuilder = OMXMLBuilderFactory.createStAXOMBuilder(
            OMAbstractFactory.getOMFactory(), parser);
        OMElement element = stAXOMBuilder.getDocumentElement();
        return element;
    }

    public static <T> OMElement castElment(List<T> list,Class<?> c)
    {
        // 构建 OMFactory 工厂
        //OMFactory factory = OMAbstractFactory.getOMFactory();
        //OMNamespace omNamespace = factory.createOMNamespace("http://test.com", "");
        OMElement omElement = BeanUtil.getOMElement(new QName("root"),list.toArray(),new QName(c.getSimpleName()), false, null);
        return omElement;
    }
    
    
    public static OMElement str2OMElement(String xmlStr) {  
        OMElement xmlValue;  
        try {  
            xmlValue = new StAXOMBuilder(new ByteArrayInputStream(xmlStr  
                    .getBytes("UTF-8"))).getDocumentElement();  
            return xmlValue;  
        } catch (Exception e) {  
            return null;  
        }  
    }  
}