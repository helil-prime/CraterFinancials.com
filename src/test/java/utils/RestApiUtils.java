package utils;

import java.util.Random;

public class RestApiUtils {
	
	// this method generate 3 digit random number
			public int randomNumber() {
				Random rand = new Random();
				int randomNum = rand.nextInt((999 - 100) + 1) + 100;
				return randomNum;
			}
			
			

}
