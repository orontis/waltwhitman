import java.util.Scanner;
import java.util.ArrayList;


public class Game
{	
	private static ArrayList<Adventurer> heroTeam = new ArrayList<Adventurer>();
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
		//Introduction and character selection
		System.out.println("\nThank you for joining the resistance. We are currently on the 10th floor and need to make our way to the 1st floor to escape. You will bring with you a team of 2 students to help you fight off the enemies.");
		print("\nFirst, of what type shall your first hero be, a STEM Superhuman (S) or Humanities Humanoid (H)?");
		print("\n");
		STEMSuperhuman.printMoveset_static();
		print("\n----------------------------------------------------------------------------------------------\n");
		HumanitiesHumanoid.printMoveset_static();
		
		print("\n\n");
		chooseHeroName(0, chooseHeroRole());
		
		print("\nNext, of what type shall your second hero be, a STEM Superhuman (S) or Humanities Humanoid (H)?");
		chooseHeroName(1, chooseHeroRole());
		
		print("\nSpend some time to get acquainted with your team before your departure:\n");
		heroTeam.get(0).printStatus();
		print("\n");
		heroTeam.get(0).printMoveset();
		print("\n\n");
		
		heroTeam.get(1).printStatus();
		print("\n");
		heroTeam.get(1).printMoveset();
		print("\n\nAre you ready to begin? [Y/N]");
		playerReady(); // if player confirms, the game begins and is run through the method runBattles()
	}
	
	public static void playerReady()
	{
		Scanner input = new Scanner(System.in);
		String answer = input.next();
		if (answer.equals("y") || answer.equals("Y"))
		{
			battles(10);
		}
		else if (answer.equals("n") || answer.equals("N"))
		{
			print("We hope you return to save the school!");
			System.exit(0);
		}
		else
		{
			print("I didn't understand that. Please type \"Y\" to continue or \"N\" to close the game.");
			playerReady();
		}
	}
	
//Used for character selection!
public static String chooseHeroRole()
	{
		Scanner chooseFirstRole = new Scanner(System.in);
		String roleInput = chooseFirstRole.next();
		String firstRole = null;
		if (roleInput.equals("S") || roleInput.equals("s"))
		{
			firstRole = "STEMSuperhuman";
			return firstRole;
		}
		else if (roleInput.equals("H") || roleInput.equals("h"))
		{
			firstRole = "HumanitiesHumanoid";
			return firstRole;
		}
		else
		{
			System.out.println("\nI didn't understand that. Please type \"S\" for STEMSuperhuman or \"H\" for HumanitiesHumanoid");
			return chooseHeroRole();
		}
	}
	
	public static void chooseHeroName(int n, String role)
	{
		print("\nWhat should the name of your hero be?");
		Scanner chooseName = new Scanner(System.in);
		String name = chooseName.next();
		
		if (role.equals("STEMSuperhuman"))
		{
			heroTeam.add(new STEMSuperhuman(name));
		}
		else
		{
			heroTeam.add(new HumanitiesHumanoid(name));
		}
	}	

//Executing the battles

	public static void battles(int currFloor)
	{
		if (floor == 0)
		{
			System.out.println("Amazing job, warrior! You have used strategy and bravery to push through each floor, saving the school and leaving safe and sound. You will be an inspiration for the many generations of students to come!");
			//refreshHighScore();
		}
		else
		{
			prepareEnemies(floor);
			executeBattle(heroTeam, enemyTeam, 1);
			battles(floor - 1);
		}
	}
	
	//creates the enemy team
	public static void prepareEnemies(int floor)
	{
		String[] lunchNames = {"The Ladle Queen", "Madame Meatloaf", "Griselda the Gravy Witch", "The Sloppy Joe Overlord", "Supreme Cafeteria Matron", "The Nutritional Tyrant", "Baroness of Blandness", "Miss Mystery Meat", "The Sodium Sorceress", "Lady Leftovers",
		"The Cafeteria Curdler", "Auntie Antibiotics", "The Overcooked Empress", "Dame Dry Chicken", "The Aproned One", "Lunch Period Warden", "The Ladle Bearer", "The Steam Table Keeper","The Cafeteria Sentinel"};
		String[] teacherNames = {"Professor Blank-Eyes","The Chalkbound","The Mindmarked","Red-Eyed Instructor","The Lesson Enforcer","The Curriculum Thrall","The Obedient Educator","The Calculated One","The Grammar Warden","The Timekeeper",
		"The Formula Slave","The Lab Puppet","The Essay Automaton","The Pop-Quiz Drone","Mr. Scantron","The Homework Haunter","The Extra Credit Zombie","The Rubric Reaper","The Attendance Enforcer"};

		enemyTeam = new ArrayList<Adventurer>();
		int rand = (int) (Math.random() * 2);
		for (int i = 0; i <= rand; i++)
		{
			String chosenName = null;
			if (floor == 5 || floor == 1)
			{
				chosenName = lunchNames[(int) (Math.random() * lunchNames.length - 1)];
			}
			else
			{
				chosenName = teacherNames[(int) (Math.random() * teacherNames.length)];
			}
			enemyTeam.add(new EvilStaff(chosenName, floor));
		}
	}


	public static void printTeams(ArrayList<Adventurer> team)
	{
		for (int i = 0; i < team.size(); i++)
		{
			if (team.get(i).getAlive())
			{
				print("----------------------");
				print(team.get(i).toString());
				team.get(i).printStatus();
				print("");
				team.get(i).printMoveset();
			}
		}
	}

	public static void executeBattle(ArrayList<Adventurer> heroes, ArrayList<Adventurer> enemies, int round)
	{
		checkTeamHealthHero(heroes, false);
		checkTeamHealthEnemy(enemies, false);
		for (int i = 0; i < enemies.size(); i++)
		{
			enemies.get(i).setEnemies(heroes);
		}
		print("\n Current floor: " + floor + "\n");
		print("-------------------------------------------------------------------");
		print("\n Enemies: ");
		printTeams(enemyTeam);

		print("\n-------------------------------------------------------------------");
		print("\nYour Team: ");
		printTeams(heroTeam);
		print("-------------------------------------------------------------------");

		print("\n\nRound " + round + "\n\n");

		print("Which hero would you like to select? CASE SENSITIVE!");
		Adventurer hero = chooseHero();
		print("\nWould you like to rest (+2 " + hero.getSpecialName() + "), Attack, or Support (yourself or an ally). Type R for rest, A for attack, SY for supporting this hero, and SO for supporting another hero.");
		print(ability(hero));
		
		print("-------------------------------------------------------------------");
		print("Enemy's turn...\n");
		print(enemyAction(heroes, enemies));
		
		if (checkTeamHealthHero(heroes,true) && checkTeamHealthEnemy(enemies,true))
		{
			executeBattle(heroes, enemies, round + 1);
		}

	}
	
	public static String enemyAction(ArrayList<Adventurer> heroes, ArrayList<Adventurer> enemies)
	{
		Adventurer randomHero = heroes.get((int) (Math.random() * heroes.size()));
		Adventurer randomEnemy = enemies.get((int) (Math.random() * enemies.size()));
		Adventurer randomEnemy2 = enemies.get((int) (Math.random() * enemies.size()));
		int rand = (int) (Math.random() * 6);
		if (floor == 5 || floor == 1)
		{
			if (rand == 0)
			{
				return randomEnemy.restoreSpecial(2);
			}
			else if (rand == 1)
			{
				return randomEnemy.attack("MM", randomHero);
			}
			else if (rand == 2)
			{
				return randomEnemy.attack("GF",randomHero);
			}
			else if (rand == 3)
			{
				return randomEnemy.attack("DS",randomHero);
			}
			else if (rand == 4)
			{
				return randomEnemy.support("CM");
			}
			else
			{
				return randomEnemy.support("CM",randomEnemy2);
			}
		}
		else
		{
			if (rand == 0)
			{
				return randomEnemy.restoreSpecial(2);
			}
			else if (rand == 1)
			{
				return randomEnemy.attack("ED", randomHero);
			}
			else if (rand == 2)
			{
				return randomEnemy.attack("PQ",randomHero);
			}
			else if (rand == 3)
			{
				return randomEnemy.attack("FE",randomHero);
			}
			else if (rand == 4)
			{
				return randomEnemy.support("MD");
			}
			else
			{
				return randomEnemy.support("MD",randomEnemy2);
			}
		}
	}
	
	public static Adventurer chooseTarget(String prevChoice)
	{
		print("Will you be targeting a hero or an evil staff member? Type H for hero and E for evil staff member.");
		Scanner readInput = new Scanner(System.in);
		String choice1 = readInput.next();
		if (choice1.equals("H"))
		{
			if (prevChoice.equals("A"))
			{
				print("Don't attack a fellow hero!");
				return chooseTarget(prevChoice);
			}
			return chooseHero();
		}
		else if (choice1.equals("E"))
		{
			if (prevChoice.equals("SO"))
			{
				print("Don't help the enemy!");
				return chooseTarget(prevChoice);
			}
			print("Which enemy will you target? CASE SENSITIVE!");
			return chooseEnemy();
		}
		else
		{
			print("I don't understand what you typed. Make sure you typed only an H OR an E");
			return chooseTarget(prevChoice);
		}
	}

	public static String ability(Adventurer hero)
	{
		Scanner readInput = new Scanner(System.in);
		String choice = readInput.nextLine();
		if (choice.equals("R"))
		{
			return hero.restoreSpecial(2);
		}
		else if (choice.equals("SY"))
		{
			print("Which support ability would you like to select? Please use the letter abbreviations.");
			return chooseSupport(hero);
		}
		else if (choice.equals("A"))
		{
			print("Which target would you like to select? CASE SENSITIVE!");
			Adventurer target = chooseTarget(choice);
			print("Which offensive ability would you like to select? Please use the letter abbreviations.");
			return chooseAttack(hero, target);

		}
		else if (choice.equals("SO"))
		{
			print("Which target would you like to select? CASE SENSITIVE!");
			Adventurer target = chooseHero();
			print("Which support ability would you like to select? Please use the letter abbreviations.");
			return chooseSupport(hero,target);
		}
		else
		{
			print("I don't understand what you typed. Make sure you typed an R, SY, A, or SO.");
			return ability(hero);
		}
	}

	public static String chooseSupport(Adventurer hero)
	{
		Scanner readInput = new Scanner(System.in);
		String choice = readInput.nextLine();
		if (hero instanceof STEMSuperhuman)
		{
			if (choice.equals("MC"))
			{
				return hero.support("MC");
			}
			else if (choice.equals("AT"))
			{
				return hero.support("AT");
			}
		}
		else
		{
			if (choice.equals("CC"))
			{
				return hero.support("CC");
			}
			else if (choice.equals("DT"))
			{
				return hero.support("DT");
			}
		}
		print("Please type in a valid selection.");
		return chooseSupport(hero);
	}
	
	public static String chooseSupport(Adventurer hero, Adventurer target)
	{
		Scanner readInput = new Scanner(System.in);
		String choice = readInput.nextLine();
		if (hero instanceof STEMSuperhuman)
		{
			if (choice.equals("MC"))
			{
				return hero.support("MC",target);
			}
			else if (choice.equals("AT"))
			{
				return hero.support("AT",target);
			}
		}
		else
		{
			if (choice.equals("CC"))
			{
				return hero.support("CC",target);
			}
			else if (choice.equals("DT"))
			{
				return hero.support("DT",target);
			}
		}
		print("Please type in a valid selection.");
		return chooseSupport(hero,target);
	}
	
	public static String chooseAttack(Adventurer hero, Adventurer target)
	{
		Scanner readInput = new Scanner(System.in);
		String choice = readInput.nextLine();
		if (target instanceof EvilStaff)
		{
			if (hero instanceof STEMSuperhuman)
			{
				if (choice.equals("CS"))
				{
					return hero.attack("CS", target);
				}
				else if (choice.equals("U"))
				{
					return hero.attack("U", target);
				}
			}
			else
			{
				if (choice.equals("PC"))
				{
					return hero.attack("PC", target);
				}
				else if (choice.equals("AA"))
				{
					return hero.attack("AA", target);
				}
				else
				{
					print("Please type in a valid selection.");
					return chooseAttack(hero, target);
				}
			}
		}
		print("Don't fight your own teammate!");
		return chooseAttack(hero, target);
	}
	

	public static Adventurer chooseEnemy()
	{
		Scanner readInput = new Scanner(System.in);
		String choice = readInput.nextLine();
		if (choice.equals(enemyTeam.get(0).getName()))
		{
			return enemyTeam.get(0);
		}
		else if (enemyTeam.size() == 2 && choice.equals(enemyTeam.get(1).getName()))
		{
			return enemyTeam.get(1);
		}
		else
		{
			print("I don't understand what you typed. Make sure you typed the name correctly, watching for spelling, spacing, punctuation, and capitalization.");
			return chooseEnemy();
		}
	}

	public static Adventurer chooseHero()
	{
		Scanner readInput = new Scanner(System.in);
		String choice = readInput.nextLine();

		if (choice.equals(heroTeam.get(0).getName()))
		{
			return heroTeam.get(0);
		}
		else if (heroTeam.size() > 1 && choice.equals(heroTeam.get(1).getName()))
		{
			return heroTeam.get(1);
		}
		else
		{
			print("Sorry, I didn't understand that. Refer to the information above and make sure your input matches the capitalization, punctuation, and spacing on the name.");
			return chooseHero();
		}
	}

	//going to be used to assess how many adventurers the player has alive
	public static boolean checkTeamHealthHero(ArrayList<Adventurer> team, boolean willPrint)
	{
		if (willPrint)
		{print("\nAssessing your team's health...");}
		if (team.size() == 0)
		{
			print("\nYour entire team has died. Your adventure is over. You were able to make it to floor " + floor + ".");
			System.exit(0);
		}
		else
		{
			for (int i = 0; i < team.size(); i++)
			{
				Adventurer currAd = team.get(i);
				if (currAd.getAlive())
				{
					if (willPrint)
					print("    - " + currAd + " is still alive");
				}
				else
				{
					if (willPrint)
					{print("    - " + currAd + " is DEAD");}
					team.remove(i);
					i--;
				}
			}
			if (willPrint)
			{print("\nYour team after this battle: " + team);}
		}
		
		if (team.size() == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static boolean checkTeamHealthEnemy(ArrayList<Adventurer> team, boolean willPrint)
	{
		if (willPrint)
		{print("\nAssessing enemy team's health...");}
			for (int i = 0; i < team.size(); i++)
			{
				Adventurer currAd = team.get(i);
				if (currAd.getAlive())
				{
					if (willPrint)
						print("    - " + currAd + " is still alive");
				}
				else
				{
					if (willPrint)
					{print("    - " + currAd + " is DEAD");}
					team.remove(i);
					i--;
				}
			}
			if (willPrint)
			{print("\nEnemy team after this battle: " + team);}
		
		if (team.size() == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}