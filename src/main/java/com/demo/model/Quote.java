/* test*/
package com.demo.model;

public class Quote {

	
	String quoteId;
	
	String quoteType;
	
	String quoteAmount;
	

	String agentId;
	
	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public Quote(){
		
	}

	public Quote(String quoteId,String quoteType, String quoteAmount,String agentId){
		
		this.quoteId=quoteId;
		this.quoteType=quoteType;
		this.quoteAmount=quoteAmount;
		this.agentId=agentId;
	}

	public String getQuoteId() {
		return quoteId;
	}


	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}


	public String getQuoteType() {
		return quoteType;
	}


	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}


	public String getQuoteAmount() {
		return quoteAmount;
	}


	public void setQuoteAmount(String quoteAmount) {
		this.quoteAmount = quoteAmount;
	}
	
	
}
