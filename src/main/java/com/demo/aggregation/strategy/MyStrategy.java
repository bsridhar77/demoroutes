package com.demo.aggregation.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;


public class MyStrategy implements AggregationStrategy {

	 
	    public Exchange aggregate(Exchange exchange1, Exchange exchange2) {
	    	System.out.println("Inside aggregate method in MyStrategy");
	        if (exchange1 == null) {
	            return exchange2;
	        } else {
	            String body1 = exchange1.getIn().getBody(String.class);
	            System.out.println();
	            System.out.println("body1:" + body1);
	            String body2 = exchange2.getIn().getBody(String.class);
	            System.out.println();
	            System.out.println("body2:" + body2);
	            String merged = (body1 == null) ? body2 : body1 + "," + body2;
	            System.out.println();
	            System.out.println("merged:" + merged);
	            exchange1.getIn().setBody("[" + merged + "]");
	            return exchange1;
	        }
	    }
	 
	}
	

