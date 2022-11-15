import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
/**
 * A program to carry on conversations with a human user.
 * This version:
 *<ul><li>
 * 		Uses advanced search for keywords 
 *</li><li>
 * 		Will transform statements as well as react to keywords
 *</li></ul>
 * @author Laurie White
 * @version April 2012
 *
 */

public class Magpie4
{
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hello, What's your favorite food?";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
  /*
	public String getResponse(String statement)
	{
    
		String response = "";
		if (statement.length() == 0)
		{
			response = "Say something about food, please. I am hungry";
		}
    else if(findKeyword(statement, "yea") >= 0){
      response = "Is there anything else you want to talk about?";
    }
     else if(findKeyword(statement, "You don't say.") >= 0){
      response = "Is there anything else you want to talk about?";
    }
    else if(findKeyword(statement, "Lets talk more about that.") >= 0)
    {
      response = "I don't want to";
    }
    

		else if (findKeyword(statement, "no") >= 0 
    || findKeyword(statement, "hate") >= 0
    || findKeyword(statement, "dislike") >= 0)
		{
			response = "Why so negative?";
    
		}
    else if (findKeyword(statement, "I don't think so") >= 0)      {
      response = "Are you sure?";
    }
    else if (findKeyword(statement, "I don't know") >= 0)           {  
      response = "Me neither";
    } 
    else if (findKeyword(statement, "Great!") >= 0)           {  
      response = "What is there to be excited about?";
    } 
  else if(findKeyword(statement, "What do you mean by that?") >=0){
      response = "I meant what I said";
    }
    else if(findKeyword(statement, "ice cream") >= 0
        || findKeyword(statement, "pizza") >= 0
				|| findKeyword(statement, "burgers") >= 0 
        || findKeyword(statement, "tacos") >= 0 
				|| findKeyword(statement, "burritos") >= 0
        || findKeyword(statement, "french fries") >= 0
        || findKeyword(statement, "churros") >= 0
        || findKeyword(statement, "sushi") >= 0
        || findKeyword(statement, "rice") >= 0
        || findKeyword(statement, "curry") >= 0) 
        {
          response = "I love that. What other foods do you like?";
        }
  
    else if(findKeyword(statement, "Hello") >= 0){
      response = "Hi";
    }
    else if(findKeyword(statement, "Thanks") >=0) {
      response = "Your welcome";
    }
    else if(findKeyword(statement, "how many") >=0) {
      response = "I am not good with counting";
    }
    
		else if (findKeyword(statement, "mother") >= 0
				|| findKeyword(statement, "father") >= 0
				|| findKeyword(statement, "sister") >= 0 
        || findKeyword(statement, "sisters") >= 0 
				|| findKeyword(statement, "brother") >= 0
        || findKeyword(statement, "brothers") >= 0
        || findKeyword(statement, "son") >= 0
        || findKeyword(statement, "sons") >= 0
        || findKeyword(statement, "niece") >= 0
        || findKeyword(statement, "nieces") >= 0
        || findKeyword(statement, "nephew") >= 0 
        || findKeyword(statement, "nephews") >= 0 
        || findKeyword(statement, "cousin") >= 0 
        || findKeyword(statement, "cousins") >= 0 
        || findKeyword(statement, "daughters") >= 0 
        || findKeyword(statement, "daughter") >= 0)
		{
			response = "Tell me more about your family.";
		}
    else if(findKeyword(statement, "cat")>=0 
            ||findKeyword(statement, "cats")>=0
            ||findKeyword(statement,"dogs")>=0
        || findKeyword(statement, "dog") >=0) 
    {
      response = "Tell me more about your pets.";
    }
    
    else if(findKeyword(statement, "Mr")>=0
           ||findKeyword(statement,"Mrs")>=0
           ||findKeyword(statement, "Ms")>=0) 
    {
      response = "Sounds like a good teacher"; 
    }  

		// Responses which require transformations
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
    
    else if (findKeyword(statement, "What is your favorite food", 0) >=0 
      ||findKeyword(statement,"How about you?")>=0 
      || findKeyword(statement, "What about you?") >=0 )
		{
			response = "I like many foods";
		}
    else if(findKeyword(statement, "I like")>=0)
    {
      response = "I like that too";
    }
    else if(findKeyword(statement, "why")>=0)
    {
      response = "can you rephrase that?";
    }
    
		else
		{
			// Look for a two word (you <something> me)
			// pattern
			int psn = findKeyword(statement, "you", 0);

			if (psn >= 0
					&& findKeyword(statement, "me", psn) >= 0)
			{
				response = transformYouMeStatement(statement);
			}
			else
			{
				response = getRandomResponse();
			}
		}
		return response;
	}
	
	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "What would it mean to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
 
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Why do you want to eat " + restOfStatement + "?";
    
	}

	
	
	/**
	 * Take a statement with "you <something> me" and transform it into 
	 * "What makes you think that I <something> you?"
	 * @param statement the user statement, assumed to contain "you" followed by "me"
	 * @return the transformed statement
	 */
	private String transformYouMeStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfYou = findKeyword (statement, "you", 0);
		int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
		
		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}
	
	

	
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @param startPos the character of the string to begin the search at
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal, int startPos)
	{
		String phrase = statement.trim();
		//  The only change to incorporate the startPos is in the line below
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
		
		//  Refinement--make sure the goal isn't part of a word 
		while (psn >= 0) 
		{
			//  Find the string of length 1 before and after the word
			String before = " ", after = " "; 
			if (psn > 0)
			{
				before = phrase.substring (psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
			}
			
			//  If before and after aren't letters, we've found the word
			if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
					&& ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
			{
				return psn;
			}
			
			//  The last position didn't work, so let's find the next, if there is one.
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
			
		}
		
		return -1;
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		final int NUMBER_OF_RESPONSES = 4;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";
		
		if (whichResponse == 0)
		{
			response = "Interesting, tell me more about you.";
		}
		else if (whichResponse == 1)
		{
			response = "Hmmm.sounds good";
		}
		else if (whichResponse == 2)
		{
			response = "I see";
		}
		else if (whichResponse == 3)
		{
			response = "ok";
		}

		return response;
	}



  int greeting = 1; 
  int casual_convo = 2;
  int meals = 3; 
  private int parseInput(String statement){
    if(findKeyword(statement,"Hello") >=0){
      return greeting; 
    }
    else if (findKeyword(statement, "ice cream") >= 0
        || findKeyword(statement, "pizza") >= 0
				|| findKeyword(statement, "burgers") >= 0 
        || findKeyword(statement, "tacos") >= 0 
				|| findKeyword(statement, "burritos") >= 0
        || findKeyword(statement, "french fries") >= 0
        || findKeyword(statement, "churros") >= 0
        || findKeyword(statement, "sushi") >= 0
        || findKeyword(statement, "rice") >= 0
        || findKeyword(statement, "curry") >= 0){
      return casual_convo;
    }
    else if(findKeyword(statement, "favorite foods") >=0) {
      return meals;
    }
    return 0; 
  }
private void setState(String statement){
  	//reset all values.  Your state logic may vary
   	String response = ""; 
    int newState = parseInput(statement); 
  
   	switch(newState) {
      case 1:
       	response = "Hello, Nice to meet you";
       	break;
      case 2:
        response = "Tell me more about food";
       	break;
      case 3:
       	response ="Would you like some meal recommendations?";
       	break;
     	default:
     	  break; 
    }
    return response;
}   

}  
