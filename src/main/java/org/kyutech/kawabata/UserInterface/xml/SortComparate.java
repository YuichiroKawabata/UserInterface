package org.kyutech.kawabata.UserInterface.xml;

import java.util.Comparator;

/**
 * @author kawabata
 *ソート用のクラスです
 */
public class SortComparate implements Comparator {
	@Override
	public int compare(Object o1, Object o2) {
		return o2.toString().length() - o1.toString().length();
	}
}
