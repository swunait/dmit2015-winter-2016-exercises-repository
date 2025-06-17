package ca.nait.dmit.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import ca.nait.dmit.domain.BMI;
import helper.JSFHelper;

// This is the "backing bean" class for "bmi.xhtml"
// Mark this class as CDI(Context Dependency Injection) managed bean class
@Named
@RequestScoped			// Instance is created for one request then discard after response is sent
//@ViewScoped			// Maintain instance until a request for another page
//@SessionScoped		// Instance is accessible from any page
//@ApplicationScoped	// One instance for entire application, all clients access same data
public class BmiController {

	private BMI bmiInstance = new BMI();
	
	public BMI getBmiInstance() {
		return bmiInstance;
	}
	public void setBmiInstance(BMI bmiInstance) {
		this.bmiInstance = bmiInstance;
	}

	// define an "event-handler" method for the commandButton
	public void calculate() {	
		// pass a message to the FacesServlet
		JSFHelper.addInfoMessage("Your bmi is " + bmiInstance.bmi());
	}
		
}
