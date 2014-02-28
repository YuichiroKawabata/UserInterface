package org.kyutech.kawabata.UserInterface;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kawabata
 * 
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
		File read_file = new File("sampledoc/dictionary.csv"); //$NON-NLS-1$
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(read_file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s;
		try {
			while ((s = br.readLine()) != null) {
				String[] sAry = s.split(","); //$NON-NLS-1$
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
	 * @param str 変換対象
	 * @return 返還後の文字列
	 */
	public String camelToSnake(String str) {
		if (str.equals(""))return ""; //$NON-NLS-1$ //$NON-NLS-2$
		String convertedStr = str.replaceAll(
				"([A-Z]+)([A-Z][a-z])", "$1_$2") //$NON-NLS-1$ //$NON-NLS-2$
				.replaceAll("([a-z])([A-Z])", "$1_$2"); //$NON-NLS-1$ //$NON-NLS-2$
		return convertedStr.toUpperCase();
	}

	/**
	 * 日本語のタグから英語のタグに変換します
	 * @param str 変換対象
	 * @return　返還後の文字列
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
