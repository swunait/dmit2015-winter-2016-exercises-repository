package ca.nait.dmit.domain;

/**
 * Determines the animal associated with a person's birth year.
 * 
 * @author Sam Wu
 * @version 2016.01.15
 *
 */
public class ChineseZodiac {

	public static String animal(int birthYear) {
		String animalName = "";
		int offset = (birthYear - 1900) % 12;
		switch( offset ) {
			case 0:
				animalName = "Rat";
				break;
			case 1:
				animalName = "Ox";
				break;
			case 2:
				animalName = "Tiger";
				break;
			case 3:
				animalName = "Rabbit";
				break;
			case 4:
				animalName = "Dragon";
				break;
			case 5:
				animalName = "Snake";
				break;
			case 6:
				animalName = "Horse";
				break;
			case 7:
				animalName = "Goat";
				break;
			case 8:
				animalName = "Monkey";
				break;
			case 9:
				animalName = "Rooster";
				break;
			case 10:
				animalName = "Dog";
				break;
			case 11:
				animalName = "Pig";
				break;
				
		}
		return animalName;
	}
}
