public class HumanitiesHumanoid extends Adventurer {
    private String [][] moveset = {{"[CC] Citation Counter", "[DT] Divine Transcendentalism"},{"[PC] Pharaoh's Curse", "[AA] AMSCO Assault"}};
    private int healCounter = 2;
    private String[][] description = {{"filler","filler"},{"filler","filllller"}};
    private int socialCredit = 0; // special resource for humanities people
    private boolean countCitations = false;
    public HumanitiesHumanoid (String name){
        this(name, 70, 70, 5, "Social Credit", "HumanitiesHumanoid");
    }
	public void printMoveset() // Taken from the STEM class
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
    public void choseSupport(String move) {
        String moveL = move.toLowerCase();
		if (moveL.equals("Citation Counter") || moveL.equals("CC"))
		{
			citationCounter(this);
		}
		else if (moveL.equals("Divine Transcendentalism") || moveL.equals("DT"))
		{
			divineTrans(this);
		}
    }  
    public void choseSupport(String move, Adventurer other) {
        String moveL = move.toLowerCase();
		if (moveL.equals("Citation Counter") || moveL.equals("CC"))
		{
			if (countCitations) {
                System.out.println("You've already used Citation Counter! Pick another move.")
            }
            else {
                citationCounter(other);
            }
		}
		else if (moveL.equals("Divine Transcendentalism") || moveL.equals("DT"))
		{
			divineTrans(other);
		}
    }
    public void divineTrans(Adventurer target) {
        if (math.Random() < 0.30) {
            defenseUp(10);
            heal(10);
            System.out.println("Divine Transcedentalism successful. 10 points added to defense; 10 HP restored.")
        }
        else {
            System.out.println("Divine Transcedentalism unsuccessful. No effects applied.")
        }
    }
    public void citationCounter(Adventurer target) {
        defenseUp(15);
        System.out.println("Citation Counter successful. 15 points added to defense.")
    }
    public void choseAttack(String move) {
        String moveL = move.toLowerCase();
		if (moveL.equals("Pharaoh's Curse") || moveL.equals("PC"))
		{
			faroCurse(this);
		}
		else if (moveL.equals("AMSCO Assault") || moveL.equals("AA"))
		{
			amscoAssault(this);
		}
    }
    public void choseAttack(String move, Adventurer other) {
        String moveL = move.toLowerCase();
		if (moveL.equals("Pharaoh's Curse") || moveL.equals("PC"))
		{
			faroCurse(other);
		}
		else if (moveL.equals("AMSCO Assault") || moveL.equals("AA"))
		{
			amscoAssault(other);
		}
    }
    public void faroCurse(Adventurer target) {

    }
    public void amscoAssault(Adventurer target) {
        target.applyDamage(5);
        if (math.Random() < 0.30) {
            defenseUp(5);
            System.out.println("AMSCO Assault applied. 5 damage done to opponent; 5 points added to defense.");
        }
        else {
            System.out.println("AMSCO Assault applied. 5 damage done to opponent.");
        }
    }

}  
