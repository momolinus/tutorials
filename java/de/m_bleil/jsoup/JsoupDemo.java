/**
 * created at 18.06.2019
 */
package de.m_bleil.jsoup;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Marcus Bleil
 */
public class JsoupDemo {

	public static void main(String[] args) {
		try {
			File htmlFile = new File(args[0]);

			Document document = Jsoup.parse(htmlFile, null);
			Elements links = document.getElementsByTag("a");

			List<String> emails = new ArrayList<>();
			for (Element link : links) {
				String linkText = link.text();
				if (linkText.contains("@")) {
					emails.add(linkText);
				}
			}

			Collections.sort(emails);
			emails.forEach(System.out::println);

			System.out.println("... successfully finished");
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
