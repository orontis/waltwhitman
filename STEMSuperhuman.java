// will have to work on adding a team mechanism into the game

public class STEMSuperhuman extends Adventurer 
{
	// first row - basic attacks, last element always special/ultimate move | second row - support
	private static String[][] moveset = {{"\u001B[32m[CS] Chemical Splash\u001b[0m","\u001b[33m[U] \u001B[31mU\u001B[32mr\u001B[34ma\u001B[35mn\u001B[36mi\u001B[37mu\u001B[33mm\u001b[0m"},{"\u001b[35m[MC] Mystery Concoction\u001B[0m]","\u001B[31m[AT] Apple Totem\u001B[0m"}};
	private int healCounter = 2;
	private static int healCounter_static = 2; // decreases by 1 whenever Mystery Concoction is used
	private static String[][] movesetDescription = {{"BASIC ATTACK - 20% chance of dealing 15 damage to yourself; 80% change of dealing 15 damage to enemy","SPECIAL ATTACK - 25% chance of dealing 100 damage to enemy; 25% chance of healing 20 HP; 50% chance of instant death"},{"HEAL (" + healCounter_static + " uses) - 30% chance of dealing 10 damage to you; 10% chance of killing you instantly; 60% chance of healing 20 HP","DEFENSE - 60% chance of protecting against all damage; 20% chance of doing nothing; 20% chance of doubling all damage taken for 1 turn"}};	
	public STEMSuperhuman(String name)
	{
		super(name, 60, 60, 5, "IQ", "STEMSuperhuman");
	}


	public static void printMoveset_static()
	{
		System.out.println("These are the abilities available to the STEM Superhuman:");
		for (int r = 0; r < 2; r++)
		{
			for (int c = 0; c < 2; c++)
			{
				System.out.println(moveset[r][c] + ": " + movesetDescription[r][c]);
			}
		}
	}

	public void printMoveset()
	{
		System.out.println("These are the abilities available to the STEM Superhuman " + this.getName() + ":");
		for (int r = 0; r < 2; r++)
		{
			for (int c = 0; c < 2; c++)
			{
				if (r == 1 && c == 0)
				{
					System.out.println("\u001B[35m[MC] Mystery Concoction\u001B[0m: HEAL (" + this.healCounter + " uses left) - 30% chance of dealing 10 damage to you; 10% chance of killing you instantly; 60% chance of healing 20 HP");
				}
				else
				{
					System.out.println(moveset[r][c] + ": " + movesetDescription[r][c]);
				}
			}
		}
	}  
	
	
	public String support(String move)
	{
		if (!this.getStunState())
		{
		if (move.equals("MC"))
		{
			return mysteryConcoction(this);
		}
		else if (move.equals("AT"))
		{
			return appleTotem(this);
		}
		else
		{
			return "error";
		}
		}
		else
		{
			return "You are stunned! You can't perform any moves.";
		}
	}
	
	public String support(String move, Adventurer other)
	{
		if (!this.getStunState())
		{
		if (move.equals("MC"))
		{
			return mysteryConcoction(other);
		}
		else if (move.equals("AT"))
		{
			return appleTotem(other);
		}
		else
		{
			return "error";
		}
		}
		else
		{
			return "You are stunned! You can't perform any moves.";
		}
	}
	
	public String mysteryConcoction(Adventurer receiver)
	{
		if (healCounter != 0)
		{
			double rand = Math.random();
			if (rand < 0.1)
			{
				receiver.applyDamage(100);
				healCounter--;
				return "Yikes! The drink contained a lot of lead and cyanide, instantly killing whoever drinks it!";
			}
			else if (rand >= 0.1 && rand < 0.4)
			{
				receiver.applyDamage(10);
				healCounter--;
				return "Why'd it have expired Pepsi?! The consumer takes 10 damage.";
			}
			else
			{
				receiver.heal(20);
				healCounter--;
				return "Such bliss in a beaker! Its warm, heavenly sweetness blesses its consumer with restoration.";
			}
		}
		else
		{
			return "You drank all of your mystery concoctions. You cannot use this ability for the remainder of the battle.";
		}
	}
	
	public String appleTotem(Adventurer receiver)
	{
		double rand = Math.random();
		if (rand < 0.5)
		{
			return "Just a nice 'ol beaker of cold water. Nothing happens.";
		}
		else
		{
			receiver.setDamageMult(0);
			return "Dr. Yu has blessed you with temporary protection. You will take no damage--but only for one time!";
		}
	}
	
	
	public String attack(String move, Adventurer other)
	{
		if (!this.getStunState())
		{
		if (move.equals("CS"))
		{
			return this.chemicalSplash(other);
		}
		else
		{
			return this.specialAttack(other); // remember to consider IQ count
		}
		}
		else
		{
			return "You are stunned! You can't perform any moves.";
		}
		
	}
	
	public String chemicalSplash(Adventurer other)
	{
		double rand = Math.random();
		if (rand <= 0.2)
		{
			this.applyDamage(15);
			return "Yikes! The chemicals are all over you, causing you to lose 15 Health!";
		}
		else
		{
			other.applyDamage(15);
			return "The enemy is covered in chemicals! They take 15 damage!";
		}
	}
	
	public String specialAttack(Adventurer other)
	{
		return this.uranium(other);
	}
	
	public String uranium(Adventurer other)
	{
		if (this.getSpecial() == 5)
		{
			double rand = Math.random();
			if (rand <= 0.25)
			{
				other.applyDamage(100);
				this.setSpecial(this.getSpecial() - 5);
				return "Nice throw! The uranium ore falls into the opponent's mouth, dealing a whopping 100 damage!";
			}
			else if (rand > 0.25 && rand <= 0.50)
			{
				this.heal(20);
				this.setSpecial(this.getSpecial() - 5);
				return "What a friendly isotope! The uranium ore blesses you with its warming radiation, restoring 20 HP.";
			}
			else
			{
				this.applyDamage(100);
				this.setSpecial(this.getSpecial() - 5);
				return "Nice one, buddy. You had to throw the uranium, not eat it.";
			}
		}
		else
		{
			return "Not enough IQ to handle uranium safely (a.k.a. to perform your special)";
		}
	}

	@Override
	public void printStatus()
	{
			System.out.println(this.getRole() + " of name " + this.getName() + ":");
			if (this.getHP() > this.getmaxHP() * 0.5)
			{
				System.out.print("\u001b[32m");
			}
			else if (this.getHP() <= this.getmaxHP() * 0.5 && this.getHP() > this.getmaxHP() * 0.25)
			{
				System.out.print("\033[33m");
			}
			else
			{
				System.out.print("\033[91m");
			}
			System.out.print("1) Health - " + this.getHP() + "/" + this.getmaxHP());
			System.out.print("\033[37m");
			System.out.print("\n2) Stunned - ");
			if (this.getStunState() == true)
			{
				System.out.print(this.getStunState() + " for " + this.getStunCount() + " turns");
			}
			else
			{
				System.out.print(this.getStunState());
			}
			System.out.print("\n3) Defense - " + this.getDefense() + "\n4) " + this.getSpecialName() + " Count - " + this.getSpecial() + "/" + this.getSpecialMax());	
	}

	//allows me to reset the healCounter every battle
	public void setHealCount(int newHC)
	{
		this.healCounter = newHC;
	}
	
}
