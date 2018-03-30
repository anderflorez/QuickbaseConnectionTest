package com.example;

import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class GetConnection {
	private String urlLink = "https://www.w3schools.com/xml/note.xml";
	private Document doc;
	
	public GetConnection() throws Exception {
		URL url = new URL(urlLink);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		
		DocumentBuilderFactory dbuildFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbuildFactory.newDocumentBuilder();
		doc = dBuilder.parse(conn.getInputStream());
		doc.normalize();
	}
	
	public Document getNormilizedDoc() {
		return this.doc;
	}
}


