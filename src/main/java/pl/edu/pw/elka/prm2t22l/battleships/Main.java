package pl.edu.pw.elka.prm2t22l.battleships;

import org.json.JSONObject;

public class Main {
	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "Jan Kowalski");
		jsonObject.put("age", 22);
		jsonObject.put("city", "Warsaw");
		jsonObject.put("student", true);
		System.out.println(jsonObject);
	}
}
