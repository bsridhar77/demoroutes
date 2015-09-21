package com.demo.cache;

import java.io.IOException;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.demo.model.Quote;
import com.sun.jersey.api.client.Client;

public class QuoteCacheBean {

	Client client = Client.create();
	public static String QUOTE_CACHE_URL = "http://localhost:1080/quotecache";
	
	public String getQuoteDetailsByQuoteId(String quoteId , Exchange myExchange) throws JsonGenerationException, JsonMappingException, IOException{
	/*	WebResource webResource = client.resource(QUOTE_CACHE_URL + "/" +quoteId );
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);

		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
*/		
		Quote quote=new Quote();
		quote.setQuoteId(quoteId);
		quote.setQuoteAmount("5000");
		quote.setQuoteType("Hello");
		quote.setAgentId("007");
	
		ObjectMapper objMapper=new ObjectMapper();
		if(null!=quoteId && quoteId.trim().equals("1")){
			myExchange.getIn().setHeader("InCache","YES");
		}else{
			myExchange.getIn().setHeader("InCache","NO");
		}
		return objMapper.writeValueAsString(quote);
	}
	
	
	public void addQuoteByQuoteId(Quote quote) throws JsonGenerationException, JsonMappingException, IOException{

			System.out.println("Body obtained is:");
			System.out.println("QuoteID:" + quote.getQuoteId());
			System.out.println("QuoteAgentID:" + quote.getAgentId());
			System.out.println("QuoteAmount:" + quote.getQuoteAmount());
			System.out.println("Quotetype:" + quote.getQuoteType());
			
			//Pass to QuoteCache Service to add it Quote Caches
			
	}
	
}
