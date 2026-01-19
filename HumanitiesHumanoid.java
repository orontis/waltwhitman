public class HumanitiesHumanoid extends Adventurer {
    private static String [][] moveset = {{"[AA] AMSCO Assault","[PC] Pharaoh's Curse"},{"[CC] Citation Counter", "[DT] Divine Transcendentalism"}};
    private static String[][] description = {{"filler","filler"},{"filler","filllller"}};
    private int socialCredit = 0; // special resource for humanities people
    private boolean countCitations = false;
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
				System.out.println(moveset[r][c] + ": " + description[r][c]);
			}
		}
	}
	
	
    public String support(String move) {
        String moveL = move.toLowerCase();
		if (moveL.equals("Citation Counter") || moveL.equals("CC"))
		{
			return citationCounter(this);
		}
		else if (moveL.equals("Divine Transcendentalism") || moveL.equals("DT"))
		{
			return divineTrans(this);
		}
		else
		{
			return "please return valid input";
		}
    }  
    public String support(String move, Adventurer other) {
        String moveL = move.toLowerCase();
		if (moveL.equals("Citation Counter") || moveL.equals("CC"))
		{
			if (countCitations) {
                return "You've already used Citation Counter! Pick another move.";
            }
            else {
                return citationCounter(other);
            }
		}
		else if (moveL.equals("Divine Transcendentalism") || moveL.equals("DT"))
		{
			return divineTrans(other);
		}
		else
		{
			return "please return valid input";
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
	/*
    public String attack(String move) {
        String moveL = move.toLowerCase();
		if (moveL.equals("Pharaoh's Curse") || moveL.equals("PC"))
		{
			faroCurse(this);
		}
		else if (moveL.equals("AMSCO Assault") || moveL.equals("AA"))
		{
			amscoAssault(this);
		}
    }*/
    public String attack(String move, Adventurer other) {
        String moveL = move.toLowerCase();
		if (moveL.equals("Pharaoh's Curse") || moveL.equals("PC"))
		{
			return faroCurse(other);
		}
		else if (moveL.equals("AMSCO Assault") || moveL.equals("AA"))
		{
			return amscoAssault(other);
		}
		else
		{
			return "please return valid input";
		}
    }
    public String faroCurse(Adventurer target) {
		return "fill in later";
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

}  
