package com.demo.microserviceclient;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.demo.model.Agent;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class AgentClientBean {

	public final static Map<String, com.demo.model.Agent> agents = new HashMap<String, Agent>();

	Client client = Client.create();
	public static String AGENT_URL = "http://localhost:9080/agent";

	AgentClientBean() {
	}

	public String getAgentDetails(String agentId) throws JsonParseException,
			JsonMappingException, IOException {
		
		WebResource webResource = client.resource(AGENT_URL + "/"+ agentId);
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);

		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
		return result;
	}

	public String registerAgent(Agent agent) throws JsonGenerationException, JsonMappingException, IOException {
		
		//TODO Should use loggers not sysouts...
		System.out.println("**********Trying to Register the agent with the passed agent Id" );
		System.out.println("**********registerAgent: Received Agent Id:"
				+ agent.getAgentId());
		System.out.println("**********registerAgent: Received Agent Name:"
				+ agent.getAgentName());
		
		WebResource webResource = client
				   .resource(AGENT_URL);
		
		org.codehaus.jackson.map.ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String inputString = ow.writeValueAsString(agent);
	
		ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class, inputString);
		
		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
		return result;
		
	}

	
	public void unregisterAgent(String agentId) throws JsonGenerationException, JsonMappingException, IOException {
		
		//TODO Should use loggers not sysouts...
		
		
		System.out.println("**********Trying to UN-Register the agent with the passed agent Id" );
		
		System.out.println("**********unregisterAgent: Received Agent Id:"	+ agentId);
		
		if(null!=agentId){
			//agents.remove(agentId);
			
			WebResource webResource = client
					   .resource(AGENT_URL + "/unregister");
			
			
			Agent agent=new Agent(agentId,null);
			org.codehaus.jackson.map.ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String inputString = ow.writeValueAsString(agent);
			System.out.println("**********inputString "  + inputString);
			ClientResponse response = webResource
			   .delete(ClientResponse.class, inputString);
			
			System.out.println("**********Successfully UNRegistered the AGent with ID: "  + agentId);
			
		}else{
			System.out.println("**********Failed to unregister the Agent with ID: "  + agentId);
		}

	}
	public String listAgents() {
		
		
		Client client = Client.create();

		WebResource webResource = client.resource(AGENT_URL);

		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

	
		String output = response.getEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
		return output;
	}

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {
		AgentClientBean agentClientBean = new AgentClientBean();
		
		//List All Agents
		System.out.println(agentClientBean.listAgents());
		
		//Get Details on specific Agent
		agentClientBean.getAgentDetails("999");
		
		//Register a New Agent
		agentClientBean.registerAgent(new Agent("999","JFK"));
		
		
		
	}

	private static void displayAgents(Collection<Agent> agentList) {
		
		Iterator<Agent> agentItr=agentList.iterator();
		
		while(agentItr.hasNext()){
			System.out.println("***BEGIN: Agent List***");			
			Agent agent=(Agent) agentItr.next();

			System.out.println("Agent ID   is: " + agent.getAgentId());
			System.out.println();
			System.out.println("Agent Name is: " + agent.getAgentName());
		}
		System.out.println("***END: Agent List***");

		
	}
}
