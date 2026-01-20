public class HumanitiesHumanoid extends Adventurer {
    private static String [][] moveset = {{"\u001B[95m[AA] AMSCO Assault\u001B[0m","\u001B[91m[PC] Pharaoh's Curse\u001B[0m"},{"\u001B[34m[CC] Citation Counter\u001B[0m", "\033[30;47m[DT] Divine Transcendentalism\u001B[0m"}};     
	private static int countCitations_static = 2;
	private static String[][] description = {{"BASIC ATTACK - Deals 5 damage; 30% chance to increase Defense by 5","SPECIAL - 50% chance of success and 50% chance of failure; If successful, the move steals the enemy's next move. Damage done to the ability's target is reflected back to the attacker. Healing done by the enemy is directed to a random hero."},{"DEFEND (" + countCitations_static + " uses) - Defense up by 15","HEAL - 70% chance of healing 10 HP and increasing defense by 10; 30% chance of doing nothing"}};
    private int socialCredit = 0; // special resource for humanities people | special costs 5 social credit
	private int countCitations = 2; // move can't be used when variable reaches 0
    public HumanitiesHumanoid (String name){
        super(name, 70, 70, 5, "Social Credit", "HumanitiesHumanoid");
    }
	
	public static void printMoveset_static() // Taken from the STEM class
	{
		System.out.println("These are the abilities available to the Humanities Humanoid:");
		for (int r = 0; r < 2; r++)
		{
			for (int c = 0; c < 2; c++)
			{
				System.out.println(moveset[r][c] + ": " + description[r][c]);
			}
		}
	}
	
	public void printMoveset() // Taken from the STEM class
	{
		System.out.println("These are the abilities available to the Humanities Humanoid " + this.getName() +  ":");
		for (int r = 0; r < 2; r++)
		{
			for (int c = 0; c < 2; c++)
			{
				if (c == 0 && r == 1)
				{
					System.out.println("\u001B[34m[CC] Citation Counter\u001B[0m: SUPPORT (" + countCitations + " uses left) - Defense up by 15");
				}
				else
				{
					System.out.println(moveset[r][c] + ": " + description[r][c]);
				}
			}
		}
	}
	
	
    public String support(String move) {
		if (!this.getStunState())
		{
		if (move.equals("Citation Counter") || move.equals("CC"))
		{
			return citationCounter(this);
		}
		else if (move.equals("Divine Transcendentalism") || move.equals("DT"))
		{
			return divineTrans(this);
		}
		else
		{
			return "please return valid input";
		}
		}
		else
		{
			return "You are stunned! You can't perform any moves.";
		}
    }  
    public String support(String move, Adventurer other) {
		if (!this.getStunState())
		{
		if (move.equals("Citation Counter") || move.equals("CC"))
		{
			if (countCitations == 0) {
                return "Bad choice. You've run out of citations and lose your turn.";
            }
            else {
                return citationCounter(other);
            }
		}
		else if (move.equals("Divine Transcendentalism") || move.equals("DT"))
		{
			return divineTrans(other);
		}
		else
		{
			return "please return valid input";
		}
		}
		else
		{
			return "You are stunned! You can't perform any moves.";
		}
    }
    public String divineTrans(Adventurer target) {
        if (Math.random() < 0.30) {
            defenseUp(10);
            heal(10);
            return "Divine Transcedentalism successful. 10 points added to defense; 10 HP restored.";
        }
        else {
            return "Divine Transcedentalism unsuccessful. No effects applied.";
        }
    }
    public String citationCounter(Adventurer target) {
        defenseUp(15);
        return "Citation Counter successful. 15 points added to defense.";
    }

    public String attack(String move, Adventurer other) {
		if (!this.getStunState())
		{
		if (move.equals("Pharaoh's Curse") || move.equals("PC"))
		{
			return faroCurse(other);
		}
		else if (move.equals("AMSCO Assault") || move.equals("AA"))
		{
			return amscoAssault(other);
		}
		else
		{
			return "please return valid input";
		}
		}
		else
		{
			return "You are stunned! You can't perform any moves.";
		}
    }
    public String faroCurse(Adventurer target) {
		setCurseState(true);
		this.setSpecial(this.getSpecial() - 5);
		return "The Pharoah enters the battlefield, looking with disdain at your opponents.";
    }
    public String amscoAssault(Adventurer target) {
        target.applyDamage(5);
        if (Math.random() < 0.30) {
            defenseUp(5);
            return "AMSCO Assault applied. 5 damage done to opponent; 5 points added to defense.";
        }
        else {
            return "AMSCO Assault applied. 5 damage done to opponent.";
        }
    }
	
	public void printStatus()
	{
		if (this.getHP() > 0)
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
		else
		{
			this.setLifeStatus(false);
		}
	}

}  
