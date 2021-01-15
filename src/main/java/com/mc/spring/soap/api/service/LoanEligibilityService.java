package com.mc.spring.soap.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mc.spring.soap.api.loaneligibility.Acknowledgement;
import com.mc.spring.soap.api.loaneligibility.CustomerRequest;


@Service
public class LoanEligibilityService {

	 
	public Acknowledgement checkLoanEligibility(CustomerRequest request) {
		
		Acknowledgement acknowledgement = new Acknowledgement();
		List<String> mismatchCriteriaList=acknowledgement.getCriteriaMismatch();		
		
		if(!(request.getAge()>25 && request.getAge()<=60)) {
			mismatchCriteriaList.add("Person age should be in between 25 and 60");
		}
		if(!(request.getYearlyIncome()>60000)) {
			mismatchCriteriaList.add("minimum income should be more than 60000 USD");
		}
		if(!(request.getCreditScore() > 500)) {
			mismatchCriteriaList.add("Low Credit Score, please try after 6 months");
		}
		
		if(mismatchCriteriaList.size()>0) {
			acknowledgement.setApprovedAmount(0);;
			acknowledgement.setIsEligible(false);
		}
		else {
			acknowledgement.setApprovedAmount(500000);
			acknowledgement.setIsEligible(true);
			mismatchCriteriaList.clear();
		}
		return acknowledgement;
	}
	
	
}
