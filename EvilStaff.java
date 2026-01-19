import java.util.Scanner;

public class EvilStaff extends Adventurer
{
	// first row - basic attacks, last element always special/ultimate move | second row - support
	private int ultCounter_teach = 1; // decreases by 1 whenever ult move for teacher is used
	private String[][] lunchLady_MS = {{"[MM] Mystery Meat","[GF] \"Please Grab a Fruit!\"","[DS] Divine Spatula"},{"[CM] Choco Milk"}};
	private String[][] teacher_MS = {{"[ED] Emotional Damage","[PQ] Pop Quiz", "[FE] THE FINAL EXAM"},{"[MD] McDonald's Coffee"}};
	private String[][] movesetDescription_teach = {{"BASIC ATTACK - Stuns player for 1 turn; 50% chance of dealing 15 damage; 50% chance of dealing 10 damage","BASIC ATTACK - Gives the player a random math problem; deals 5 damage if player gives a wrong answer", "ULTIMATE ATTACK (" + ultCounter_teach + " uses left) - If the player has less than 25 HP, they instantly die."},{"HEAL - Heals 5 HP"}};
	private String[][] movesetDescription_lunch = {{"BASIC ATTACK - 50% chance of dealing 15 damage; 50% chance of healing the enemy for 5 HP","BASIC ATTACK - 30% chance of stunning the player for 2 turns; deals 5 damage","SPECIAL ATTACK - 30% chance of dealing 70 damage; 30% chance of healing 15 HP; 30% chance of stunning player for 2 turns; 10% chance of instantly killing the player."},{"HEAL (uses left) - 30% chance of dealing 10 damage to you; 10% chance of killing you instantly; 60% chance of healing 20 HP","DEFENSE - 60% chance of protecting against all damage; 20% chance of doing nothing; 20% chance of doubling all damage taken for 1 turn"}};
	private String[][] activeMoveset = teacher_MS;
	private String[][] activeDesc = movesetDescription_teach;
	private static int floor;
	public int abilityBuff = 0; // only 5 if the lunch lady is on first floor
	
	public EvilStaff(String name, int floor)
	{
		super(name, 100, 100, 5, "tears", "teacher");
		this.floor = floor;
		enemiesCreated++;
		refreshState();
	}
	
	public static void setFloor(int newFloor)
	{
		floor = newFloor;
	}
	
	public String support(String move)
	{
		if (getCurseState())
		{
			int rand = (int) (Math.random() * 2);
			if ((this.getEnemies()).size() == 1)
			{
				rand = 0;
			}
			if (floor == 5 || floor == 1)
			{
				setCurseState(false);
				return chocoMilk(this.getEnemies().get(rand));
			}
			else
			{
				setCurseState(false);
				return coffee(this.getEnemies().get(rand)));
			}
		}
		else
		{
			if (floor == 5 || floor == 1)
			{
				return chocoMilk(this);
			}
			else
			{
				return coffee(this);
			}
		}
	}
	
	public String support(String move, Adventurer other)
	{
		if (getCurseState())
		{
			support(move);
		}
		else
		{
			if (floor == 5 || floor == 1)
			{
				return chocoMilk(other);
			}
			else
			{
				return coffee(other);
			}	
		}
	}
	
	public String attack(String move, Adventurer other)
	{
		if (getCurseState())
		{
			if (floor == 5 || floor == 1)
			{
				if (move.equals("MM"))
				{
					setCurseState(false);
					return mysteryMeat(this);
				}
				else if (move.equals("GF"))
				{
					setCurseState(false);
					return fruit(this);
				}
				else
				{
					if (this.getSpecial() != 5)
					{
						setCurseState(false);
						return "Lunch Lady attempted to use her divine spatula, but It decided that she was not worthy yet.";
					}
					else
					{
						setCurseState(false);
						this.setSpecial(this.getSpecial() - 5);
						return divineSpatula(this);
					}
				}	
			}
			else
			{
				if (move.equals("ED"))
				{
					return emotionalDamage(this);
				}
				else if (move.equals("PQ"))
				{
					return popQuiz(this);
				}
				else
				{
					if (this.getSpecial() != 5 && ultCounter_teach != 1)
					{
						return "The teacher attempted to assign the final exam, but your aura was too powerful that the exam starting answering itself.";
					}
					else
					{
						this.setSpecial(this.getSpecial() - 5);
						ultCounter_teach--;
						return finalExam(this);
					}
				}
			}
		}
		else
		{
			if (floor == 5 || floor == 1)
			{
				if (move.equals("MM"))
				{
					return mysteryMeat(other);
				}
				else if (move.equals("GF"))
				{
					return fruit(other);
				}
				else
				{
					if (this.getSpecial() != 5)
					{
						return "Lunch Lady attempted to use her divine spatula, but It decided that she was not worthy yet.";
					}
					else
					{
						this.setSpecial(this.getSpecial() - 5);
						return divineSpatula(other);
					}
				}	
			}
			else
			{
				if (move.equals("ED"))
				{
					return emotionalDamage(other);
				}
				else if (move.equals("PQ"))
				{
					return popQuiz(other);
				}
				else
				{
					if (this.getSpecial() != 5 && ultCounter_teach != 1)
					{
						return "The teacher attempted to assign the final exam, but your aura was too powerful that the exam starting answering itself.";
					}
					else
					{
						this.setSpecial(this.getSpecial() - 5);
						ultCounter_teach--;
						return finalExam(other);
					}
				}
			}
		}
	}
	
	public String emotionalDamage(Adventurer other)	
	{
		other.setStunCount(1);
		other.setStunState(true);
		
		double rand = Math.random();
		if (rand < 0.5)
		{
			other.applyDamage(10);
			return "Why'd you even try to roast the teacher, bro?";
		}
		else
		{
			other.applyDamage(15);
			return "Yeah, just drop high school, bro.";
		}
	}
	
	public String finalExam(Adventurer other)
	{
		if (other.getHP() < 25)
		{
			other.applyDamage(100);
			return "It's ok. McDonald's MIGHT still hire you!";
		}
		return "Good thing your brain still works.";
	}
	
	public String popQuiz(Adventurer other)
	{
		int digit1 = (int) (Math.random() * 1100) - 100;
		int digit2 = (int) (Math.random() * 200) - 100 ;
		int digit3 = (int) (Math.random() * 1100) - 100;
		System.out.print(digit1 + " + " + digit2 + " + " + digit3 + " = ");
		
		Scanner readInput = new Scanner(System.in);
		double answer = 0;
		try{
			answer = readInput.nextDouble();
		}catch(NumberFormatException e) {
			System.out.println("Bro, what? That's not even a number...");
		}
		
		if (answer == digit1 + digit2 + digit3)
		{
			return "Correct! You survived the pop quiz!";
		}
		else
		{
			other.applyDamage(5);
			return "Nice try! You'll get it next time...I hope.";
		}
	}
	
	public String mysteryMeat(Adventurer other)
	{
		double rand = Math.random();
		if (rand < 0.5)
		{
			other.heal(5);
			return "The mystery meat wasn't so mysterious after all! It's a nice A5 Wagyu steak.";
		}
		else
		{
			other.applyDamage(15 + abilityBuff);
			return "Yea, you probably shouldn't have eaten that. -15 HP";
		}
	}
	
	public String fruit(Adventurer other)
	{
		double rand = Math.random();
		other.applyDamage(5 + abilityBuff);
		if (rand < 0.3)
		{
			other.setStunState(true);
			other.setStunCount(2);
			return "The lunch lady asks you to grab a fruit, causing you to freeze in your tracks and lose social credit.";
		}
		else
		{
			return "The lunch lady asks you to grab a fruit. Though you push through the line, you do suffer from social humiliation.";
		}
	}
	
	public String divineSpatula(Adventurer other)
	{
		double rand = Math.random();
		if (rand < 0.3)
		{
			other.applyDamage(20 + abilityBuff);
			return "The spatula strikes you at an unimaginable speed, dealing great damage.";
		}
		else if (rand >= 0.3 && rand < 0.6)
		{
			this.heal(15 + abilityBuff);
			return "The spatula blesses its user with restoration.";
		}
		else if (rand >= 0.6 && rand < 0.9)
		{
			other.setStunState(true);
			other.setStunCount(2);
			return "The divine spatula freezes you in place for 2 turns.";
		}
		else
		{
			other.applyDamage(100);
			return "The divine spatula has deemed you a traitor and doomed you to eternal sufferring.";
		}
	}
	
	public String coffee(Adventurer target)
	{
		target.heal(5);
		return "The opponent drinks the mythical McDonald's iced coffee. It blesses them with life, liberty, and 5 more HP.";
	}
	
	public String chocoMilk(Adventurer target)
	{
		target.heal(abilityBuff + 10);
		return "The opponent chugs a carton of chocolate milk, the nutrients of which heals their injuries.";
	}
	
	

	
	public void setMoveSet(String[][] newMS)
	{
		this.activeMoveset = newMS;
	}
	
	public void refreshState() // checks enemyState to determine whether moveset should be swapped
	{
		if (floor != 5 && floor != 1)
		{
			setSpecialName("tears");
			this.setRole("Mind-controlled Teacher");
			activeMoveset = teacher_MS;
			activeDesc = movesetDescription_teach;
		}
		else
		{
			setSpecialName("forks");
			this.setRole("Lunch Lady");
			activeMoveset = lunchLady_MS;
			activeDesc = movesetDescription_lunch;
			if (floor == 1)
			{
				abilityBuff = 5;
			}
		}
	}
	
	public void printMoveset()
	{
		System.out.print("These are the abilities for the ");
		if (floor != 5 && floor != 1)
		{
			System.out.println("mind-controlled teacher " + this.getName() + ":");
		}
		else
		{
			System.out.println("lunch lady " + this.getName() + ":");
		}
		
		for (int r = 0; r < activeMoveset.length; r++)
		{
			for (int c = 0; c < activeMoveset[r].length; c++)
			{
				System.out.println(activeMoveset[r][c] + ": " + activeDesc[r][c]);
			}
		}
	}
	
	//will have to work on later
	public static int getEnemiesDefeated()
	{
		return enemiesCreated - 1; // essentially, this will tell the user how many enemies they have defeated when they died.
	}
}
