/**
 * created at 25.01.2017
 */
package de.m_bleil.async;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

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
		    .tag("kayak_rental", "yes")
		    .boundingBox(52.82, 11.94, 53.89, 14.10)
		    .end()
		    .output(OutputVerbosity.BODY,
			    OutputModificator.CENTER,
			    OutputOrder.QT,
			    10000)
		    .build();

	    String server = "http://overpass-api.de/api/interpreter?data=";
	    HttpClient client = HttpClient.newHttpClient();
	    HttpRequest request = HttpRequest
		    .newBuilder(URI.create(server + query))
		    .timeout(Duration.ofSeconds(5))
		    .build();
	    HttpResponse<InputStream> response = client.send(request,
		    HttpResponse.BodyHandlers.ofInputStream());

	    System.out.println(response.body());
	}
	catch (IOException | InterruptedException e) {
	    e.printStackTrace();
	}
    }
}
