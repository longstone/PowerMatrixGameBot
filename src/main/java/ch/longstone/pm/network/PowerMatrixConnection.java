package ch.longstone.pm.network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class PowerMatrixConnection {
	DefaultHttpClient httpClient;
	String cookie;

	public PowerMatrixConnection(DefaultHttpClient httpclient) {
		this.httpClient = httpclient;
	}

	public static HttpPost createSimplePost(String target) {
		HttpPost httpPost = new HttpPost(target);
		addHeadersPost(httpPost);
		return httpPost;
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
		HttpPost httpPost = PowerMatrixConnection.createSimplePost(target);
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

	public boolean pauseBuilding(long id) throws UnsupportedEncodingException {
		HttpPost httpPost = PowerMatrixConnection.createSimplePost("http://powermatrixgame.com/my/pause");
		return pauseOrResume(httpPost, id);
	}

	public boolean resumeBuilding(long id) throws UnsupportedEncodingException {
		HttpPost httpPost = PowerMatrixConnection.createSimplePost("http: // powermatrixgame.com/my/resume");
		return pauseOrResume(httpPost, id);
	}

	private boolean pauseOrResume(HttpPost httpPost, long id) throws UnsupportedEncodingException {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("building_id", id + ""));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		boolean result = false;
		try {
			HttpResponse responseLogin = httpClient.execute(httpPost);
			System.out.println(responseLogin.getStatusLine());
			HttpEntity loginEntity = responseLogin.getEntity();
			responseLogin.getEntity().getContent();
			result = "true".equals(EntityUtils.toString(loginEntity));
			EntityUtils.consume(loginEntity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
