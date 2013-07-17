package ch.longstone.pm.main;

import java.io.IOException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import ch.longstone.pm.json.Parser;
import ch.longstone.pm.network.FlowManager;
import ch.longstone.pm.network.PowerMatrixConnection;

public class Main {
	private final static String HOME = "http://powermatrixgame.com/game/start";
	private final static String LAST_UPDATE = "http://powermatrixgame.com/my/last_updated";

	/**
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Parser p = new Parser();
		
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		System.out.println("Laden der Startseite:");
		FlowManager.getFrontpage(httpClient, HOME);

		// prepare our connection managers
		PowerMatrixConnection con = new PowerMatrixConnection(httpClient);

		// Login
		HttpPost httpPost = FlowManager.createLoginPost(new HttpPost(HOME));
		con.doPost(httpPost);

		// get "last_updated"
		String json = con.getJSON(LAST_UPDATE);
		p.parseLastUpdated(json);

	}

}
