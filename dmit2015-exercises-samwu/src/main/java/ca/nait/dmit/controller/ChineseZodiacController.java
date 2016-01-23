package ca.nait.dmit.controller;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ca.nait.dmit.domain.ChineseZodiac;
import helper.JSFHelper;

@Named			// this is a CDI managed bean
//@RequestScoped	// store the managed for one HTTP request
@ViewScoped
public class ChineseZodiacController implements java.io.Serializable {
	
	private static final Logger logger = Logger.getLogger(
			ChineseZodiacController.class.getName());
	
	@PostConstruct
	public void init() {
		logger.log(Level.INFO, "created instance of ChineseZodiacController");
		logger.log(Level.INFO, "birthYear = {0}, animalImageUrl = {1}",
				new Object[] {birthYear, animalImageUrl});
	}
	
	@PreDestroy
	public void cleanUp() {
		logger.log(Level.INFO, "destroyed instance of ChineseZodiacController");
	}
	

	private int birthYear = Calendar.getInstance().get(Calendar.YEAR);
	private String animalImageUrl = "/resources/images/chinese_zodiac.jpg";
	
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public String getAnimalImageUrl() {
		return animalImageUrl;
	}
	public void setAnimalImageUrl(String animalImageUrl) {
		this.animalImageUrl = animalImageUrl;
	}
	
	public void findAnimal() {
		String animalName = ChineseZodiac.animal(birthYear);
		String message = String.format("%d is the year of the %s", 
				birthYear, animalName);
		JSFHelper.addInfoMessage(message);
		animalImageUrl = String.format("/resources/images/zodiac_%s.jpg", 
				animalName.toLowerCase());
		logger.log(Level.INFO, "birthYear = {0}, animalImageUrl = {1}",
				new Object[] {birthYear, animalImageUrl});
		
	}			
	
}



