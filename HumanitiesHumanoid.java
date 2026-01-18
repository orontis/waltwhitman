public class HumanitiesHumanoid extends Adventurer {
    private String [2][2] moveset = {{"[AA] AMCSO Assault","[DT] Divine Transcendentalism"},{"[PC] Pharaoh's Curse", "[CC] Citation Counter"}};
    private int healCounter = 2;
    private String[2][2] description = {{"filler","filler"},{"filler","filllller"}};
    private int damageMultiplier = 1;
    private int socialCredit = 0; // special resource for humanities people
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
    public String choseSupport(String move, Adventurer other) {
        String moveL = move.toLowerCase();
		if (moveL.equals("AMSCO Assault") || moveL.equals("AA"))
		{
			return amscoAssault(other);
		}
		else if (moveL.equals("Divine Transcendentalism") || moveL.equals("DT"))
		{
			return divineTrans(other);
		}
    }
    public String choseAttack(String move, Adventurer other) {
        String moveL = move.toLowerCase();
		if (moveL.equals("Pharaoh's Curse") || moveL.equals("PC"))
		{
			return faroCurse(other);
		}
		else if (moveL.equals("Citation Counter") || moveL.equals("CC"))
		{
			return citationCounter(other);
		}
    }

}  
