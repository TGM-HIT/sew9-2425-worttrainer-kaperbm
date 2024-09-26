package org.example;

public class TrainingsDaten {

	private String wort;

	private String url;

	public TrainingsDaten(String wort, String url) {

	}

	public String getUrl() {
		return url;
	}

	public String getWort() {
		return wort;
	}

	public boolean checkValid() {
		if(this.url== null) {
			return false;
		}
		if (!(this.url.startsWith("http://") || this.url.startsWith("https://"))) {
			return false;
		}
		return true;
	}

}
