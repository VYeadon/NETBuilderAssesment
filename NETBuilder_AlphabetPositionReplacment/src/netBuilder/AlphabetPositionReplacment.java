package netBuilder;

import java.util.stream.Collectors;

public class AlphabetPositionReplacment {

	public static void main(String[] args) {
		
		AlphabetPositionReplacment obj = new AlphabetPositionReplacment();
		
		String word = obj.replaceLetterWithPosition("This NETbuilder assessment is way too easy.");
		
		System.out.println(word);
	}
	
	public String replaceLetterWithPosition(String string) {
		
		// converts string into an IntStream of chars which represent the ascci code
		// filters out any ascii codes that arent within the range of lower case letters
		// maptoObj converts from IntStream to Stream<integer> which can then be collected
		// to string converts the list into a string
		return
		string.toLowerCase().chars()
							.filter(text -> text>60 & text<123)
							.mapToObj(text -> text-96)
							.collect(Collectors.toList())
							.toString();

	}

}
