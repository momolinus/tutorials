/**
 * created at 04.05.2017
 */
package de.m_bleil.java_api.javascript;

import javax.script.*;

import org.pmw.tinylog.Logger;

/**
 * @author Marcus Bleil, www.m-bleil.de
 */
public class JavaScriptDemo {

	public static void main(String[] args) {

		ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

		try {
			Object result = engine.eval("Math.random();");
			Logger.info("engine.eval(\"Math.random();\") = {}", result);
		}
		catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
