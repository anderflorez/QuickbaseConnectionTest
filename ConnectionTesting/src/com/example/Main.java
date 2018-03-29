package com.example;

public class Main {

	public static void main(String[] args) {
		
		// Testing the Get Request
		try {
			GetConnection connGet = new GetConnection();
			System.out.println("This is the GET request response: ");
			System.out.println(connGet.getResponse() + "\n");
			
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
		try {
			QuickbaseAuth qbconn = new QuickbaseAuth();
			System.out.println("This is the POST request response from Quickbase: ");
			System.out.println(qbconn.getResponse() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			RecordData recData = new RecordData();
			System.out.println("This is the POST request response from Quickbase for RecordData: ");
			System.out.println(recData.getResponse() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
