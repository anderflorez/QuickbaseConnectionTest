package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecordData {
	
	private String urlLink = "https://unlimitedcompanies.quickbase.com/db/biih8i86s";
	private String data = "<qdbapi>\n" + 
			"   <ticket>9_bnj4hfg6y_bzzaw3_dd2i_a_-b_dvp2bspuwfmbvdsnv3ptdzurirzcyy9cpqbdmeh6tdgz4bgfc57a6i_pqadc4</ticket>\n" + 
			"   <apptoken>cg72wc4c9pbumidqkhgaibkcgh3v</apptoken>\n" + 
			"   <query>{'3'.EX.'95'}</query>\n" + 
			"   <clist>3.298.300.1.2</clist>\n" + 
			"   <fmt>structured</fmt>\n" + 
			"</qdbapi>";
	private String xmlResponse;
	
	public RecordData() throws Exception {
		
		OutputStream outStream;
		
		URL url = new URL(urlLink);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/xml");
		conn.setRequestProperty("QUICKBASE-ACTION", "API_DoQuery");
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
