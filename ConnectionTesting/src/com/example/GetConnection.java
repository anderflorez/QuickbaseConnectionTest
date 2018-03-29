package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetConnection {
	private String urlLink = "https://www.w3schools.com/xml/note.xml";
	private String xmlResponse;
	
	public GetConnection() throws Exception {
		URL url = new URL(urlLink);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		
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
