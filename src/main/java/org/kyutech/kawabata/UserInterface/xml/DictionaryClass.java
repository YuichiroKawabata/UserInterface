package org.kyutech.kawabata.UserInterface.xml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.kyutech.kawabata.UserInterface.sortComparate;

/**
 * @author kawabata 辞書クラスです。辞書データの格納や日本語から英語への変換を扱えます
 */
public class DictionaryClass {
	/**
	 * 変換元をキーとしたマップです
	 */
	public Map<String, String> dictionaryMap = new HashMap<>();
	ArrayList<String> keyList = new ArrayList<String>();

	/**
	 * コンストラクタです
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings({ "resource", "unchecked" })
	public DictionaryClass() {
		//csvファイルのロード
		InputStream in = getClass().getClassLoader().getResourceAsStream("sampledoc/dictionary.csv");
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(in));
		String str;
		try {
			while ((str = br.readLine()) != null) {
				String[] sAry = str.split(","); //$NON-NLS-1$
				if (sAry.length > 0) {
					String key = sAry[0].replaceAll("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$
					this.keyList.add(key);
					String value = "";
					if (sAry.length>1)
						value = sAry[1].replaceAll("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$
					this.dictionaryMap.put(key, value);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(this.keyList,new sortComparate());
	}

	/**
	 * キャメルケース表記をスネークケース表記（小文字）へ
	 * 
	 * @param str
	 *            変換対象
	 * @return 変換後の文字列
	 */
	public String camelToSnake(String str) {
		if (str.equals(""))return ""; //$NON-NLS-1$ //$NON-NLS-2$
		String convertedStr = str.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2") //$NON-NLS-1$ //$NON-NLS-2$
				.replaceAll("([a-z])([A-Z])", "$1_$2"); //$NON-NLS-1$ //$NON-NLS-2$
		return convertedStr.toUpperCase();
	}

	/**
	 * 日本語のタグから英語のタグに変換します
	 * 
	 * @param str
	 *            変換対象
	 * @return　変換後の文字列
	 */
	@SuppressWarnings("unchecked")
	public String japaneseToEnglish(String str) {

		for (int i = 0; i < this.keyList.size(); i++) {
			if (str.contains(this.keyList.get(i))) {
				str = str.replaceAll(this.keyList.get(i),
						this.dictionaryMap.get(this.keyList.get(i)));
			}
		}
		return camelToSnake(str);
	}
}
