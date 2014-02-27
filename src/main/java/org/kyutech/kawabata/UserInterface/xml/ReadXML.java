package org.kyutech.kawabata.UserInterface.xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Dictionary;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.kyutech.kawabata.UserInterface.DictionaryClass;

/**
 * @author kawabata dom4jライブラリを使用してXMLファイルを読み込むクラスです
 */
public class ReadXML {

	DictionaryClass dic; 
	/**
	 * @param args
	 *            メインメソッドです
	 */
	public static void main(String[] args) {
		ReadXML readXml = new ReadXML();
		readXml.encodeXML("sampledoc/input.edm"); //$NON-NLS-1$
	}
	
	void encodeXML(String path){
		SAXReader reader = new SAXReader();
		this.dic = new DictionaryClass();
		try {
			Document doc = reader.read(path);
			String xml = doc.asXML();
			//System.out.println(xml);
			for (int i = 0; i < doc.selectNodes("/ERD/ENTITY/ATTR").size(); i++) {
				Element newElement = (Element) doc.selectNodes("/ERD/ENTITY/ATTR").get(i); //$NON-NLS-1$
				String attributeValue = newElement.attribute("P-NAME").getValue(); //$NON-NLS-1$ 
				String newAttributeValue = this.dic.dictionaryMap.get(attributeValue); 
				System.out.println(newAttributeValue);
				newElement.attribute("P-NAME").setValue(newAttributeValue);
			}
			for (int i = 0; i < doc.selectNodes("/ERD/ENTITY").size(); i++) {
				Element newElement = (Element) doc.selectNodes("/ERD/ENTITY").get(i); //$NON-NLS-1$
				String attributeValue = newElement.attribute("P-NAME").getValue(); //$NON-NLS-1$ 
				String newAttributeValue = this.dic.dictionaryMap.get(attributeValue); 
				System.out.println(newAttributeValue);
				newElement.attribute("P-NAME").setValue(newAttributeValue);
			}
			System.out.println("index size: " +  doc.selectNodes("/ERD/ENTITY/INDEX").size());
			for (int i = 0; i < doc.selectNodes("/ERD/ENTITY/INDEX").size(); i++) {
				Element newElement = (Element) doc.selectNodes("/ERD/ENTITY/INDEX").get(i); //$NON-NLS-1$
				String attributeValue = newElement.attribute("P-NAME").getValue(); //$NON-NLS-1$ 
				String newAttributeValue = this.dic.dictionaryMap.get(attributeValue);
				System.out.println(newAttributeValue);
				System.out.println("index " + newElement.asXML());
				newElement.attribute("P-NAME").setValue("TESTTEST");
			}

			xml = doc.asXML();
			writingXML(doc);
			System.out.println(xml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}	

	}
	
	@SuppressWarnings({ "null", "resource" })
	static void writingXML(Document document){
		FileOutputStream fos = null;  
		OutputFormat format = null;
		XMLWriter writer = null;
		try {
		    fos = new FileOutputStream("output.edm"); 
		    format = OutputFormat.createPrettyPrint();
		    writer = new XMLWriter(fos, format);
		    writer.write(document);
		} catch (Exception e) {
		    // エラー処理
		} finally {
		    try {
		        writer.close();
		    } catch (IOException e) {
		        // エラー処理
		    }
		}
	}

}