/**
 * created at 02.05.2019
 */
package de.m_bleil.java_api.string;

import org.pmw.tinylog.Logger;

/**
 * @author M. Comp. Sc. Marcus Bleil<br>
 *         TU Berlin<br>
 *         Arbeits-, Ingenieur- und Organisationspsychologie<br>
 *         Marchstr. 12, 10587 Berlin<br>
 *         marcus.bleil@tu-berlin.de<br>
 *         http://www.aio.tu-berlin.de
 */
public class CharacterInspection {

	public static void main(String[] args) {

		for (int i = 0; i < 256; i++) {
			String character = Character.toString((char) i);

			Logger.info("{}={}", i, character);
		}
	}
}
