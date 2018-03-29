package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class QuickbaseAuth {
	
	private String urlLink = "https://unlimitedcompanies.quickbase.com/db/main";
	private String data = "<qdbapi><username>uec_ops_support@unlimitedcompanies.com</username><password>accountPassword</password></qdbapi>";
	private String xmlResponse;
	
	public QuickbaseAuth() throws Exception {
		
		OutputStream outStream;
		
		URL url = new URL(urlLink);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/xml");
		conn.setRequestProperty("QUICKBASE-ACTION", "API_Authenticate");
		conn.setRequestProperty("usertoken", "bzzaw3_dd2i_dhgcgfadikek3k9h3eh7dmi45z");
		conn.setDoOutput(true);
		
		outStream = conn.getOutputStream();
		BufferedWriter outStreamW = new BufferedWriter(new OutputStreamWriter(outStream));
		outStreamW.write(data);
		outStreamW.flush();
		outStreamW.close();
		
		StringBuffer sb = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		
		this.xmlResponse = sb.toString();
	}
	
	public String getResponse() {
		return this.xmlResponse;
	}
}
