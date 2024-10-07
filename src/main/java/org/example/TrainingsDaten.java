package org.example;

import java.net.URL;

public class TrainingsDaten {
	private String wort;
	private URL url;

	public TrainingsDaten(String wort, URL url) {
		if (wort == null || url == null) {
			throw new IllegalArgumentException("Wort und URL dürfen nicht null sein");
		}
		this.wort = wort;
		this.url = url;
	}

	public String getWort() {
		return wort;
	}

	public URL getUrl() {
		return url;
	}
}
