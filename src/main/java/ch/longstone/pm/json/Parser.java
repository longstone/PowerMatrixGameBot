package ch.longstone.pm.json;

import ch.longstone.pm.json.domain.Last_Updated;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Parser {
	Gson gson;
	JsonParser parser;

	public Parser() {
		gson = new Gson();
		parser = new JsonParser();
	}

	public void parseLastUpdated(String jsonString) {
		JsonObject obj = parser.parse(jsonString).getAsJsonObject();
		Last_Updated lastUp = gson.fromJson(obj, Last_Updated.class);

		System.err.println(lastUp); // to String in DataObjects implemented
	}
}
