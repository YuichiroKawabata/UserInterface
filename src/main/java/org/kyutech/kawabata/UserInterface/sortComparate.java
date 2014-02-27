package org.kyutech.kawabata.UserInterface;

import java.util.Comparator;

/**
 * @author kawabata
 *ソート用のクラスです
 */
public class sortComparate implements Comparator {
	@Override
	public int compare(Object o1, Object o2) {
		return o2.toString().length() - o1.toString().length();
	}
}
