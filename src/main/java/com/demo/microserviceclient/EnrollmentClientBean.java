package com.demo.microserviceclient;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.demo.model.Enrollment;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class EnrollmentClientBean {

	public final static Map<String, Enrollment> enrollments = new TreeMap<String, Enrollment>();

	Client client = Client.create();
	public static String ENROLLMENT_URL = "http://localhost:5080/enrollment";

	EnrollmentClientBean() {
		/*//For Test
		enrollments.put("123", new Enrollment("1", "Senior","1000"));
		enrollments.put("456", new Enrollment("2", "SmallGroup","1000"));
		enrollments.put("789", new Enrollment("3", "MediumGroup","1000"));*/
	}

	public String getEnrollmentsByAgentId(String agentId)  {
		
		WebResource webResource = client.resource(ENROLLMENT_URL + "/all/agent/" + agentId);
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

	public String getEnrollmentDetailsByEnrollmentId(String enrollmentId) {
		WebResource webResource = client.resource(ENROLLMENT_URL + "/" +enrollmentId );
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

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {
		EnrollmentClientBean enrollmentclientBean = new EnrollmentClientBean();
		
		//Display Enrollment based on AgentId
		enrollmentclientBean.getEnrollmentsByAgentId("123");
		
		
		//Display Enrollment based on AgentId
		/*displayEnrollments(enrollmentclientBean.getEnrollmentsByAgentId("33"),"33");*/
		
	}

public static void displayEnrollments(Collection<Enrollment> enrollmentList,String agentId) {
		
		Iterator<Enrollment> enrollmentItr=enrollmentList.iterator();
		
		System.out.println("Displaying Enrollments for Agent:" + agentId);
		
		System.out.println("***BEGIN: Enrollment List***");		
		while(enrollmentItr.hasNext()){
			
					
			Enrollment enrollment=(Enrollment) enrollmentItr.next();

			System.out.println("Enrollment ID   is: "   + enrollment.getEnrollmentId());
			System.out.println();
			System.out.println("Enrollment Type is: "   + enrollment.getEnrollmentType());
			System.out.println();
			System.out.println("Enrollment Amount is: " + enrollment.getEnrollmentAmount());
			
			
		}
		System.out.println("***END: Enrollment List***");

		
	}
}
