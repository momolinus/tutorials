/**
 *
 */
package de.m_bleil.overpasser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.asynchttpclient.*;

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
			.format(OutputFormat.XML)
			.timeout(60)
			.filterQuery()
			.node()
			.tag("railway", "station")
			// .tagNot("access", "private")
			.boundingBox(52.84, 12.23, 53.68, 13.85)
			.end()
			.output(OutputVerbosity.BODY, OutputModificator.CENTER, OutputOrder.QT, 10)
			.build();

		System.out.println(query);

		String server = "http://overpass-api.de/api/interpreter?data=";
		try (AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient()) {
			Future<Response> f = asyncHttpClient.prepareGet(server + query).execute();

			Response r = f.get();

			String resultText = r.getResponseBody(StandardCharsets.UTF_8);
			System.out.println(resultText);
			try (BufferedWriter outputFile =
				Files.newBufferedWriter(Paths.get("stations.osm.xml"), StandardCharsets.UTF_8)) {
				outputFile.write(resultText);
			}
		}
		catch (InterruptedException | ExecutionException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
