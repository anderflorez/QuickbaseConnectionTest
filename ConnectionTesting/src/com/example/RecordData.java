package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecordData {
	
	private StringBuilder data;
	private String xmlResponse;
	
	public RecordData(String ticket, String apptoken, String query, String clist) throws Exception {
		
		data = new StringBuilder();
		data.append("<qdbapi><ticket>");
		data.append(ticket);
		data.append("</ticket><apptoken>");
		data.append(apptoken);
		data.append("</apptoken><query>");
		data.append(query);
		data.append("</query><clist>");
		data.append(clist);
		data.append("</clist></qdbapi>");
		
		OutputStream outStream;
		
		URL url = new URL("https://unlimitedcompanies.quickbase.com/db/biih8i86s");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/xml");
		conn.setRequestProperty("QUICKBASE-ACTION", "API_DoQuery");
		conn.setDoOutput(true);
		
		outStream = conn.getOutputStream();
		BufferedWriter outStreamW = new BufferedWriter(new OutputStreamWriter(outStream));
		outStreamW.write(data.toString());
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
