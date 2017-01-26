/**
 * created at 25.01.2017
 */
package de.m_bleil.async;

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
 * @author Marcus Bleil, www.m-bleil.de
 */
public class Demo {

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
			.boundingBox(52.82, 11.94, 53.89, 14.10)
			.end()
			.output(OutputVerbosity.BODY, OutputModificator.CENTER, OutputOrder.QT, 10000)
			.build();

		try (AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient()) {

			String server = "http://overpass-api.de/api/interpreter?data=";

			String data = "[out:xml][timeout:25];(node[\"kayak_rental\"=\"yes\"]"
				+ "(52.82,11.94,53.85,14.10););" + "out body;>;out skel qt;";

			Future<Response> f = asyncHttpClient.prepareGet(server + query).execute();

			Response r = f.get();
			String result = r.getResponseBody(StandardCharsets.UTF_8);

			try (BufferedWriter writer =
				Files.newBufferedWriter(Paths.get("result.osm.xml"), StandardCharsets.UTF_8)) {
				writer.write(result);
			}
		}
		catch (InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();
		}
	}
}
