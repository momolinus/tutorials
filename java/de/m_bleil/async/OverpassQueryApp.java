/**
 * created at 25.01.2017
 */
package de.m_bleil.async;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import hu.supercluster.overpasser.library.output.OutputFormat;
import hu.supercluster.overpasser.library.output.OutputModificator;
import hu.supercluster.overpasser.library.output.OutputOrder;
import hu.supercluster.overpasser.library.output.OutputVerbosity;
import hu.supercluster.overpasser.library.query.OverpassQuery;

/**
 * @author Marcus Bleil, www.m-bleil.de
 */
public class OverpassQueryApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
	try {
	    String query = new OverpassQuery()
		    .format(OutputFormat.XML)
		    .timeout(60)
		    .filterQuery()
		    .node()
		    .tag("amenity", "supermarket")
		    .boundingBox(52.82, 11.94, 53.89, 14.10)
		    .end()
		    .output(OutputVerbosity.BODY,
			    OutputModificator.CENTER,
			    OutputOrder.QT,
			    10000)
		    .build();
	    System.out.println(query);
	    HttpClient client = HttpClient.newHttpClient();

	    String test = "[out:xml][timeout:60];"
		    + " (node[\"amenity\"=\"shops\"]"
		    + "(52.82,11.94,53.89,14.1);<;); out body center qt 10000;";

	    URI uri = new URI(
		    "https",
		    "overpass-api.de",
		    "/api/interpreter",
		    "data=" + query.replaceAll("\"", ""),
		    null);
	    System.out.println(uri.toString());

	    HttpRequest request = HttpRequest
		    .newBuilder(uri)
		    .timeout(Duration.ofSeconds(5))
		    .build();
	    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
	    System.out.println(response.statusCode());
	    System.out.println(response.body());
	}
	catch (IOException | InterruptedException | URISyntaxException e) {
	    e.printStackTrace();
	}
    }
}
