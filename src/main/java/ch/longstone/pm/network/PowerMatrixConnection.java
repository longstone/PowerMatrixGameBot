package ch.longstone.pm.network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class PowerMatrixConnection {
	DefaultHttpClient httpClient;
	String cookie;

	public PowerMatrixConnection(DefaultHttpClient httpclient) {
		this.httpClient = httpclient;
	}

	/**
	 * do a simple Post, ignoring the result
	 * 
	 * @param post
	 * @throws ClientProtocolException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void doPost(HttpPost post) throws ClientProtocolException, UnsupportedEncodingException, IOException {
		try {
			HttpResponse httpResponse = httpClient.execute(FlowManager.createLoginPost(post));
			HttpEntity loginEntity = httpResponse.getEntity();
			EntityUtils.consume(loginEntity);
		} finally {
			post.releaseConnection();
		}
	}

	/**
	 * Loads the json data from a specified url @target
	 * 
	 * @param target
	 *            the url to send the post
	 * @throws ClientProtocolException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public String getJSON(String target) throws ClientProtocolException, UnsupportedEncodingException, IOException {
		HttpPost httpPost = new HttpPost(target);
		addHeadersPost(httpPost);
		String result = "";
		try {
			HttpResponse responseLogin = httpClient.execute(httpPost);
			System.out.println(responseLogin.getStatusLine());
			HttpEntity loginEntity = responseLogin.getEntity();
			responseLogin.getEntity().getContent();
			result = EntityUtils.toString(loginEntity);
			EntityUtils.consume(loginEntity);
		} finally {
			httpPost.releaseConnection();

		}
		return result;
	}

	// helper adds the headers
	private static void addHeadersPost(HttpPost httpost) {
		httpost.addHeader("Host", "powermatrixgame.com");
		httpost.addHeader("Connection", "keep-alive");
		httpost.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpost.addHeader("Origin", "http://powermatrixgame.com");
		httpost.addHeader("X-Requested-With", "XMLHttpRequest");
		httpost.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36");
		httpost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		httpost.addHeader("Referer", "http://powermatrixgame.com/game/start");
		httpost.addHeader("Accept-Encoding", "");
		httpost.addHeader("Accept-Language", "de-DE,de;q=0.8,en-US;q=0.6,en;q=0.4");
	}

}
