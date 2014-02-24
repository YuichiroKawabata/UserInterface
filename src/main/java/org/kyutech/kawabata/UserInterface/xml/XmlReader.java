package org.kyutech.kawabata.UserInterface.xml;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XmlReader {
	private final static String INDEX = "INDEX";
	private final static String ATTR = "ATTR";
	private final static String ENTITY ="ENTITY";
	private final static String ENTITYS ="ENTITYs";
	private String xmlFile;
	
	public static void main(String[] args) throws Exception {
		System.out.println("readStart");
		XmlReader xmlReader = new XmlReader("tt.xml");
		XmlReader.parseXml(xmlReader.xmlFile);
	}
	
	/**
	 * @param filename
	 */
	public XmlReader(String filename) {
		this.xmlFile = filename;
	}
	
	private static void parseXml(String xmlFile) throws Exception {
		DocumentBuilderFactory dbf 
					= DocumentBuilderFactory.newInstance();
		DocumentBuilder db 
					= dbf.newDocumentBuilder();
		
		Document doc = db.parse(new FileInputStream(xmlFile));;
		
		Element root =doc.getDocumentElement();
		
		walk(root);
	}
	
	private static void walk(Node node) {
		for (Node ch = node.getFirstChild();   //指定されたノードの最初の子を取得して
				  ch != null;				   //子がいる限り
				  ch = ch.getNextSibling()) {  //兄弟をたどる
			//System.out.println(ch.getNodeName());
			//今度はdependenciesというノードに絞って解析する
			getSpecificNodeValue(ENTITYS, ch.getNodeName(),ch);
		}
	}
	
	private static void getSpecificNodeValue(String targetNodeName, String nodeName, Node node) {
		if(nodeName != null) {
			if (targetNodeName.equals(nodeName)) {
				System.out.println(node.getNodeName()); //dependencies
				for (Node ch = node.getFirstChild();    //dependenciesの最初の子を取得する
						  ch != null;					//子がいる限り
						  ch = ch.getNextSibling()) {   //兄弟をたどる
					System.out.println(ch.getNodeName());
					getSpecificNodeValue(ENTITY, ch.getNodeName(),ch);
				}
			}
		}
	}
}