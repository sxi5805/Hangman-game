/**
 * Hangman.java
 * 
 * Version			:		1.0
 */

import java.io.* ;
import java.util.* ;

/**
 * The class initiates game of hangman between users entered via command line.
 * A random word is chosen from text file and user should guess that word by guessing letters.
 * Correct guess adds 10 points and wrong guess deducts 5 points.
 * Winner is displayed at the end.
 * 
 * @author 			:		Swetha Kannan Iyer
 * @author			: 		Siddharth Bidwalkar
 */

public class Hangman 
{

/**
 * Main program
 * @param 			:		args		Names of users playing the game
 * @throws 			:		Exception
 */
	public static void main(String[] args) throws Exception
	{

// Create object of class hangman
		Hangman h = new Hangman() ;
	
		int n , len , i , succ , wrong , j , k , nolet , l;
		String ip ;

// Get length of array of command line arguements
		n = args.length ;
	
// Initialize and add player names in the array
		String[] names = new String[n] ;
		for(i = 0 ; i < n-1 ; i ++)
		{
			names[i] = args[i+1] ;
		}
		n = n - 1 ;

// Initialize the array to store points scored by the users
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int[] users = new int[n] ;
		for( i = 0 ; i < n ; i ++)
			users[i] = 0 ;
		
		for(i = 0 ; i < n ; i ++)
		{

// For every user, get random word from the file from getWord function
			String word = h.getWord(args[0]) ;
			len = word.length() ;

// Declare two arrays of the word length and add characters of the word into alph array using split
			String[] alph = new String[len] ;
			String[] alph1 = new String[len] ;
			alph = word.split("",-1) ;

// Initialize values of alph1 array with blank.l 
			for(l = 0 ; l < len ; l ++)
				alph1[l] = "" ;

// Player turn starts. Set wrong turns equal to zero.
			System.out.println(names[i]+" turn. Guess the "+(len)+" letter word") ;
			succ = 0 ;
			wrong = 0 ;
			while(succ == 0)
			{

// Display the letters in user guessed words and blank letters as _ .
				for(j = 0 ; j < len ; j ++)
				{
					if(alph1[j].equals(""))
						System.out.print("_ ");
					else
						System.out.print(alph1[j]+" ");
				}
				System.out.println() ;
				
// Ask user to enter a letter
				System.out.println("Enter a letter") ;
				ip = br.readLine() ;
				
// Check if letter entered by user is present in the word by 
// comparing letter with elements of alph array. If letter is found, add that letter 
// in appropriate position of alph1 array.
				nolet = 0 ;
				for(k = 0 ; k < len ; k ++)
				{
					if(alph[k].equals(ip))
					{
						if(alph1[k].equals(""))
						{
							alph1[k] = alph[k] ;
							users[i] = users[i] + 10 ;
						}
					}
					else
						nolet = nolet + 1 ;
				}
// If letter is not found in the word, then the user loses one wrong chance.
				if(nolet == len)
				{
					wrong = wrong + 1 ;
					users[i] = users[i] - 5 ;
					System.out.println("SORRY... wrong letter. "+(8-wrong)+" wrong guesses left") ;
				}
				
// User is allowed 8 wrong chances. If the chances are over, then game is over.
				if(wrong == 8)
				{
					System.out.println("You have exhausted your wrong turns") ;
					succ = 1 ;
				}

// If there are no blanks left in alph1 array, that means user has guessed the word correctly.
				else
				{
					nolet = 0 ;
					for(k = 0 ; k < len ; k ++)
					{
						if(alph1[k].equals(""))
							nolet = nolet + 1 ;
					}
					if(nolet == 0)
					{
						System.out.println("CONGRATULATIONS !! You have guessed the word correctly") ;
						succ = 1 ;
					}
				}
			}
// Display the word to the user at end of the game
			System.out.println("The word was "+word) ;
		}
		
// Display the  name of users alongwith their points. Find the maximum points and declare that user
// as winner. IF two users have same points, then game is tied.
		nolet = 0 ;
		succ = 0 ;
		for(i = 0 ; i < n ; i ++)
		{
			System.out.println(names[i]+" : "+users[i]) ;
			if(succ < users[i])
			{
				succ = users[i] ;
				nolet = 0 ;
			}
			else if(succ == users[i] && succ != 0)
				nolet = nolet + 1 ;
		}
		
		if(nolet > 0)
			System.out.println("Game is tied") ;
		else
		{
			for(i = 0 ; i < n ; i ++)
			{
				if(succ == users[i])
				{
					System.out.println(names[i]+" wins the game !!") ;
					break ;
				}
			}
		}
	}

/**
 * The function reads a file from the system and returns a randomly selected word
 * 
 * @param 			:			file		File name which user passes to the program
 * @return			:			word		random selected word
 * @throws 			Exception
 */
	String getWord(String file) throws Exception
	{
		String word = "" ; 
		int i , lines = 0 , index = 0 ;
		
// Read file from user using bufferedreader.
		BufferedReader br = new BufferedReader(new FileReader(file)) ;
		BufferedReader br1 = new BufferedReader(new FileReader(file)) ;
		
// Count the number of lines in the file
		while(br.readLine() != null)
		{
			lines = lines + 1 ;
		}
		
// Add the words in the file in wrds array
		String[] wrds = new String[lines] ;
		for(i = 0 ; i < lines ; i ++)
		{
			wrds[i] = br1.readLine() ;
		}
		
// Select a random word from wrds array and return it to the main program
		Random r = new Random() ;
		index = r.nextInt(lines) ;
		word = wrds[index] ;
		
		return word ; 
	}
}
