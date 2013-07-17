package ch.longstone.pm.network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

/**
 * Keeps the session alive
 * 
 * @author stone
 * 
 */
public class SessionManager {
	PowerMatrixConnection con;
	KeepAliveThread keepalive = null;

	public SessionManager(PowerMatrixConnection con) {
		this.con = con;
	}

	public void start() {
		if (keepalive != null) {
			throw new IllegalStateException("Shutdown not clean, or never happened");
		}
		keepalive = new KeepAliveThread(con);
		keepalive.start();
	}
	public void stop(){
		keepalive.shutdown();
		keepalive = null;
	}
}

class KeepAliveThread extends Thread {
	private PowerMatrixConnection con;
	private volatile boolean stop;

	public KeepAliveThread(PowerMatrixConnection con) {
		super("SessionManager.KeepAlive");
		this.con = con;
	}

	@Override
	public void run() {
		while (!stop)
			try {
				con.getJSON("http://powermatrixgame.com/worldquest/open");
				Thread.sleep(10000);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

	}

	public void shutdown() {
		stop = true;
	}
}
