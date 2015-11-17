package com.demo.microserviceclient;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class QuoteClientBean {



	Client client = Client.create();
	public static String QUOTE_URL = "http://localhost:1080/quote";

	QuoteClientBean() {}
	
	
	/*public String getQuotesByAgentId(String agentId) {
	
		WebResource webResource = client.resource(QUOTE_URL + "/all/agent/" + agentId);
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}

		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
		return result;
		
		
	}

	
	public String getSmallGroupQuotesByAgentId(String agentId){
		WebResource webResource = client.resource(QUOTE_URL + "/sg/agent/" +agentId );
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}

		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
		return result;
	}
	
	public String getMediumGroupQuotesByAgentId(String agentId){
		WebResource webResource = client.resource(QUOTE_URL + "/med/agent/" +agentId );
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}

		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
		return result;
	}*/
	
	public String getQuoteClientsByQuoteReferenceNumber(String quoteId){
		
		String url=QUOTE_URL + "/" +quoteId;
		
		System.out.println("Quote URL is:" + url);
		
		WebResource webResource = client.resource(url);
		
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);

		String result = response.getEntity(String.class);
		
		//return result;
		String processedResult=processJSONResult(result);
		System.out.println(processedResult);
		return processJSONResult(processedResult);
		//System.out.println("Quote Response from the Server: ");
		//System.out.println(result);
		//return result;
	}
	
	
	
	/*public String getQuotesByQuoteTypeAndAgentId(String quoteType, String agentId){
		
		
		System.out.println("Received quoteType: " + quoteType);
		System.out.println("Received agentId: " + agentId);
		WebResource webResource = client.resource(QUOTE_URL + "/agent/" + agentId +"?type="+ quoteType );
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}

		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
		return result;
	}*/
	




	private String processJSONResult(String result) {
			ObjectNode node;
			String res=null;
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				node = (ObjectNode) new ObjectMapper().readTree(result);
				JsonNode productRatingNode=node.get("productRating");
				if(null!=productRatingNode && productRatingNode.isArray()){
					for (JsonNode element : productRatingNode) {
						String contractCode = element.path("contractCode").asText();
						String planCode = element.path("planCode").asText();
						String productCode = element.path("productCode").asText();
							
					/*	System.out.println("contractCode : " + contractCode);
						System.out.println("planCode : " + planCode);
						System.out.println("productCode : " + productCode);*/
						
						PlanClientBean planClientBean = new PlanClientBean();
						String planResponse=planClientBean.getPlanDetailsByPlanNumber(contractCode+"/"+productCode+"/" + planCode);
						
						//((ObjectNode) element).put("", planResponse);//getPlanBenefits(planResponse));
						
						ObjectNode productBenefitNode = mapper.createObjectNode();
						productBenefitNode.putObject(planResponse);
/*						productBenefitNode.put("planproductId","1");
						productBenefitNode.put("planid","2");
						productBenefitNode.put("contractCode","contractCode001");
*/						//productBenefitNode.set("productBenefit",productBenefitNode);
						((ObjectNode) element).set("productBenefit", productBenefitNode);
						//System.out.println("Plan Details for input:" + planClientBean.getPlanDetailsByPlanNumber(contractCode+"/"+productCode+"/" + planCode));
	
					}
					
				
					res=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
					
					}
/*				Iterator<?> itr=productRatingNode.iterator();
				while(itr.hasNext()){
					itr.next();
				}
*/				//System.out.println(productRatingNode);
				/*ArrayNode arrayNode = node.putArray("new array");
				arrayNode.add(true).add(1).add("new value"); // chain add calls
				arrayNode.addObject().put("nestedInArray", "nested object value"); // add an object and add to it
				System.out.println(node);*/
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return res;
	}


	/*private JsonNode getPlanBenefits(String planResponse) {
		ObjectNode node;
		JsonNode planBenefitsNode = null;
		try {
			node = (ObjectNode) new ObjectMapper().readTree(planResponse);
			planBenefitsNode=node.get("planBenefit");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return planBenefitsNode;
	}*/


	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {
		QuoteClientBean quoteClientBean = new QuoteClientBean();
		
		System.out.println("Final Response is:"+ quoteClientBean.getQuoteClientsByQuoteReferenceNumber("9"));
		
	}

	
}
