public class STEMSuperhuman extends Adventurer 
{
	// first row - basic attacks, last element always special/ultimate move | second row - support
	private String[2][2] moveset = {{"Chemical Splash","Uranium"},{"Mystery Concoction","Apple Totem"}};
	private int healCounter = 2; // decreases by 1 whenever Mystery Concoction is used
	private String[2][2] movesetDescription = {{"BASIC ATTACK - 20% chance of dealing 15 damage to yourself; 80% change of dealing 15 damage to enemy","SPECIAL ATTACK - 25% chance of dealing 100 damage to enemy; 25% chance of healing 20 HP; 50% chance of instant death"},{"HEAL (" + healCounter + "uses left) - 30% chance of dealing 10 damage to you; 10% chance of killing you instantly; 60% chance of healing 20 HP","DEFENSE - 60% chance of protecting against all damage; 20% chance of doing nothing; 20% chance of doubling all damage taken for 1 turn"}};
	private int damageMultiplier = 1;
	private int IQ = 0; // this is the special resource | max is 5, which is also the cost of performing the special
	
	public STEMSuperhuman(String name)
	{
		this(name, 60, 60, 5, "IQ", "STEMSuperhuman");
	}

	public void printMoveset()
	{
		System.out.println("These are the abilities available to the " + this.getRole() + " :");
		for (int r = 0; r < 2; r++)
		{
			for (int c = 0; c < 2; c++)
			{
				System.out.println(moveset[r][c] + ": " + movesetDescription[r][c]);
			}
		}
	}  
	
	public void action(String selection)
	{
		printMoveset();
	}
	
	public String support(String move)
	{
		String moveL = move.toLowerCase();
		if (moveL.equals("mystery concotion"))
		{
			return mysteryConcoction(this);
		}
		else if (moveL.equals("apple totem"))
		{
			return appleTotem(this);
		}
	}
	
	public String support(String move, Adventurer other)
	{
		String moveL = move.toLowerCase();
		if (moveL.equals("mystery concotion"))
		{
			return mysteryConcoction(other);
		}
		else if (moveL.equals("apple totem"))
		{
			return appleTotem(other);
		}
	}
	
	public String mysteryConcoction(Adventurer receiver)
	{
		
	}
	
	public String appleTotem(Adventurer receiver)
	{
		
	}
	
	
	public String attack(String move, Adventurer other)
	{
		String moveL = move.toLowerCase();
		if (moveL.equals("chemical splash"))
		{
			return this.chemicalSplash(other);
		}
		else if (moveL.equals("uranium"))
		{
			return this.specialAttack(other); // remember to consider IQ count
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
		if (this.IQ == 5)
		{
			double rand = Math.random()
			if (rand <= 0.25)
			{
				other.applyDamage(100);
				return "Nice throw! The uranium ore falls into the opponent's mouth, dealing a whopping 100 damage!";
			}
			else if (rand > 0.25 && rand <= 0.50)
			{
				this.heal(20);
				return "What a friendly isotope! The uranium ore blesses you with its warming radiation, restoring 20 HP.";
			}
			else
			{
				this.applyDamage(100);
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
	if (health > 0)
	{
		System.out.println(role + " of name " + name + ":");
		if (health > MAX_HP * 0.5)
		{
			System.out.print("\u001b[32mGreen");
		}
		else if (health <= MAX_HP * 0.5 && health > MAX_HP * 0.25)
		{
			System.out.print("u001b[33mYellow");
		}
		else
		{
			System.out.print("uoo1b[31mRed");
		}
		System.out.print("1) Health - " + health + "/" + MAX_HP + "u001b[0m" + "\n2) Stunned - ");
		if (isStunned == true)
		{
			System.out.print(isStunned + " for " + stunCount + " turns");
		}
		else
		{
			System.out.print(isStunned);
		}
		System.out.print("\n3) Defense - " + defense + "\n4) " + specialName + " Count - " + specialCount + "/" + "specialMax");	
	}
	else
	{
		System.out.println("\u001b[31mRed" + "YOU ARE DEAD.")
		System.exit(0); // exits the game
	}
	
	@Override
	public void applyDamage(int amount){
		int totalDamage = amount * damageMultiplier;
		if (defense >= totalDamage)
			{
				this.defense -= totalDamage;
			}
		else if (defense < totalDamage && defense > 0)
			{
				int temp = this.defense;
				this.defense = 0;
				this.health -= totalDamage - temp; 
			}
		else
			{
				this.health = this.health - totalDamage;
			}
		
		if (health < 0)
		{
			health = 0;
		}
	
		if (damageMultiplier != 1)
		{
			damageMultiplier = 1;
		}
	}
}
