package com.demo.microserviceclient;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class QuoteClientBean {



	Client client = Client.create();
	public static String QUOTE_URL = "http://localhost:6080/quote";

	QuoteClientBean() {}
	
	
	public String getQuotesByAgentId(String agentId) {
	
		WebResource webResource = client.resource(QUOTE_URL + "/all/agent/" + agentId);
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);
	/*	if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}*/

		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
		return result;
		
		
	}

	
	public String getSmallGroupQuotesByAgentId(String agentId){
		WebResource webResource = client.resource(QUOTE_URL + "/sg/agent/" +agentId );
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);
	/*	if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}*/

		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
		return result;
	}
	
	public String getMediumGroupQuotesByAgentId(String agentId){
		WebResource webResource = client.resource(QUOTE_URL + "/med/agent/" +agentId );
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);
		/*if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}*/

		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
		return result;
	}
	
	public String getQuoteDetailsByQuoteId(String quoteId){
		WebResource webResource = client.resource(QUOTE_URL + "/" +quoteId );
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);
		/*if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}*/

		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
		return result;
	}
	
	public String getQuotesByQuoteTypeAndAgentId(String quoteType, String agentId){
		
		
		System.out.println("Received quoteType: " + quoteType);
		System.out.println("Received agentId: " + agentId);
		/*WebResource webResource = client.resource(QUOTE_URL + "/agent/" + agentId +"?type="+ quoteType );
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}

		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);*/
		return quoteType;
	}
	




	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {
		QuoteClientBean quoteClientBean = new QuoteClientBean();
		
		/*
		//Display Quote based on AgentId
		//quoteClientBean.getQuotesByAgentId("123");
		
		//Display Small Group Quote based on AgentId
		quoteClientBean.getSmallGroupQuotesByAgentId("123");
		
		//Display Medium Group Quote based on AgentId
		quoteClientBean.getMediumGroupQuotesByAgentId("123");
		*/
		
		//Display Quotes based on Quote Type and AgentId
		quoteClientBean.getQuotesByQuoteTypeAndAgentId("MediumGroup","123");
		
		//Display Quotes based on Quote Type and AgentId
		quoteClientBean.getQuotesByQuoteTypeAndAgentId("SeniorGroup","123");
		
		
		//Display Quote details based on QuoteId
		//quoteClientBean.getQuoteDetailsByQuoteId("2");
		
	}

	/*public static void displayQuotes(Collection<Quote> quoteList,String agentId) {
		
		Iterator<Quote> quoteItr=quoteList.iterator();
		
		System.out.println("Displaying Quotes for Agent:" + agentId);
		System.out.println("***BEGIN: Quote List***");		
		while(quoteItr.hasNext()){
			
				
			Quote quote=(Quote) quoteItr.next();

			System.out.println("Quote ID   is: "   + quote.getQuoteId());
			System.out.println();
			System.out.println("Quote Type is: "   + quote.getQuoteType());
			System.out.println();
			System.out.println("Quote Amount is: " + quote.getQuoteAmount());
		}
		System.out.println("***END: Quote List***");

		
	}*/
}
