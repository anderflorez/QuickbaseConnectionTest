package com.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {

	public static void main(String[] args) {
		
		// Testing the Get Request
		try {
			GetConnection connGet = new GetConnection();
			Document doc = connGet.getNormilizedDoc();
			System.out.println("This is the GET request response: ");
			System.out.println("Root element: " + connGet.getNormilizedDoc().getDocumentElement().getNodeName());
			NodeList nlist = doc.getElementsByTagName("note");
		
			System.out.println("Testing element: " + connGet.getNormilizedDoc().getElementsByTagName("heading").item(0).getTextContent());
			
			System.out.println("---------------------------------");
			
			for (int i = 0; i < nlist.getLength(); i++) {
				Node nNode = nlist.item(i);
				Element element;
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					element = (Element) nNode;
					System.out.println("to: " + element.getElementsByTagName("to").item(0).getTextContent());
					System.out.println("from: " + element.getElementsByTagName("from").item(0).getTextContent());
					System.out.println("heading: " + element.getElementsByTagName("heading").item(0).getTextContent());
					System.out.println("body: " + element.getElementsByTagName("body").item(0).getTextContent());
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		//Testing the POST Request
		try {
			PostConnection connPost = new PostConnection();
			System.out.println("This is the POST request response: ");
			System.out.println(connPost.getResponse() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// Testing an authentication POST Quickbase Request
		System.out.println();
		System.out.println();
		System.out.println("=========================================");
		QuickbaseAuth qbconn = null;
		try {
			String username = "uec_ops_support@unlimitedcompanies.com";
			String password = "Unlimited3812##";
			String usertoken = "bzzaw3_dd2i_dhgcgfadikek3k9h3eh7dmi45z";
			qbconn = new QuickbaseAuth(username, password, usertoken);
			System.out.println("This is the POST request response from Quickbase: ");
			System.out.println("This is the obtained ticket: " + qbconn.getTicket());
			System.out.println("This is the obtained user id: " + qbconn.getUserId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Testing a DoQuery POST Quickbase Request
		System.out.println();
		System.out.println();
		System.out.println("=========================================");
		try {
			RecordData recData = new RecordData(qbconn.getTicket(), "cg72wc4c9pbumidqkhgaibkcgh3v", "{'3'.EX.'95'}", "3.298.300.1.2");
			System.out.println("This is the POST request response from Quickbase for RecordData: ");
			System.out.println(recData.getResponse() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
