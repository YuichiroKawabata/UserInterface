package org.kyutech.kawabata.UserInterface.xml;

import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * @author kawabata dom4jライブラリを使用してXMLファイルを読み込むクラスです
 */
public class ReadXML {

	/**
	 * @param args
	 *            メインメソッドです
	 */
	private Dictionary dictionay;
	public static void main(String[] args) {
		SAXReader reader = new SAXReader();
		
		try {
			Document doc = reader.read("sampledoc/input.edm"); //$NON-NLS-1$
			Element rootElement = doc.getRootElement();
			String xml = doc.asXML();
			System.out.println(xml);
			List nodes = doc.selectNodes("/ERD/ENTITY/ATTR"); //$NON-NLS-1$;
			for (int i = 0; i < nodes.size(); i++) {
				Element newElement = (Element) doc.selectNodes("/ERD/ENTITY/ATTR").get(i);
				newElement.attribute("P-NAME").setValue("TESTTEST");
				System.out.println(newElement.asXML());
			}
			nodes = doc.selectNodes("/ERD/ENTITY"); //$NON-NLS-1$;
			for (int i = 0; i < nodes.size(); i++) {
				Element newElement = (Element) doc.selectNodes("/ERD/ENTITY").get(i);
				newElement.attribute("P-NAME").setValue("TESTTEST");
				System.out.println(newElement.asXML());
			}
			xml = doc.asXML();
			System.out.println(xml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	

		
		

	}

}