package org.kyutech.kawabata.UserInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kawabata
 * 
 */
public class Dictionary {
	Map<String, String> dictionaryMap = new HashMap<>();

	/**
	 * メインクラスです
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Dictionary dic = new Dictionary();
	}

	/**
	 * コンストラクタです
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public Dictionary() throws IOException {
		File read_file = new File("sampledoc/dictionary.csv"); //$NON-NLS-1$
		BufferedReader br = new BufferedReader(new FileReader(read_file));
		String s;
		while ((s = br.readLine()) != null) {
			String[] sAry = s.split(","); //$NON-NLS-1$
			if (sAry.length > 1) {
				this.dictionaryMap.put(sAry[0], sAry[1]);
				System.out.println(this.dictionaryMap.get(sAry[0]));
			}
		}
	}
}
