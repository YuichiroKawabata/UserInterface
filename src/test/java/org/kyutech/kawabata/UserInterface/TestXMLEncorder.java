package org.kyutech.kawabata.UserInterface;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.kyutech.kawabata.UserInterface.xml.DictionaryClass;
import org.kyutech.kawabata.UserInterface.xml.XMLEncorder;

/**
 * @author kawabata
 *XMLEncorderに対してのテストを記述します
 */
public class TestXMLEncorder {
	/**
	 * 決め打ちされたファイルが正しく変換されているか確認するテストです
	 */
	@SuppressWarnings("static-method")
	@Test
	public void ConvertXMLTest() {
		XMLEncorder readXml = new XMLEncorder();
		String result = readXml.encodeXML("src/main/resources/sampledoc/input.edm","output.edm"); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(result, is("変換完了しました"));
	}
}
