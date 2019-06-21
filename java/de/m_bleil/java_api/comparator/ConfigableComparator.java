/**
 *
 */
package de.m_bleil.java_api.comparator;

import java.util.*;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class ConfigableComparator implements Comparator<String> {

	private List<String> leadingTokens = new ArrayList<>();
	private Comparator<String> naturalOrder = Comparator.naturalOrder();

	/**
	 * @param string
	 */
	public ConfigableComparator(String string) {
		leadingTokens.add(string);
	}

	public ConfigableComparator(String... string) {
		leadingTokens.addAll(Arrays.asList(string));
	}

	public ConfigableComparator(Collection<String> string) {
		leadingTokens.addAll(string);
	}

	@Override
	public int compare(String o1, String o2) {
		if (naturalOrder.compare(o1, o2) == 0) {
			return 0;
		}

		if (leadingTokens.contains(o1) || leadingTokens.contains(o2)) {
			String lowest = null;
			for (String token : leadingTokens) {
				if (token.equals(o1) || token.equals(o2)) {
					lowest = token;
					break;
				}
			}

			if (lowest.equals(o1)) {
				return -1;
			}
			else {
				return 1;
			}
		}

		return naturalOrder.compare(o1, o2);
	}

}
