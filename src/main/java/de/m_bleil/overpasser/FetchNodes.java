/**
 *
 */
package de.m_bleil.overpasser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import hu.supercluster.overpasser.library.output.*;
import hu.supercluster.overpasser.library.query.OverpassQuery;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class FetchNodes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String query = new OverpassQuery()
			.format(OutputFormat.JSON)
			.timeout(60)
			.filterQuery()
			.node()
			.tag("railway", "station")
			// .tagNot("access", "private")
			.boundingBox(52.8408089, 12.2297711, 53.6785119, 13.8548903)
			.end()
			.output(OutputVerbosity.BODY, OutputModificator.CENTER, OutputOrder.QT, 10)
			.build();

		System.out.println(query);

		try {
			URL url = new URL("http://overpass-api.de/api/interpreter?data=" + query);

			try (InputStream is = url.openStream();
				Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.name())) {

				System.out.println(scanner.useDelimiter("\\Z").next());
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
