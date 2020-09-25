import java.util.Random;
import java.util.ArrayList;
public class GameMain
{
  static Random random = new Random(); // random number generator for this program
  
  private static String getQuote(String[] quotes, int[] usedIndexes)
  {
	if (countOnes(usedIndexes) == quotes.length) // if we've used up all quotes ...
		return ""; // return the empty String
	
	int index = random.nextInt(quotes.length); // get a random quote index
	while (usedIndexes[index] == 1) // has this index been used before?
		index = random.nextInt(quotes.length); // if so, pick a different one
	usedIndexes[index] = 1; // show that this new index has now been used
		
	return quotes[index]; // and return the quote we just secured
  }
  
  private static int countOnes(int[] usedIndexes) // count the number of used indexes
  {
	int numUsed = 0;
	for (int nextIndex : usedIndexes)
		if (nextIndex == 1) numUsed++; // indicate if this one has been used
	
	return numUsed;
  }
  
  final static int JESUS = 0, YEESUS = 1; // whom to quote next
	
  public static void main(String[] args)
  {
    String[] jesus = IO.readAllLines("jesus.txt"); // read Jesus quotes from a file
	int[] jUsedIndexes = new int[jesus.length]; // keep track of which indexes we used
	// note: this initializes all index "used" records in this array to 0
	
    String[] yeezus = IO.readAllLines("yeezus.txt"); // read Yeezus quotes from a file
	int[] yUsedIndexes = new int[yeezus.length]; // keep track of which indexes we used
	// note: this initializes all index "used" records in this array to 0
	
	int pick; // whether to quote Jesus or Yeezus (0 or 1)
	String quote; // the next quote to print
	int jQuotes = 0, yQuotes = 0; // number of quotes generated from each
	
	for (int i = 0; i < 20; i++) // generate 20 total quotes from Jesus and/or Yeezus
	{		
		pick = random.nextInt(2); // decide whom to quote
		
		// note: it's possible in the following sections for the random object to
		// generate the same array index multiple times, so we have to prevent that
		
		if (pick == JESUS) // that's a Jesus quote
		{
			quote = getQuote(jesus, jUsedIndexes);
			if (quote.length() == 0) // if no more Jesus quotes left ...
			{
				i--; // try again (don't count this as a used quote)
				continue;
				// note: must be careful not to use up both sets of quotes,
				// otherwise this will cause an infinite for loop!
			}
			
			System.out.println("Jesus quote:");
			System.out.println(quote); // print that Jesus quote
			jQuotes++; // count that quote
		}
		else // that's a Yeezus quote
		{
			quote = getQuote(yeezus, yUsedIndexes);
			if (quote.length() == 0) // if no more Yeezus quotes left ...
			{
				i--; // try again (don't count this as a used quote)
				continue;
				// note: must be careful not to use up both sets of quotes,
				// otherwise this will cause an infinite for loop!
			}
			
			System.out.println("Yeezus quote:");
			System.out.println(quote); // print that Yeezus quote
			yQuotes++; // count that quote
		}
	}
	
	System.out.println("number of Jesus quotes: " + jQuotes);
	System.out.println("number of Yeezus quotes: " + yQuotes);
  }
}