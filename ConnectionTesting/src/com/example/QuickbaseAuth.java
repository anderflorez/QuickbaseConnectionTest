package com.example;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class QuickbaseAuth {

	private StringBuilder data;
	private String userToken;
	private String ticket;
	private String userId;
	
	public QuickbaseAuth(String username, String password, String userToken) throws Exception {
		
		this.userToken = userToken;
		data = new StringBuilder();
		data.append("<qdbapi><username>");
		data.append(username);
		data.append("</username><password>");
		data.append(password);
		data.append("</password></qdbapi>");
		
		OutputStream outStream;
		
		URL url = new URL("https://unlimitedcompanies.quickbase.com/db/main");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/xml");
		conn.setRequestProperty("QUICKBASE-ACTION", "API_Authenticate");
		conn.setRequestProperty("usertoken", this.userToken);
		conn.setDoOutput(true);
		
		outStream = conn.getOutputStream();
		BufferedWriter outStreamW = new BufferedWriter(new OutputStreamWriter(outStream));
		outStreamW.write(data.toString());
		outStreamW.flush();
		outStreamW.close();
		
		DocumentBuilderFactory dBuildFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dBuildFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(conn.getInputStream());
		doc.normalize();
		
		if (doc.getElementsByTagName("errcode").item(0).getTextContent().equals("0")) {
			ticket = doc.getElementsByTagName("ticket").item(0).getTextContent();
			userId = doc.getElementsByTagName("userid").item(0).getTextContent();
		}
		
	}
	
	public String getTicket() {
		return this.ticket;
	}
	
	public String getUserId() {
		return this.userId;
	}
}
