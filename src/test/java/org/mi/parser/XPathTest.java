package org.mi.parser;

import org.apache.ibatis.io.Resources;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

/**
 * @author yfwang2012@gmail.com
 * @description
 * @since 2019/12/9 21:16
 */
public class XPathTest {
  private static String resource = "D:\\code\\github\\mybatis-3\\src\\test\\java\\resources\\miXPathTest.xml";

  public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    //开启验证
    documentBuilderFactory.setValidating(true);
    documentBuilderFactory.setNamespaceAware(false);
    documentBuilderFactory.setIgnoringComments(true);
    documentBuilderFactory.setIgnoringElementContentWhitespace(false);
    documentBuilderFactory.setCoalescing(false);
    documentBuilderFactory.setExpandEntityReferences(true);

    //创建 DocumentBuilder
    DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
    //设置异常处理对象
    builder.setErrorHandler(new ErrorHandler() {
      @Override
      public void warning(SAXParseException exception) throws SAXException {
        System.out.println("warning:" + exception.getMessage());
      }

      @Override
      public void error(SAXParseException exception) throws SAXException {
        System.out.println("error:" + exception.getMessage());
      }

      @Override
      public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("fatalError:" + exception.getMessage());
      }
    });

    //将文档加载到一个 document 对象中
    Document document = builder.parse(resource);
    //创建 XPathFactory
    XPathFactory factory = XPathFactory.newInstance();
    //创建 xPath
    XPath xPath = factory.newXPath();

    //编译 XPath 表达式
    XPathExpression expr = xPath.compile("//book[author='周志明']/title/text()");
    Object result = expr.evaluate(document, XPathConstants.NODESET);
    System.out.println("查询作者为周志明的图书的标题：");
    NodeList nodes = (NodeList) result;
    for (int i = 0; i< nodes.getLength(); i++) {
      System.out.println(nodes.item(i).getNodeValue());
    }

    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    System.out.println("查询1997年之后的图书的标题：");
    nodes = (NodeList) xPath.evaluate("//book[@year>1997]/title/text()", document, XPathConstants.NODESET);
    for (int i = 0; i< nodes.getLength(); i++) {
      System.out.println(nodes.item(i).getNodeValue());
    }

    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    System.out.println("查询1997年之后的图书的属性和标题：");
    nodes = (NodeList) xPath.evaluate("//book[@year>1997]/@*|//book[@year>1997]/title/text()", document, XPathConstants.NODESET);
    for (int i = 0; i< nodes.getLength(); i++) {
      System.out.println(nodes.item(i).getNodeValue());
    }
  }
}
