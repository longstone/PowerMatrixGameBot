package ch.longstone.pm.network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import ch.longstone.pm.credentials.Creds;

public class FlowManager {

	/**
	 * creates the HttpPost for Login
	 * 
	 * @param httpPost
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static HttpPost createLoginPost(HttpPost httpPost) throws UnsupportedEncodingException {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("e_mail", Creds.USERNAME));
		nvps.add(new BasicNameValuePair("password", Creds.PASSWORD));
		nvps.add(new BasicNameValuePair("submit_login", "Login"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));

		return httpPost;
	}

	/**
	 * only GET's the frontpage
	 * 
	 * @param httpClient
	 * @param url
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void getFrontpage(DefaultHttpClient httpClient, String url) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(url);
		httpClient.execute(httpGet);
		httpGet.releaseConnection();
	}

}
