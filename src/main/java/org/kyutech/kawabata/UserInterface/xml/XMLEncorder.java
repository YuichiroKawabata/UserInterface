package org.kyutech.kawabata.UserInterface.xml;

import java.io.FileOutputStream;
import java.io.IOException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author kawabata dom4jライブラリを使用してXMLファイルを読み込むクラスです
 */
public class XMLEncorder {

	private static final String PNAME = "P-NAME"; //$NON-NLS-1$
	DictionaryClass dic; 
	/**
	 * @param args
	 *            メインメソッドです
	 */
	public static void main(String[] args) {
		XMLEncorder readXml = new XMLEncorder();
		readXml.encodeXML("src/main/resources/sampledoc/input.edm","output.edm"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	/**
	 * XMLを変換して出力します
	 * @param inputPath
	 * @param outputPath
	 * @return 変換が成功したかを返します
	 */
	public String encodeXML(String inputPath,String outputPath){
		SAXReader reader = new SAXReader();
		try {
			this.dic = new DictionaryClass();			
		} catch (Exception e) {
			// TODO: handle exception
			return e.toString();
		}

		try {
			Document doc = reader.read(inputPath);
			changePName(doc,"/ERD/ENTITY/ATTR"); //$NON-NLS-1$
			changePName(doc,"/ERD/ENTITY"); //$NON-NLS-1$
			changePName(doc,"/ERD/ENTITY/INDEX"); //$NON-NLS-1$
			//変換したXMLを出力します(変換結果を返します)
			return writingXML(doc,outputPath);		
		} catch (DocumentException e) {
			e.printStackTrace();
			return "変換失敗しました" + e.toString();	 //$NON-NLS-1$
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
	static String writingXML(Document document,String path){
		FileOutputStream fos = null;  
		OutputFormat format = null;
		XMLWriter writer = null;
		try {
		    fos = new FileOutputStream(path);
		    format = OutputFormat.createPrettyPrint();
		    writer = new XMLWriter(fos, format);
		    writer.write(document);
		    System.out.println(document.asXML());
		    return "変換完了しました"; //$NON-NLS-1$
		} catch (Exception e) {
		    e.printStackTrace();
			return "変換失敗しました" + e.toString();	 //$NON-NLS-1$
		} finally {
		    try {
		        writer.close();
		    } catch (IOException e) {
			    e.printStackTrace();
				return "変換失敗しました" + e.toString();	 //$NON-NLS-1$
		    }
		}
	}

}