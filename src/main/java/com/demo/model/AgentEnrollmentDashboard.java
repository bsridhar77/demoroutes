package com.demo.model;


import java.util.List;

public class AgentEnrollmentDashboard {

	

	public List<Enrollment> getEnrollmentList() {
		return enrollmentList;
	}


	public void setEnrollmentList(List<Enrollment> enrollmentList) {
		this.enrollmentList = enrollmentList;
	}


	Agent agent;
	
	public Agent getAgent() {
		return agent;
	}


	public void setAgent(Agent agent) {
		this.agent = agent;
	}


	List<Enrollment> enrollmentList;
	

	public AgentEnrollmentDashboard(){
		
	}
	
	
}
