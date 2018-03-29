package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostConnection {

	private String urlLink = "http://httpbin.org/post";
	private String xmlResponse;
	
	public PostConnection() throws Exception {
		
		URL url = new URL(urlLink);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line;
		
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		in.close();
		this.xmlResponse = sb.toString();
	}
	
	public String getResponse() {
		return xmlResponse;
	}
}
