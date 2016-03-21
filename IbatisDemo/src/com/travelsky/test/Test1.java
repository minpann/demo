package com.travelsky.test;

import java.util.Arrays;
import java.util.List;

public class Test1 {
	
	public static void main(String[] args)
	{
		List<String> countries = Arrays.asList("France", "India", 
			"China", "USA", "Germany");

		for (String country : countries)
		{
			System.out.println("Hello " + country + "!");
		}
	}
	
}
