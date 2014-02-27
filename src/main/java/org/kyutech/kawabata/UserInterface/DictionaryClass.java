package org.kyutech.kawabata.UserInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kawabata
 * 
 */
public class DictionaryClass {
	public Map<String, String> dictionaryMap = new HashMap<>();

	/**
	 * メインクラスです
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		DictionaryClass dic = new DictionaryClass();
	}

	/**
	 * コンストラクタです
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public DictionaryClass(){
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
				if (sAry.length > 1) {
					String key = sAry[0].replaceAll("\"","");
					String value = sAry[1].replaceAll("\"","");
					//this.dictionaryMap.put(sAry[0],this.camelToSnake((sAry[1])));
					this.dictionaryMap.put(key,this.camelToSnake(value));
					//System.out.println(key + "   " +this.camelToSnake(this.dictionaryMap.get(key)));
					//System.out.println(this.camelToSnake(this.dictionaryMap.get(sAry[0])));
					//1System.out.println(sAry[0] + "   " + this.dictionaryMap.get("\"" +"プロジェクトマネージャー"+"\""));
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * 	 キャメルケース表記をスネークケース表記（小文字）へ
	 * @param targetStr
	 * @return 返還後の文字列
	 */
	public String camelToSnake(String targetStr) {
		if(targetStr.equals("")) return "";
		String convertedStr = targetStr
				.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
				.replaceAll("([a-z])([A-Z])", "$1_$2");
		return convertedStr.toUpperCase();
	}
}
