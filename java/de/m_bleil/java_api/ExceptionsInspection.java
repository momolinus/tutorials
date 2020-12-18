/**
 * created at 15.08.2019
 */
package de.m_bleil.java_api;

import org.pmw.tinylog.Logger;

/**
 * @author M. Comp. Sc. Marcus Bleil<br>
 *         TU Berlin<br>
 *         Arbeits-, Ingenieur- und Organisationspsychologie<br>
 *         Marchstr. 12, 10587 Berlin<br>
 *         marcus.bleil@tu-berlin.de<br>
 *         http://www.aio.tu-berlin.de
 */
public class ExceptionsInspection {

	public static void main(String[] args) {
		new RuntimeException("hello world").printStackTrace();

		Logger.info("es geht weiter");
	}
}
