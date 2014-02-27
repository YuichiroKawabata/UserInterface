package org.kyutech.kawabata.UserInterface.xml;

import java.io.FileOutputStream;
import java.io.IOException;
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

	private static final String PNAME = "P-NAME"; //$NON-NLS-1$
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
			changePName(doc,"/ERD/ENTITY/ATTR"); //$NON-NLS-1$
			changePName(doc,"/ERD/ENTITY"); //$NON-NLS-1$
			changePName(doc,"/ERD/ENTITY/INDEX"); //$NON-NLS-1$
			
			//変換したXMLを出力します
			writingXML(doc,""); //$NON-NLS-1$
			System.out.println(doc.asXML());
		} catch (DocumentException e) {
			e.printStackTrace();
		}	

	}
	
	private void changePName(Document doc, String xPath) {
		// TODO Auto-generated method stub
		for (int i = 0; i < doc.selectNodes(xPath).size(); i++) {
			Element newElement = (Element) doc.selectNodes(xPath).get(i);
			String attributeValue = newElement.attribute(PNAME).getValue();
			String newAttributeValue = this.dic.japaneseToEnglish(attributeValue); 
			System.out.println(newAttributeValue);
			newElement.attribute(PNAME).setValue(newAttributeValue);
		}
	}

	@SuppressWarnings({ "null", "resource" })
	static void writingXML(Document document,String path){
		FileOutputStream fos = null;  
		OutputFormat format = null;
		XMLWriter writer = null;
		path = "output.edm"; //$NON-NLS-1$
		try {
		    fos = new FileOutputStream(path);
		    format = OutputFormat.createPrettyPrint();
		    writer = new XMLWriter(fos, format);
		    writer.write(document);
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    try {
		        writer.close();
		    } catch (IOException e) {
			    e.printStackTrace();
		    }
		}
	}

}