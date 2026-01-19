import java.util.Scanner;
import java.util.ArrayList;


public class Game
{	
	private static Adventurer[] heroTeam = new Adventurer[2];
	private static ArrayList<Adventurer> enemyTeam = new ArrayList<Adventurer>();
	private static int floor = 10;
	private static boolean gameActive = false;

	public static void main(String[] args)
	{
		
		//A way for the user to start the confirm
		System.out.print("Welcome, brave and intelligent student of Stuyvesant! Beware, the evil Lunch Ladies have mind-controlled all of the teachers in the school, and it is up to you to free them and save the day! Do you accept? [Y/N] ");
		confirmPlaying();
		
		
	}
	
	public static void print(String input)
	{
		System.out.println(input);
	}

	public static void confirmPlaying()
	{
		Scanner readAnswer1 = new Scanner(System.in);
		String beginGame = (readAnswer1.next()).toLowerCase();
		if (beginGame.length() == 1)
		{
			if (beginGame.equals("y"))
			{
				gameActive = true;
				executeGame();
			}
			else if (beginGame.equals("n"))
			{
				System.out.println("\nWe hope you will return again to help us!");
				System.exit(0);
			}
			else
			{
				System.out.println("\nI didn't understand that. Please type \"Y\" or \"N\".");
				confirmPlaying();
			}
		}
		else
		{
			System.out.println("\nI didn't understand that. Please type \"Y\" or \"N\".");
			confirmPlaying();
		}
	}
	
	public static void executeGame()
	{
		System.out.println("\nThank you for joining the resistance. We are currently on the 10th floor and need to make our way to the 1st floor to escape. You will bring with you a team of 2 students to help you fight off the enemies.");
		print("\nFirst, of what type shall your first hero be, a STEM Superhuman (S) or Humanities Humanoid (H)?");
		print("\n");
		STEMSuperhuman.printMoveset_static();
		print("\n----------------------------------------------------------------------------------------------\n");
		HumanitiesHumanoid.printMoveset_static();
		
		print("\n\n")
		chooseHeroes(0);
		
		print("\nNext, of what type shall your second hero be, a STEM Superhuman (S) or Humanities Humanoid (H)?");
		chooseHeroes(1 /*index*/);
		
		print("\nGreat choices! I'm sure they'll do great on your journey.");
	}
	
	public static boolean checkInput(String input, String[] validInputs)
	{
		for (int i = 0; i < validInputs.length; i++)
		{
			if (input.equals(validInputs[i]))
			{
				return true;
			}
		}
		return false;
	}
	
	public static void chooseHeroes(int n)
	{
		Scanner chooseFirstRole = new Scanner(System.in);
		String roleInput = chooseFirstRole.next();
		String firstRole = null;
		if (firstType_og.equals("S") || firstType_og.equals("s"))
		{
			validInput = true;
			firstType = "STEMSuperhuman";
		}
		else if (firstType_og.equals("H") || firstType_og.equals("h"))
		{
			validInput = true;
			firstType = "HumanitiesHumanoid";
		}
		else
		{
			System.out.println("\nI didn't understand that. Please type \"S\" for STEMSuperhuman or \"H\" for HumanitiesHumanoid");
		}
	}
		
		print("\nWhat should the name of your hero be?");
		Scanner chooseName = new Scanner(System.in);
		String name = chooseName.next();
		
		if (firstType.equals("STEMSuperhuman"))
		{
			heroTeam[n] = new STEMSuperhuman(name);
		}
		else
		{
			heroTeam[n] = new HumanitiesHumanoid(name);
		}
	}

}