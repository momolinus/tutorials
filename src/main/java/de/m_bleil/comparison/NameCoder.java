/**
 * 
 */
package de.m_bleil.comparison;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.pmw.tinylog.Logger;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class NameCoder {

	/**
	 * pattern build by RegEx {@code ([0-9]+)$}, meaning fetched all number at the end of string
	 */
	private static final Pattern INDEX_PATTERN = Pattern.compile("([0-9]+)$");

	private int index = 0;

	private String name;
	private String prefix = "";

	public NameCoder(String name) {
		this.name = Objects.requireNonNull(name, "code must not be null");

		List<String> tokens = Arrays
			.stream(name.split("_"))
			.collect(Collectors.toList());

		int countTokens = tokens.size();

		if (countTokens >= 2) {
			for (int i = 0; i < countTokens - 1; i++) {
				prefix += tokens.get(i);
			}
			index = buildIndex(tokens.get(countTokens - 1));
		}
		else {
			prefix = tokens.get(0);
		}
	}

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public String getPrefix() {
		return prefix;
	}

	@Override
	public String toString() {
		return "NameCoder [name=" + name + ", index=" + index + ", prefix=" + prefix + "]";
	}

	private int buildIndex(String indexToken) {
		Matcher matcher = INDEX_PATTERN.matcher(indexToken);
		int parsedIndex = Integer.MIN_VALUE;

		if (matcher.find()) {
			parsedIndex = Integer.parseInt(matcher.group(1));

			Logger.debug("index = parse({}) for {}", matcher.group(1), indexToken);
		}

		return parsedIndex;
	}
}
