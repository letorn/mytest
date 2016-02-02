package javascript;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Main {

	public static void main(String[] args) throws Exception {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		engine.put("性别", 1);
		engine.eval("性别 = 性别 +1");
		Object val = engine.get("性别");
		System.out.println(val);
	}
	
}
