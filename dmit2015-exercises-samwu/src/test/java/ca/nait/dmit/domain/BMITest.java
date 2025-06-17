package ca.nait.dmit.domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BMITest {

	@Test
	public void testUnderweight() {
		BMI bmiBean = new BMI();
		bmiBean.setWeight(100);
		bmiBean.setHeight(66);
		assertEquals(16.1, bmiBean.bmi(), 0.05 );
		assertEquals("underweight", bmiBean.bmiCategory() );
	}	

	@Test
	public void testNormal() {
		BMI bmiBean = new BMI();
		bmiBean.setWeight(140);
		bmiBean.setHeight(66);
		assertEquals(22.6, bmiBean.bmi(), 0.05 );
		assertEquals("normal", bmiBean.bmiCategory() );
	}

	@Test
	public void testOverweight() {
		BMI bmiBean = new BMI();
		bmiBean.setWeight(175);
		bmiBean.setHeight(66);
		assertEquals(28.2, bmiBean.bmi(), 0.05 );
		assertEquals("overweight", bmiBean.bmiCategory() );
	}
	
	@Test
	public void testObese() {
		BMI bmiBean = new BMI();
		bmiBean.setWeight(200);
		bmiBean.setHeight(66);
		assertEquals(32.3, bmiBean.bmi(), 0.05 );
		assertEquals("obese", bmiBean.bmiCategory() );
	}


}
