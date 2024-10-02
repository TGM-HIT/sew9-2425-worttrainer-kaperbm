package org.example;

import java.net.MalformedURLException;
import java.net.URL;

public class TrainingsDaten {
	private String wort;
	private URL url;

	public TrainingsDaten(String wort, URL url) {
		this.wort = wort;
		this.url = url;
	}

	public String getWort() {
		return wort;
	}

	public URL getUrl() {
		return url;
	}

	public boolean checkValid() {
		return url != null && (url.toString().startsWith("http://") || url.toString().startsWith("https://"));
	}
}
