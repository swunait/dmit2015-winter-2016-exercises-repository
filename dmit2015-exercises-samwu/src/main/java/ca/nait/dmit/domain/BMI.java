package ca.nait.dmit.domain;

/**
 * Calculates a person's body mass index (BMI) and their BMI category.
 * @author Sam Wu
 * @version 2016.01.15
 */
public class BMI {

	private int weight;
	private int height;
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Calculates the body mass index (BMI) using the weight and height of the person.
	 * The BMI of a person is calculated using the formula: <br />
	 * 	<strong>BMI = 703 * weight / (height * height)</strong>
	 * @return the body mass index (BMI) value of the person
	 */
	public double bmi() {
		return 703 * getWeight() / Math.pow( getHeight(), 2) ;
	}
	
	/**
	 * Determines the BMI category of the person using their BMI value
	 * and comparing it against the following table.
	 * 
	 * @return one of the following: underweight, normal, overweight, obese
	 */
	public String bmiCategory() {
		String category = "";
		double bmiValue = bmi();		
		if( bmiValue < 18.5 ) {
			category = "underweight";
		} else if( bmiValue < 25 ) {
			category = "normal";
		} else if( bmiValue < 30 ) {
			category = "overweight";
		} else {
			category = "obese";
		}
		return category;
	}
	
}
