/**
 * created at 05.05.2017
 */
package de.m_bleil.java_api.javascript;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import org.pmw.tinylog.Logger;

/**
 * @author Marcus Bleil, www.m-bleil.de
 *
 */
public class PrintScriptEngines {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final ScriptEngineManager manager = new ScriptEngineManager();

		for (ScriptEngineFactory enginFactory : manager.getEngineFactories()){
			Logger.info(enginFactory.getEngineName());
		}
	}
}
