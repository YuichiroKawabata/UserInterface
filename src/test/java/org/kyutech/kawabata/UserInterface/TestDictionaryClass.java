package org.kyutech.kawabata.UserInterface;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.kyutech.kawabata.UserInterface.xml.DictionaryClass;

/**
 * @author kawabata
 * 
 */
public class TestDictionaryClass {
	/**
	 * 変換が正しく行われているか確認するテストです
	 */
	@SuppressWarnings("static-method")
	@Test
	public void dictionaryConvertTest() {
		DictionaryClass dic = new DictionaryClass();
		assertThat(dic.japaneseToEnglish("プロジェクトマネージャー"), is("PM")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("プロジェクトリーダー"), is("PL")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("プロジェクト名"), is("PROJECT_NM")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("プロジェクト開始日"), is("PROJECT_START_DATE")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("プロジェクト終了日"), is("PROJECT_END_DATE")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("プロジェクト種別"), is("PROJECT_TYPE")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("プロジェクト分類"), is("PROJECT_CLASS")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("プロジェクトID"), is("PROJECT_ID")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("プロジェクト"), is("PROJECT")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("顧客ID"), is("CUSTOMER_ID")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("顧客名"), is("CUSTOMER_NM")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("顧客"), is("CUSTOMER")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("業種"), is("JOB_TYPE")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("備考（自由記述）"), is("NOTE_FREE")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("PK_プロジェクト"), is("PK_PROJECT")); //$NON-NLS-1$ //$NON-NLS-2$
		assertThat(dic.japaneseToEnglish("PK_顧客"), is("PK_CUSTOMER")); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
